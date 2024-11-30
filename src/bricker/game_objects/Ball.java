package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

public class Ball extends GameObject {
    private static final float BALL_SPEED = 150;
    private final Sound collissionSound;
    private int collisions;


    public Ball(Vector2 startLoc, Renderable renderable, Sound collissionSound) {
        super(startLoc, new Vector2(50, 50), renderable);
        this.collissionSound = collissionSound;
        float ballVelX = BALL_SPEED;
        float ballVelY = BALL_SPEED;
        Random rand = new Random();
        if (rand.nextBoolean()) {
            ballVelX = -ballVelX;
        }
        if (rand.nextBoolean()) {
            ballVelY = -ballVelY;
        }
        this.setVelocity(new Vector2(ballVelX, ballVelY));
        this.collisions = 0;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        this.collisions++;
        collissionSound.play();
    }

    public int getCollisionCounter(){
        return this.collisions;
    }

}
