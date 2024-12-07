package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import src.bricker.BrickBreakerGameManager;

public class BasicCollisionStrategy implements CollisionStrategy {
    protected final BrickBreakerGameManager gameManager;

    public BasicCollisionStrategy(BrickBreakerGameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        return;
    }

    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        return gameManager.remove(thisObj);

    }
}