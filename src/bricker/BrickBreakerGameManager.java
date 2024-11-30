package src.bricker;

import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;
import src.bricker.game_objects.Paddle;

public class BrickBreakerGameManager extends GameManager {

    public BrickBreakerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Vector2 windowDimensions = windowController.getWindowDimensions();

        Renderable ballImg =
            imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        GameObject ball = new Ball(windowDimensions.mult((float)0.5), ballImg, collisionSound);
        gameObjects().addGameObject(ball);

        Renderable paddleImg =
                imageReader.readImage("assets/paddle.png", false);
        Paddle paddle = new Paddle(paddleImg, inputListener);
        paddle.setCenter(
                new Vector2(windowDimensions.x()/2, windowDimensions.y() - 30)
        );
        gameObjects().addGameObject(paddle);

        GameObject rightBuffer = new GameObject(
                new Vector2(windowDimensions.x(), 0),
                new Vector2(1, windowDimensions.y()),
                null
        );
        gameObjects().addGameObject(rightBuffer);

        GameObject leftBuffer = new GameObject(
                new Vector2(-1, 0),
                new Vector2(1, windowDimensions.y()),
                null
        );
        gameObjects().addGameObject(leftBuffer);

        GameObject topBuffer = new GameObject(
                new Vector2(0, -1),
                new Vector2(windowDimensions.x(), 1),
                null
        );
        gameObjects().addGameObject(topBuffer);

        for (int i = 0; i < windowDimensions.x() / Brick.BRICK_WIDTH; i++) {
            for (int j = 0; j < 5; j++) {
                Renderable brickImg = imageReader.readImage("assets/brick.png", false);
                GameObject brick = new Brick(
                        new Vector2(i * Brick.BRICK_WIDTH, j * Brick.BRICK_HEIGHT),
                        brickImg
                );
                gameObjects().addGameObject(brick);
            }
        }

        Renderable lifeImg = imageReader.readImage("assets/heart.png", true);
        GameObject lifeCount = new GameObject(
                new Vector2(0, 0),
                new Vector2(0, 0),
                lifeImg
        );
        gameObjects().addGameObject(lifeCount);

    }

    public static void main(String[] args) {
        new BrickBreakerGameManager(
                "Brick Breaker",
                new Vector2(800, 600)
        ).run();

    }
}
