package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;
import src.bricker.game_objects.FauxBall;

public class MoreBallsCollisionStrategy implements CollisionStrategy {

    private final Vector2 start;
    private final Renderable fauxBallImg;
    private static  final int NUMBER_OF_BALLS = 2;

    public MoreBallsCollisionStrategy(Vector2 start, Renderable fauxBallImg){
        this.start = start;
        this.fauxBallImg = fauxBallImg;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, GameObjectCollection objects) {
        for (int i = 0; i < NUMBER_OF_BALLS; i++) {
            FauxBall fake = new FauxBall(start, fauxBallImg);
            objects.addGameObject(fake);
        }
    }
}
