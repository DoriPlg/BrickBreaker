package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.game_objects.Puck;

public class MoreBallsCollisionStrategy implements CollisionStrategy {

    private final Vector2 start;
    private final Renderable fauxBallImg;
    private static  final int NUMBER_OF_BALLS = 2;

    public MoreBallsCollisionStrategy(Vector2 start, Renderable fauxBallImg){
        this.start = start;
        this.fauxBallImg = fauxBallImg;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            Puck fake = new Puck(start, fauxBallImg);
//            objects.addGameObject(fake);
        }
    }
}
