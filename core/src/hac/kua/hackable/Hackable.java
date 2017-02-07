package hac.kua.hackable;

import com.badlogic.gdx.graphics.g2d.Batch;
import hac.kua.scripts.LuaScript;

/**
 * Created by kevin on 1/21/2017.
 * Base class for every hackable item
 */
public abstract class Hackable implements Hackable_Interface, Runnable{

    private Thread interactThread;

    private boolean running = true;
    public LuaScript script;
    public Hackable(){}

    @Override
    public void run() {
        while(running) {

            if(!script.interruptManager.interrupted)
            update();

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }


    public  void interact() {
        if(!script.interruptManager.interrupted)  new Thread(() -> script.executeFunction("interact", this, null)).start();
    }

    @Override
    public  void interact(Hackable user) {
        if(!script.interruptManager.interrupted) new Thread(() -> script.executeFunction("interact", this, user)).start();
    }

    @Override
    public void update() {
        if(!script.interruptManager.interrupted) script.executeFunction("update", this);
    }


    //Usually not accessable in LUA, but maybe. . .
    @Override
    public void draw(Batch batch) {

    }
}
