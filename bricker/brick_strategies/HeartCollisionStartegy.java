package bricker.brick_strategies;

import danogl.GameObject;
import bricker.main.BrickerGameManager;

public class HeartCollisionStartegy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * Constructor for the HeartCollisionStartegy
     * @param gameManager the game manager that will be used in the strategy
     */
    public HeartCollisionStartegy(BrickerGameManager gameManager){
        super(gameManager);
    }


    /**
     * On collision for the HeartCollisionStartegy
     * Should call the special action of the strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        gameManager.makeHeart(thisObj.getCenter());
    }
}
