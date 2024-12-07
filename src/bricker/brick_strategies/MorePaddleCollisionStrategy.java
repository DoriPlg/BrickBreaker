package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.BrickBreakerGameManager;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;
import src.bricker.game_objects.Paddle;

import java.util.Random;

public class MorePaddleCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * Constructor for the MorePaddleCollisionStrategy
     * @param gameManager the game manager that will be used in the strategy
     */
    public MorePaddleCollisionStrategy(BrickBreakerGameManager gameManager) {
        super(gameManager);
    }


    /**
     * Special action for the MorePaddleCollisionStrategy
     * Should make an extra paddle in the game
     * @param thisObj the object that will be used in the special action
     * @param otherObj the other object that will be used in the special action
     */
    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        gameManager.makeExtraPaddle();
    }

    /**
    * Overriden onCollision method for the MorePaddleCollisionStrategy
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
