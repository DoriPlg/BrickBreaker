package bricker.brick_strategies;

import danogl.GameObject;
import bricker.main.BrickerGameManager;

public class MoreBallsCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    private static  final int NUMBER_OF_BALLS = 2;

    /**
     * Constructor for the MoreBallsCollisionStrategy
     * @param gameManager the game manager that will be used in the strategy
     */
    public MoreBallsCollisionStrategy(BrickerGameManager gameManager) {
        super(gameManager);
    }


    /**
     * On collision for the MoreBallsCollisionStrategy
     * Should call the special action of the strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        gameManager.makePucks(NUMBER_OF_BALLS);
    }
}
