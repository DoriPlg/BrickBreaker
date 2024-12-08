package bricker.brick_strategies;

import danogl.GameObject;
import bricker.main.BrickerGameManager;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Puck;

public class TurboCollisionStrategy extends BasicCollisionStrategy implements CollisionStrategy {

    /**
     * Constructor for the TurboCollisionStrategy
     * @param gameManager the game manager that will be used in the strategy
     */
    public TurboCollisionStrategy(BrickerGameManager gameManager) {
        super(gameManager);
    }



    /**
     * On collision for the TurboCollisionStrategy
     * Should call the special action of the strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        if (!(otherObj instanceof Puck) && (otherObj instanceof Ball)) {
            ((Ball)otherObj).turboModeOn();
        }
    }
}