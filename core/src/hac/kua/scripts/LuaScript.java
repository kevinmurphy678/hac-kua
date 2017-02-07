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

    //Allows lua script interruption in case of infinite loop..
    public InterruptManager interruptManager;

    //For knowing when the script is actually interrupted
    public Boolean interrupted = false;

    // Init the object and call the load method
    public LuaScript(String scriptFileName) {
        this(scriptFileName, JsePlatform.standardGlobals());
    }

    public LuaScript(String scriptFileName, Globals globals){
        this.scriptFileExists = false;
        this.globals = globals;
        interruptManager = new InterruptManager();
        this.globals.load(interruptManager);
        this.load(scriptFileName);
    }

    //Forces the script to stop, in case of infinite loop
    public void forceInterruption()
    {
        interruptManager.setInterrupted(true);
    }


    public void loadGlobalFunctions()
    {
        globals.set("Input", CoerceJavaToLua.coerce(Gdx.input));
        registerJavaFunction(Call.getInstance());
    }

    //Loads string data into the script
    public boolean loadString(String data) {
        try {
            chunk = globals.load(data);
            loadGlobalFunctions();

        } catch (LuaError e) {
            // If reading the file fails, then log the error to the console
            Gdx.app.log("Debug", "LUA ERROR! " + e.getMessage());
            return false;
        }
        try {
            chunk.call();
        }
        catch(RuntimeException e)
        {
            interrupted = true;
        }

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
            loadGlobalFunctions();
        } catch (LuaError e) {
            // If reading the file fails, then log the error to the console
            Gdx.app.log("Debug", "LUA ERROR:  " + e.getMessage());
            this.scriptFileExists = false;
            return false;
        }
        try {
            chunk.call();
        }
        catch(RuntimeException e)
        {
            interrupted = true;
        }
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

                interruptManager.setInterrupted(true);

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