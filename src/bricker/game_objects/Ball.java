package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.ImageReader;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Ball extends GameObject {
    private Sound collissionSound;
    private int collisions;


    public Ball(Vector2 startLoc, Renderable renderable, Sound collissionSound) {
        super(startLoc, new Vector2(50, 50), renderable);
        this.collissionSound = collissionSound;
        this.setVelocity(Vector2.DOWN.mult(300));
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
