package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

public class Ball extends GameObject {
    private static final float BALL_SPEED = 150;
    private static final float TURBO_FACTOR = 1.4f;
    private static final int HITS_IN_TURBO = 6;
    static final Vector2 STANDARD_BALL_SIZE = new Vector2(50, 50);
    private final Sound collissionSound;
    private final Renderable regularRender;
    private final Renderable turboRender;
    private int collisions;
    private int timeTurbo;
    private boolean turboMode;



    /**
    * Constructor for the Ball class
    * @param startLoc the starting location of the ball
    * @param regularRenderable the renderable for the ball
    * @param turboRender the renderable for the ball in turbo mode
    * @param collissionSound the sound to be played on collision
    */
    public Ball(Vector2 startLoc, Renderable regularRenderable, Renderable turboRender, Sound collissionSound) {
        super(startLoc, STANDARD_BALL_SIZE, regularRenderable);
        this.collissionSound = collissionSound;
        this.regularRender = regularRenderable;
        this.turboRender = turboRender;
        // Random angle between 45 and 135 degrees
        Random rand = new Random();
        float angle = (float) (rand.nextFloat() * Math.PI / 2 + Math.PI / 4);
        setVelocity(new Vector2((float) Math.cos(angle), (float) Math.sin(angle)).mult(BALL_SPEED));
        this.collisions = 0;
        this.turboMode = false;
    }

    /**
     * Turns on the turbo mode of the ball
     * If the turbo mode is already on, it does nothing
     */
    public void turboModeOn(){
        if (!turboMode) {
            setVelocity(getVelocity().mult(TURBO_FACTOR));
            renderer().setRenderable(turboRender);
            timeTurbo = collisions;
            this.turboMode = true;
        }
    }

    /**
     * Turns off the turbo mode of the ball
     * If the turbo mode is already off, it does nothing
     */
    private void turboModeOff(){
        if(turboMode){
            setVelocity(getVelocity().mult(1/TURBO_FACTOR));
            renderer().setRenderable(regularRender);
            this.turboMode = false;
        }
    }

    /**
     * Overrides the onCollisionEnter method of the GameObject class,
     * to handle the collision of the ball with other objects, making it 
     * bounce off the object and play a sound, incrementing the collision counter
     * and turning on the turbo mode if the number of collisions is greater than the
     * number of hits in turbo mode plus the time the turbo mode has been on.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisions++;
        collissionSound.play();
        if (collisions > HITS_IN_TURBO + timeTurbo && turboMode) {
            turboModeOff();
        }
    }
}