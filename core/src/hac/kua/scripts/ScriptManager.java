//package hac.kua.scripts;
///*
// * Riho Peterson 2014
// * tulevik.EU
// * http://www.indiedb.com/games/office-management-101
// */
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.utils.ObjectMap;
//
///*
// * Manages the Lua script cache and provides methods to call Lua script functions
// */
//public final class ScriptManager {
//    private static ObjectMap<String, LuaScript> scripts;
//
//    // Constructor
//    public ScriptManager() {
//        ScriptManager.scripts = new ObjectMap<String, LuaScript>();
//    }
//
//    // Load file by script key, returns false if fails
//    // You don't need to call this yourself, since the function calling methods do it already
//    public static boolean load(String name) {
//
//        LuaScript script = new LuaScript("assets/lua/" + name + ".lua");
//
//        if (script.canExecute()) {
//            // If already exists in cache, then delete the old one first
//            if (ScriptManager.scripts.containsKey(name)) {
//                ScriptManager.scripts.remove(name);
//            }
//            // Add to the cache
//            ScriptManager.scripts.put(name, script);
//            // Register ExecuteScript function, so it can be called from Lua scripts
//            script.registerJavaFunction(ExecuteScript.getInstance());
//            return true;
//        } else {
//            Gdx.app.log("Debug", name + " script " + " not found!");
//        }
//        return false;
//    }
//
//    // Execute a Lua function functionName in script file (key) and pass the rest of the parameters to the function
//    // Returns false when fails
//    public static boolean executeFunction(String key, String functionName, Object... objects) {
//        // Run the function if the script file is the cache
//        if (ScriptManager.scripts.containsKey(key)) {
//            return ScriptManager.scripts.get(key).executeFunction(functionName, objects);
//        } else {
//            // Try to load the script to the cache
//            if (!ScriptManager.load(key)) {
//                return false;
//            }
//            // Run the function
//            return ScriptManager.scripts.get(key).executeFunction(functionName, objects);
//        }
//    }
//
//    // Execute a Lua function functionName in script file (key) and pass the array of parameters to the function
//    // Returns false when fails
//    public static boolean executeFunctionParamsAsArray(String key, String functionName, Object[] objects) {
//        // Run the function if the script file is the cache
//        if (ScriptManager.scripts.containsKey(key)) {
//            return ScriptManager.scripts.get(key).executeFunctionParamsAsArray(functionName, objects);
//        } else {
//            // Try to load the script to the cache
//            if (!ScriptManager.load(key)) {
//                return false;
//            }
//            // Run the function
//            return ScriptManager.scripts.get(key).executeFunctionParamsAsArray(functionName, objects);
//        }
//    }
//
//    // Execute a Lua function "init" in script file (key) and pass the array of parameters to the function
//    // Returns false when fails
//    public static boolean executeInit(String key, Object... objects) {
//        // Run the function if the script file is the cache
//        if (ScriptManager.scripts.containsKey(key)) {
//            return ScriptManager.scripts.get(key).executeInit(objects);
//        } else {
//            // Try to load the script to the cache
//            if (!ScriptManager.load(key)) {
//                return false;
//            }
//            // Run the function
//            return ScriptManager.scripts.get(key).executeInit(objects);
//        }
//    }
//
//    // Execute a Lua function "update" in script file (key) and pass the array of parameters to the function
//    // Returns false when fails
//    public static boolean executeUpdate(String key, Object... objects) {
//        return ScriptManager.executeFunction(key, "update", objects);
//    }
//
//    // Clear the whole script cache
//    public static void dispose() {
//        ScriptManager.scripts = new ObjectMap<String, LuaScript>();
//    }
//}