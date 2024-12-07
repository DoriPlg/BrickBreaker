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
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if (super.onCollision(thisObj, otherObj)) {
            if (!(otherObj instanceof Puck) && (otherObj instanceof Ball)) {
                ((Ball)otherObj).turboModeOn();
            }
            return true;
        }
        return false;
    }
}