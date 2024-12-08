package bricker.brick_strategies;

import danogl.GameObject;
import bricker.main.BrickerGameManager;
import bricker.gameobjects.Brick;

public class BasicCollisionStrategy implements CollisionStrategy {
    final BrickerGameManager gameManager;


    /**
     * Constructor for the BasicCollisionStrategy
     * @param gameManager the game manager
     */
    public BasicCollisionStrategy(BrickerGameManager gameManager) {
        this.gameManager = gameManager;
    }



    /**
     * This method is used to remove the object from the game when a collision occurs
     * @param thisObj the object that is colliding
     * @param otherObj the object that is being collided with
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if(gameManager.remove(thisObj)){
            thisObj.setTag(Brick.POPPED);
        }
    }
}