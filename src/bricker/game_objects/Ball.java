package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

public class Ball extends GameObject {
    private static final float BALL_SPEED = 150;
    private static final Vector2 STANDARD_BALL_SIZE = new Vector2(50, 50);
    private static final float TURBO_FACTOR = 1.4f;
    private static final int HITS_IN_TURBO = 6;
    private final Sound collissionSound;
    private final Renderable regularRender;
    private final Renderable turboRender;
    private int collisions;
    private int timeTurbo;


    public Ball(Vector2 startLoc, Renderable regularRenderable, Renderable turboRender, Sound collissionSound) {
        super(startLoc, STANDARD_BALL_SIZE, regularRenderable);
        this.collissionSound = collissionSound;
        this.regularRender = regularRenderable;
        this.turboRender = turboRender;
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        double angle = rand.nextDouble() * Math.PI;
        ballVelY *= (float) Math.cos(angle);
        ballVelX *= (float) Math.sin(angle);
        this.setVelocity(new Vector2(ballVelX, ballVelY));
        this.collisions = 0;
    }

    public void turboModeOn(){
        if (getCollisionCounter() > HITS_IN_TURBO + timeTurbo) {
            setVelocity(getVelocity().mult(TURBO_FACTOR));
            renderer().setRenderable(turboRender);
            timeTurbo = getCollisionCounter();
        }
    }

    private void turboModeOff(){
        setVelocity(getVelocity().mult(1/TURBO_FACTOR));
        renderer().setRenderable(regularRender);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisions++;
        collissionSound.play();
        if (collisions > HITS_IN_TURBO + timeTurbo) {
            turboModeOff();
        }
    }

    public int getCollisionCounter(){
        return this.collisions;
    }

}
