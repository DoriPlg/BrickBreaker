package bricker.gameobjects;

import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import bricker.main.BrickerGameManager;

public class Puck extends Ball{

    private static final float SIZE_FACTOR = (float)3/4;
    public static final Vector2 PUCK_SIZE = Ball.STANDARD_BALL_SIZE.mult(SIZE_FACTOR);

    private final BrickerGameManager gameManager;

    /** 
     * Constructor for the puck
     * @param startLoc the starting location of the puck
     * @param renderable the renderable that will be used for the puck
     * @param collissionSound the sound that will be used for the puck
     * @param gameManager the game manager that will be used in the puck
     */
    public Puck(Vector2 startLoc, Renderable renderable, Sound collissionSound, BrickerGameManager gameManager) {
        super(startLoc, renderable, null, collissionSound);
        setDimensions(PUCK_SIZE);
        setVelocity(getVelocity().flipped(Vector2.UP));
        this.gameManager = gameManager;
    }


    /**
     * Overriden update method for the puck, to make it remove itself when it goes out of bounds
     * @param deltaTime the time that has passed since the last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(getTopLeftCorner().y() > gameManager.getScreenBottom()){
            gameManager.remove(this);
        }
    }
}