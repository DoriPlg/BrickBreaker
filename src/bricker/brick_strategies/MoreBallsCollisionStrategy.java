package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.BrickBreakerGameManager;
import src.bricker.game_objects.Puck;

public class MoreBallsCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    private static  final int NUMBER_OF_BALLS = 2;

    /**
     * Constructor for the MoreBallsCollisionStrategy
     * @param gameManager the game manager that will be used in the strategy
     */
    public MoreBallsCollisionStrategy(BrickBreakerGameManager gameManager) {
        super(gameManager);
    }


    /**
     * Special action for the MoreBallsCollisionStrategy
     * Should make the number of balls that is defined in the constant
     * @param thisObj the object that will be used in the special action
     * @param otherObj the other object that will be used in the special action
     */
    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        gameManager.makePucks(NUMBER_OF_BALLS);
    }


    /**
     * On collision for the MoreBallsCollisionStrategy
     * Should call the special action of the strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if(super.onCollision(thisObj, otherObj)){
            specialAction(thisObj, otherObj);
            return true;
        }
        return false;
    }
}
