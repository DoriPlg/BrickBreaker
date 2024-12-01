package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;
import src.bricker.game_objects.Paddle;

import java.util.Random;

public class MorePaddleCollisionStrategy implements CollisionStrategy {

    private final Renderable paddleImg;
    private final Vector2 windowSize;
    private final UserInputListener inputListener;

    public MorePaddleCollisionStrategy(Renderable paddleImg,
                                       Vector2 windowSize,
                                       UserInputListener inputListener
    ) {
        super();
        this.paddleImg = paddleImg;
        this.windowSize = windowSize;
        this.inputListener = inputListener;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, GameObjectCollection objects) {
        Paddle paddle = new Paddle(paddleImg, inputListener, windowSize.x());
        Random rnd = new Random();
        float random = rnd.nextFloat(0,1);
        paddle.setCenter(
                new Vector2(paddle.getDimensions().x()/2 +
                        (windowSize.x()-paddle.getDimensions().x()) * random, windowSize.y() - 30)
        );
        objects.addGameObject(paddle);
    }
}
