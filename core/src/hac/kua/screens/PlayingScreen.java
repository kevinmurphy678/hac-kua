package hac.kua.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.utils.ShaderLoader;
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

    private PerspectiveCamera camera3D;



    private PostProcessor postProcessor;

    private Hackable hackable1;
    private Hackable hackable2;

    private DecalBatch dBatch;



    @Override
    public void show() {

        Core.setupFont();
        Core.setupAssets();
        Core.setupStage();

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

        camera3D = new PerspectiveCamera(80, 640, 480);
        camera3D.position.set(0,0,-5);
        camera3D.near = 1;
        camera3D.far = 30000;
        camera3D.position.set(0, 0, 128);

        dBatch = new DecalBatch(new CameraGroupStrategy(camera3D));

        asfasf = new TextureRegion(new Texture(Gdx.files.internal("assets/text.png")));

    }
TextureRegion asfasf = new TextureRegion();
    @Override
    public void render(float delta) {

        Core.assets.update();

        Gdx.gl.glClearColor(0.5f,0.45f,0.6f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        //postProcessor.capture();
       // postProcessor.setClearColor(0.1f, 0.2f, 0.1f, 0.9f);

        //World drawing/////////
//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        for(ObjectMap.Entry<String, Hackable> entry : Hackable_Manager.hackables)
//        {
//            entry.value.update(Gdx.graphics.getDeltaTime());
//            entry.value.draw(batch);
//        }
//
//        batch.end();
        ///////////////////////

        camera3D.update();
        int decals = 0;
        for(int x = 0; x < 10; x++)
            for(int y = 0; y < 10; y++)
            {
                if(x==0 || x ==9 || y==0 ||y ==9) {
                    for(int z = 0; z < 256; z++) {
                        Decal test = Decal.newDecal(16,16,asfasf,true);
                        test.setColor(z/256f,z/256f,z/256f,1);
                        test.setPosition(x * 16, y * 16, z * 1/8f);
                        dBatch.add(test);
                        decals++;
                    }
                }
            }

        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
        {
            camera3D.translate(Gdx.input.getDeltaX(), Gdx.input.getDeltaY(), 0);
        }


        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            camera3D.rotate(Gdx.input.getDeltaY(),1,0,0);
            camera3D.rotate(Gdx.input.getDeltaX(),0,1,0);
        }
        //test.lookAt(camera3D.position, camera3D.up);

        dBatch.flush();

        //Stage & HUD Drawing///////
        Stage stage = Core.stage;
        Gdx.input.setInputProcessor(stage);

        stage.draw();
        stage.act(delta);


        stage.getBatch().begin();
        Core.font.draw(stage.getBatch(), "FPS: " + Gdx.graphics.getFramesPerSecond(), 100,50);
        Core.font.draw(stage.getBatch(), "Decals: " + decals, 100,100);
        stage.getBatch().end();
        ////////////////////////


        //postProcessor.render();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2,camera.viewportHeight / 2, 0);
        Stage stage = Core.stage;
        stage.getViewport().update(width, height);
        stage.getCamera().position.set(stage.getCamera().viewportWidth / 2, stage.getCamera().viewportHeight / 2, 0);

        //Post Processing
        ShaderLoader.BasePath = "assets/shaders/";
        postProcessor = new PostProcessor(true, true,true);

       // int effectsff = Effect.TweakContrast.v | Effect.PhosphorVibrance.v | Effect.Scanlines.v | Effect.Tint.v;
        //CrtMonitor crt = new CrtMonitor(width, height, true, true, CrtScreen.RgbMode.ChromaticAberrations, effectsff );
        //postProcessor.addEffect(crt);
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
