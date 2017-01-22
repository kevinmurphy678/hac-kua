package hac.kua.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by kevin on 1/21/2017.
 * Helper class to store static and instance objects
 */
public class Core {


    //Fonts
    public static BitmapFont font;
    public static void setupFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/gohufont-14.fon"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 14;
        Core.font = generator.generateFont(parameter);
        Core.font.getData().markupEnabled=true;
        generator.dispose();
    }


    //Assets
    public static AssetManager assets;
    public static void setupAssets(){
        assets = new AssetManager();
        //TODO: load asset here
    }




}
