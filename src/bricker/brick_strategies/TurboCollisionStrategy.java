package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.BrickBreakerGameManager;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Puck;

public class TurboCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    public TurboCollisionStrategy(BrickBreakerGameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        if (!(otherObj instanceof Puck) && otherObj instanceof Ball ball){
            ball.turboModeOn();
        }
    }
}
