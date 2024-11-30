package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;

public class BasicCollisionStrategy implements CollisionStrategy {

    private static final float BALL_ACCELERATION_ON_HIT = 1.06f;

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (thisObj instanceof Brick) {
            if (otherObj instanceof Ball) {
                otherObj.setVelocity(otherObj.getVelocity().mult(BALL_ACCELERATION_ON_HIT));
            }
        }

    }
}
