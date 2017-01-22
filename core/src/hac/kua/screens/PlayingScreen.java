package hac.kua.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ObjectMap;
import hac.kua.hackable.Hackable;
import hac.kua.hackable.Hackable_Manager;
import hac.kua.hackable.MovingThing;
import hac.kua.scripts.Call;
import hac.kua.utils.Core;
import jdk.nashorn.internal.objects.Global;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.lang.reflect.Field;


/**
 * Created by kevin on 1/21/2017.
 * Main playing screen.
 */


public class PlayingScreen implements Screen{
    private SpriteBatch batch;

    private Hackable hackable1;
    private Hackable hackable2;

    @Override
    public void show() {


        Core.setupFont();

        batch = new SpriteBatch();


        hackable1 = new MovingThing();
        hackable2 = new MovingThing();
        hackable1.interact(hackable1);
        hackable2.interact(hackable1);

        //Adding to global hackables list
        Hackable_Manager.add("Hack1", hackable1);
        Hackable_Manager.add("Hack2", hackable2);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.5f,0.45f,0.6f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for(ObjectMap.Entry<String, Hackable> entry : Hackable_Manager.hackables)
        {
            entry.value.update(Gdx.graphics.getDeltaTime());
            entry.value.draw(batch);
        }

        batch.end();

        //Call 'anotherFunction' on hackable1
        hackable1.script.executeFunction("anotherFunction", "Hello there");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }


    //UNUSED////////////
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
