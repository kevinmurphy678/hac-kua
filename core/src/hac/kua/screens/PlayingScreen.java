package hac.kua.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.*;
import hac.kua.hackable.Hackable;
import hac.kua.hackable.Hackable_Manager;
import hac.kua.hackable.MovingThing;
import hac.kua.utils.Core;


/**
 * Created by kevin on 1/21/2017.
 * Main playing screen.
 */


public class PlayingScreen implements Screen{

    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Hackable hackable1;
    private Hackable hackable2;

    @Override
    public void show() {

        Core.setupFont();
        Core.setupAssets();

        camera = new OrthographicCamera();
        viewport = new FillViewport(640,480,camera);


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

        Core.assets.update();

        Gdx.gl.glClearColor(0.5f,0.45f,0.6f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        Core.font.draw(batch, "Welcome!", 200,200);

        for(ObjectMap.Entry<String, Hackable> entry : Hackable_Manager.hackables)
        {
            entry.value.update(Gdx.graphics.getDeltaTime());
            entry.value.draw(batch);
        }

        batch.end();

        //Call 'anotherFunction' on hackable1
        //hackable1.script.executeFunction("anotherFunction", "Hello there");

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2,camera.viewportHeight / 2, 0);
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
