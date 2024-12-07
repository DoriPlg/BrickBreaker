package src.bricker.game_objects;
import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import java.awt.*;

public class LifeCount extends GameObject {
    private static final Color GREEN = Color.GREEN;
    private static final Color YELLOW = Color.YELLOW;
    private static final Color RED = Color.RED;
    private static final Vector2 RENDER_SIZE = new Vector2(20, 20);
    private final static int LIVES_UPPER_LIMIT = 4;

    private TextRenderable text;
    private int lives;

    public LifeCount(Vector2 position, Renderable renderable, int lives) {
        super(position, RENDER_SIZE, renderable);
        this.lives = lives;
        this.text = new TextRenderable(String.valueOf(lives));

    }

    @Override
    public void render(Graphics2D g)
        /*
         * Renders the life count on the screen,
         * by drawing the heart image multiple times
         */
    {
        if(lives>=3){
            text.setColor(GREEN);
        }
        else if(lives==2){
            text.setColor(YELLOW);
        }
        else if(lives==1){
            text.setColor(RED);
        }
        text.setString(String.valueOf(lives));
        text.render(g, getTopLeftCorner(), RENDER_SIZE);
        for (int i = 0; i < lives; i++) {
            renderer().getRenderable().render(g,
                    new Vector2(getTopLeftCorner().x() + RENDER_SIZE.mult(2f).x() + i * (int) RENDER_SIZE.x(),
                            getTopLeftCorner().y()),
                    getDimensions()
            );
        }
    }

    public void decrementLife() {
        lives--;
    }

    public void incrementLife() {
        if(this.lives<LIVES_UPPER_LIMIT){
            lives++;
        }
    }

    public int getLives() {
        return lives;
    }



}