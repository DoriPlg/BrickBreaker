package src.bricker.game_objects;

import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Puck extends Ball{

    private static final float SIZE_FACTOR = (float)3/4;
    public static final Vector2 PUCK_SIZE = Ball.STANDARD_BALL_SIZE.mult(SIZE_FACTOR);

    /**
     Constructor for the puck
     */
    public Puck(Vector2 startLoc, Renderable renderable, Sound collissionSound) {
        super(startLoc, renderable, null, collissionSound);
        setDimensions(PUCK_SIZE);
    }
}