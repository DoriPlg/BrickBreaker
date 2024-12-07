package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.BrickBreakerGameManager;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Puck;

public class TurboCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * Constructor for the TurboCollisionStrategy
     * @param gameManager the game manager that will be used in the strategy
     */
    public TurboCollisionStrategy(BrickBreakerGameManager gameManager) {
        super(gameManager);
    }


    /**
     * Special action for the TurboCollisionStrategy
     * Should make the ball go faster
     * @param thisObj the object that will be used in the special action
     * @param otherObj the other object that will be used in the special action
     */
    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        if (!(otherObj instanceof Puck) && (otherObj instanceof Ball)) {
            ((Ball)otherObj).turboModeOn();
        }
    }


    /**
     * On collision for the TurboCollisionStrategy
     * Should call the special action of the strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if (super.onCollision(thisObj, otherObj)) {
            specialAction(thisObj, otherObj);
            return true;
        }
        return false;
    }
}