package hac.kua.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import hac.kua.desktop.Taskbar;

/**
 * Created by kevin on 1/21/2017.
 * Helper class to store static and instance objects
 */
public class Core {
    //Fonts
    public static BitmapFont font;
    public static GlyphLayout fontLayout;
    public static Taskbar taskbar;

    public static void setupFont(){
        fontLayout = new GlyphLayout();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/gohufont-14.fon"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 14;
        Core.font = generator.generateFont(parameter);
        Core.font.getData().markupEnabled=true;

        generator.dispose();
    }

    //Assets
    public static AssetManager assets;
    public static Texture pixel;//Temp
    public static void setupAssets(){
        assets = new AssetManager();
        pixel = new Texture(Gdx.files.internal("assets/pixel.png"));
        //TODO: load asset here
    }


    //Stage
    public static Stage stage;
    public static Skin skin;
    public static void setupStage()
    {

        stage = new Stage();
        stage = new Stage(new ScreenViewport());
        skin = new Skin();

        skin.add("default-font", font, BitmapFont.class);
        skin.add("small-font", font, BitmapFont.class);
        FileHandle fileHandle = Gdx.files.internal("assets/uiskin.json");
        FileHandle atlasFile = Gdx.files.internal("assets/uiskin.atlas");

        if (atlasFile.exists()) {
            skin.addRegions(new TextureAtlas(atlasFile));
        }

        skin.load(fileHandle);
        VisUI.load(skin);
    }

}
