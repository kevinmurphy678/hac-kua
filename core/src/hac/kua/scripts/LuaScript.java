package hac.kua.scripts;

import com.badlogic.gdx.Gdx;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaScript {

    private final Globals globals;
    private LuaValue chunk;

    // Script exists and is otherwise loadable
    private boolean scriptFileExists;

    // Keep the file name, so it can be reloaded when needed
    public String scriptFileName;

    //Current script contents
    public String scriptContents = null;


    // Init the object and call the load method
    public LuaScript(String scriptFileName) {
        this(scriptFileName, JsePlatform.standardGlobals());
    }

    public LuaScript(String scriptFileName, Globals globals){

        this.scriptFileExists = false;
        this.globals = globals;

        this.load(scriptFileName);
    }


    public boolean loadString(String data) {
        try {
            chunk = globals.load(data);
            registerJavaFunction(Call.getInstance());
        } catch (LuaError e) {
            // If reading the file fails, then log the error to the console
            Gdx.app.log("Debug", "LUA ERROR! " + e.getMessage());
            this.scriptFileExists = false;
            return false;
        }
        chunk.call();
        this.scriptContents = data;
        return true;
    }


    // Load the file
    public boolean load(String scriptFileName) {
        this.scriptFileName = scriptFileName;

        if (!Gdx.files.internal(scriptFileName).exists()) {
            this.scriptFileExists = false;
            return false;
        } else {
            this.scriptFileExists = true;
        }
        try {
            chunk = globals.load(Gdx.files.internal(scriptFileName).readString());

            registerJavaFunction(Call.getInstance());
            //registerJavaFunction(CallNative.getInstance());


        } catch (LuaError e) {
            // If reading the file fails, then log the error to the console
            Gdx.app.log("Debug", "LUA ERROR! " + e.getMessage());
            this.scriptFileExists = false;
            return false;
        }

        // An important step. Calls to script method do not work if the chunk is not called here
        chunk.call();

        return true;
    }


    public boolean canExecute() {
        return scriptFileExists;
    }

    public LuaValue executeFunction(String functionName, Object... objects) {
        return executeFunctionParamsAsArray(functionName, objects);
    }

    // Now this function takes the parameters as an array instead, mostly meant so we can call other Lua script functions from Lua itself
    public LuaValue executeFunctionParamsAsArray(String functionName, Object[] objects) {
        if (!canExecute()) {
            return  LuaValue.FALSE;
        }

        LuaValue luaFunction = globals.get(functionName);

        // Check if a functions with that name exists
        if (luaFunction.isfunction()) {
            LuaValue[] parameters = new LuaValue[objects.length];

            int i = 0;
            for (Object object : objects) {
                // Convert each parameter to a form that's usable by Lua
                parameters[i] = CoerceJavaToLua.coerce(object);
                i++;
            }

            try {
                // Run the function with the converted parameters
                return (LuaValue)luaFunction.invoke(parameters);
            } catch (LuaError e) {
                // Log the error to the console if failed
                Gdx.app.log("Debug", "LUA ERROR! " + e.getMessage());
                return  LuaValue.FALSE;
            }

        }
        return  LuaValue.FALSE;
    }

    // With this we register a Java function that we can call from the Lua script
    public void registerJavaFunction(TwoArgFunction javaFunction) {
        globals.load(javaFunction);
    }
}