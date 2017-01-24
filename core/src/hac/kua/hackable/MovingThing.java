package hac.kua.hackable;

import com.badlogic.gdx.math.Vector2;
import hac.kua.scripts.LuaScript;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by kevin on 1/21/2017.
 * Basic moving thing
 */
public class MovingThing extends Hackable {

    public Vector2 position = new Vector2();

    public MovingThing() {script = new LuaScript("assets/lua/hello.lua");}

    @Override
    public void interact(Hackable user) {
        super.interact(user);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
