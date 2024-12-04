package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.game_objects.Ball;

public class TurboCollisionStrategy implements CollisionStrategy {

    public TurboCollisionStrategy() {}

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (otherObj instanceof Ball ball){
            ball.turboModeOn();
        }
    }
}
