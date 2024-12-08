package bricker.brick_strategies;

import danogl.GameObject;
import bricker.main.BrickerGameManager;


public class MorePaddleCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * Constructor for the MorePaddleCollisionStrategy
     * @param gameManager the game manager that will be used in the strategy
     */
    public MorePaddleCollisionStrategy(BrickerGameManager gameManager) {
        super(gameManager);
    }


    /**
    * Overriden onCollision method for the MorePaddleCollisionStrategy
    * Should call the special action of the strategy
    * @param thisObj the object that will be used in the on collision
    * @param otherObj the other object that will be used in the on collision
    * @return true if the collision was successful, false otherwise
    */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        gameManager.makeExtraPaddle();
    }
}
