package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;

public class MoreBallsCollisionStrategy implements CollisionStrategy {
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (thisObj instanceof Brick){
            if (otherObj instanceof Ball){
                // add 2 more balls to the game
                
            }
        }
    }
}
