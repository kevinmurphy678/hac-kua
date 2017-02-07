package hac.kua.hackable;

import com.badlogic.gdx.graphics.g2d.Batch;
import hac.kua.scripts.LuaScript;

/**
 * Created by kevin on 1/21/2017.
 */
public interface Hackable_Interface {


    void interact(Hackable user);
    void update();
    void draw(Batch batch);


}
