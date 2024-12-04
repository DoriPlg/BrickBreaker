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
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        return gameManager.remove(otherObj);
    }
}
