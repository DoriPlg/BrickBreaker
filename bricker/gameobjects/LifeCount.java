package bricker.gameobjects;
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
    private final int LIVES_UPPER_LIMIT;

    private TextRenderable text;
    private int lives;


    /**
     * Constructor for the LifeCount class
     * @param position The position of the life count on the screen
     * @param renderable The renderable object of the life count
     * @param lives The number of lives the player has
     * @param max_lives The maximum number of lives the player can have
     */
    public LifeCount(Vector2 position, Renderable renderable, int lives, int max_lives) {
        super(position, RENDER_SIZE, renderable);
        this.lives = lives;
        this.LIVES_UPPER_LIMIT = max_lives;
        this.text = new TextRenderable(String.valueOf(lives));

    }


    /**
     * Renders the life count on the screen
     * The life count is rendered as a number and as a series of heart icons
     * @param g The graphics object to render the life count
     */
    @Override
    public void render(Graphics2D g)
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


    /**
     * Decreases the number of lives the player has
     */
    public void decrementLife() {
        lives--;
    }


    /**
     * Increases the number of lives the player has
     */
    public void incrementLife() {
        if(this.lives<LIVES_UPPER_LIMIT){
            lives++;
        }
    }


    /**
     * Getter for the number of lives the player has
     * @return the number of lives the player has
     */
    public int getLives() {
        return lives;
    }
}