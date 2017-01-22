package hac.kua.hackable;

import com.badlogic.gdx.graphics.g2d.Batch;
import hac.kua.scripts.LuaScript;

/**
 * Created by kevin on 1/21/2017.
 * Base class for every hackable item
 */
public abstract class Hackable implements  Hackable_Interface {

    @Override
    public void interact(Hackable user) {
        script.executeFunction("interact", this, user);
    }

    @Override
    public void update(float delta) {
        script.executeFunction("update", this, delta);
    }


    //Usually not accessable in LUA, but maybe. . .
    @Override
    public void draw(Batch batch) {

    }

    public LuaScript script;
    public Hackable(){}
}
