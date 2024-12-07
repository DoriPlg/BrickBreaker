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
     * cosntructor
     */
    public MoreBallsCollisionStrategy(BrickBreakerGameManager gameManager) {
        super(gameManager);
    }

    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj) {
        if(super.onCollision(thisObj, otherObj)){
            gameManager.makePucks(NUMBER_OF_BALLS);
            return true;
        }
        return false;
    }
}
