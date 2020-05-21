package hac.kua.hackable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import hac.kua.scripts.LuaScript;
import hac.kua.utils.Core;
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

    public void Move(int dir){System.out.println("Moved: " + dir);}

    public MovingThing() {script = new LuaScript("assets/lua/hello.lua");}


    @Override
    public void draw(Batch batch){
        batch.setColor(Color.WHITE);
        batch.draw(Core.pixel, position.x, position.y, 32, 32);
    }

    @Override
    public void interact() {
        super.interact();
    }

    @Override
    public void interact(Hackable user) {
        super.interact(user);
    }

    @Override
    public void update() {
        super.update();
    }
}
