package hac.kua.scripts;

import com.badlogic.gdx.Gdx;
import hac.kua.hackable.Hackable_Manager;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * Created by kevin on 1/21/2017.
 * Call class for calling other lua scripts from within lua
 */
public final class Call extends TwoArgFunction {

    private static Call instance = new Call();
    public Call() {}
    public static Call getInstance() {
        return instance;
    }
    @Override
    public LuaValue call(LuaValue modname, LuaValue env) {
        env.set("Call", new ExecuteScriptImplementation());
        return env;
    }
    static class ExecuteScriptImplementation extends ThreeArgFunction {
        public LuaValue call(LuaValue key, LuaValue functionName, LuaValue objects) {
            if (!key.isstring()) {
                Gdx.app.log("Debug", "The first parameter of ExecuteScript has to be a string");
                return LuaValue.valueOf(false);
            }
            if (!functionName.isstring()) {
                Gdx.app.log("Debug", "The second parameter of ExecuteScript has to be a string");
                return LuaValue.valueOf(false);
            }
            // Table is Lua's version of an array
            if (!objects.istable()) {
                Gdx.app.log("Debug", "The third parameter of ExecuteScript has to be a table");
                return LuaValue.valueOf(false);
            }
            // Convert Lua value to a table
            LuaTable luaTable = objects.checktable();
            // Construct a Java array from the table
            Object[] objectArray = new Object[luaTable.length()];
            for (int i = 0; i < luaTable.length(); i++) {
                objectArray[i] = luaTable.get(i + 1);
            }
            // Run the Lua function functionName in script file key and pass the parameters to it, convert the result to a form that Lua can use
            return Hackable_Manager.hackables.get(key.toString()).script.executeFunctionParamsAsArray(functionName.toString(), objectArray);
        }
    }
}
