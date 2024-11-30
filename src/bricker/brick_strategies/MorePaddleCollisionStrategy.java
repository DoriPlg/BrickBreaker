package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;

public class MorePaddleCollisionStrategy implements CollisionStrategy {
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (thisObj instanceof Brick){
            if (otherObj instanceof Ball){
                // Add another paddle
            }
        }
    }
}
