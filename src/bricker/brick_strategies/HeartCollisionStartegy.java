package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import src.bricker.BrickBreakerGameManager;

public class HeartCollisionStartegy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * Constructor for the HeartCollisionStartegy
     * @param gameManager the game manager that will be used in the strategy
     */
    public HeartCollisionStartegy(BrickBreakerGameManager gameManager){
        super(gameManager);
    }


    /**
     * Special action for the HeartCollisionStartegy
     * Should make a heart in the game
     * @param thisObj the object that will be used in the special action
     * @param otherObj the other object that will be used in the special action
     */
    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        gameManager.makeHeart(thisObj.getCenter());
    }


    /**
     * On collision for the HeartCollisionStartegy
     * Should call the special action of the strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if(super.onCollision(thisObj, otherObj)){
            specialAction(otherObj, thisObj);
            return true;
        }
        return false;
    }
}
