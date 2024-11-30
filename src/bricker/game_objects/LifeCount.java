package src.bricker.game_objects;
import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.*;

public class LifeCount extends GameObject {
    private static final Vector2 HEART_SIZE = new Vector2(20, 20);
    private int lives;

    public LifeCount(Vector2 position, Renderable renderable, int lives) {
        super(position, HEART_SIZE, renderable);
        this.lives = lives;
    }

    @Override
    public void render(Graphics2D g)
    /*
    * Renders the life count on the screen,
    * by drawing the heart image multiple times
    */
   {
        for (int i = 0; i < lives; i++) {
            renderer().getRenderable().render(g,
                    new Vector2(getTopLeftCorner().x() + i * (int) HEART_SIZE.x(), getTopLeftCorner().y()),
                    getDimensions()
             );
        }
   }

    public void loseLife() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

}
