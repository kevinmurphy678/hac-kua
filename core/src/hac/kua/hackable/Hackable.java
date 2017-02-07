package hac.kua.hackable;

import com.badlogic.gdx.graphics.g2d.Batch;
import hac.kua.scripts.LuaScript;

/**
 * Created by kevin on 1/21/2017.
 * Base class for every hackable item
 */
public abstract class Hackable implements Hackable_Interface, Runnable{

    private boolean running = true;
    public LuaScript script;
    public Hackable(){}

    @Override
    public void run() {
        while(running) {

            if(!script.interruptManager.isInterrupted())
            update();

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }


    public  void interact() {
        if(!script.interruptManager.isInterrupted())  new Thread(() -> script.executeFunction("interact", this, null)).start();
    }

    @Override
    public  void interact(Hackable user) {
        if(!script.interruptManager.isInterrupted()) new Thread(() -> script.executeFunction("interact", this, user)).start();
    }

    @Override
    public void update() {
        if(!script.interruptManager.isInterrupted()) script.executeFunction("update", this);
    }


    //Usually not accessable in LUA, but maybe. . .
    @Override
    public void draw(Batch batch) {

    }
}
