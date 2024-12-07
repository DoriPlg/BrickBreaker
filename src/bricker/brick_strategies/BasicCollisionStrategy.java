package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import src.bricker.BrickBreakerGameManager;

public class BasicCollisionStrategy implements CollisionStrategy {
    final BrickBreakerGameManager gameManager;


    /**
     * Constructor for the BasicCollisionStrategy
     * @param gameManager the game manager
     */
    public BasicCollisionStrategy(BrickBreakerGameManager gameManager) {
        this.gameManager = gameManager;
    }


    /**
     * This method is used to perform a special action when a collision occurs
     * @param thisObj the object that is colliding
     * @param otherObj the object that is being collided with
     */
    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        return;
    }


    /**
     * This method is used to remove the object from the game when a collision occurs
     * @param thisObj the object that is colliding
     * @param otherObj the object that is being collided with
     */
    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        return gameManager.remove(thisObj);

    }
}