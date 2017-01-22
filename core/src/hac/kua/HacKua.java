package hac.kua;
import com.badlogic.gdx.Game;
import hac.kua.screens.PlayingScreen;


public class HacKua extends Game {
	@Override
    public void create () {
        setScreen(new PlayingScreen());
	}
}
