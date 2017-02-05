package hac.kua;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import hac.kua.utils.Core;

/**
 * Created by kevin on 1/27/2017.
 */
public class GameMap {

    public GameMap(){}

    public float xPos = 199;
    public float yPos = 300;

    public void update(float delta)
    {

    }

    public void draw(Batch batch)
    {
        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                int size = 16;
                batch.setColor(Color.WHITE);
                batch.draw(Core.pixel, xPos + (x*size), yPos + (y*size), size-4, size-4);
            }
        }
    }


}
