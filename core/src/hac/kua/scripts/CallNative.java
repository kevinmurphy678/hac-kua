//package hac.kua.scripts;
//
//import com.badlogic.gdx.Gdx;
//import hac.kua.hackable.Hackable_Manager;
//import net.openhft.compiler.CompilerUtils;
//import org.luaj.vm2.LuaTable;
//import org.luaj.vm2.LuaValue;
//import org.luaj.vm2.lib.ThreeArgFunction;
//import org.luaj.vm2.lib.TwoArgFunction;
//
///**
// * Created by kevin on 1/21/2017.
// * Testing out : https://github.com/OpenHFT/Java-Runtime-Compiler
// * Un-used for now...
// *         compile group: 'net.openhft', name: 'compiler', version: '2.3.0'
// */
//public final class CallNative extends TwoArgFunction{
//
//    private static CallNative instance = new CallNative();
//    public CallNative() {}
//    public static CallNative getInstance() {
//        return instance;
//    }
//
//
//    @Override
//    public LuaValue call(LuaValue modname, LuaValue env) {
//        //env.set("Call", new Call.ExecuteScriptImplementation());
//
//        // dynamically you can call
//        String className = "mypackage.MyClass";
//        String javaCode = "package mypackage;\nimport com.badlogic.gdx.*;\n" +
//                "public class MyClass implements Runnable {\n" +
//                "    public void run() {\n" +
//                "        Gdx.gl.glClearColor(1f,0f,1f,1f);\n" +
//                "    }\n" +
//                "}\n";
//
//        try {
//            CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
//            Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
//            Runnable runner = (Runnable) aClass.newInstance();
//            runner.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return LuaValue.TRUE;
//    }
//}
