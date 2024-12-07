package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import src.bricker.BrickBreakerGameManager;

public class HeartCollisionStartegy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * constructor
     */
    public HeartCollisionStartegy(BrickBreakerGameManager gameManager){
        super(gameManager);
    }

    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        gameManager.makeHeart(thisObj.getCenter());
    }

    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if(super.onCollision(thisObj, otherObj)){
            specialAction(otherObj, thisObj);
            return true;
        }
        return false;
    }
}
