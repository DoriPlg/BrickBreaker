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
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        if (!(otherObj instanceof Puck) && (otherObj instanceof Ball)) {
            ((Ball)otherObj).turboModeOn();
        }
    }

    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if (super.onCollision(thisObj, otherObj)) {
            specialAction(thisObj, otherObj);
            return true;
        }
        return false;
    }
}