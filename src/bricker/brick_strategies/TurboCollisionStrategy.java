package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Puck;

public class TurboCollisionStrategy implements CollisionStrategy {

    public TurboCollisionStrategy() {}

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (!(otherObj instanceof Puck) && otherObj instanceof Ball ball){
            ball.turboModeOn();
        }
    }
}
