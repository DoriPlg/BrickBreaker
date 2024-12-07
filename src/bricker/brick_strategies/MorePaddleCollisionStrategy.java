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
         * constructor
         */
        public MorePaddleCollisionStrategy(BrickBreakerGameManager gameManager) {
            super(gameManager);
        }

    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        gameManager.makeExtraPaddle();
    }

    /**
        * method that takes action in the case in which a brick with the extra paddle property was hit
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
