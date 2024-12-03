package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import src.bricker.BrickBreakerGameManager;

public class BasicCollisionStrategy implements CollisionStrategy {
    private final BrickBreakerGameManager gameManager;

    public BasicCollisionStrategy(BrickBreakerGameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        gameManager.remove(thisObj);
    }
}
