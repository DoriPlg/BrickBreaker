package src.bricker;

import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;
import src.bricker.game_objects.LifeCount;
import src.bricker.game_objects.Paddle;

public class BrickBreakerGameManager extends GameManager {

    private final static int SCREEN_WIDTH = 700;
    private final static int SCREEN_LENGTH = 500;


    /**
     * Default Constructor.
     */
    public BrickBreakerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }


    /**
     * This method intializes the game.
     * @param
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Vector2 windowDimensions = windowController.getWindowDimensions();

        //ball
        Renderable ballImg =
            imageReader.readImage("assets/ball.png", true);
        Sound collisionSound = soundReader.readSound("assets/blop.wav");
        GameObject ball = new Ball(windowDimensions.mult((float)0.5), ballImg, collisionSound);
        gameObjects().addGameObject(ball);

        //paddle
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
                Renderable brickImg = imageReader.readImage("assets/brick.png",
                        false);
                GameObject brick = new Brick(
                        new Vector2(i * Brick.BRICK_WIDTH, j * Brick.BRICK_HEIGHT),
                        brickImg,
                        gameObjects()
                );
                gameObjects().addGameObject(brick);
            }
        }

        Renderable lifeImg = imageReader.readImage("assets/heart.png", true);
        LifeCount lifeCount =
                new LifeCount(
                        new Vector2(10,windowDimensions.y()-30),
                        lifeImg,
                        5
                );
        gameObjects().addGameObject(lifeCount);

    }

    /**
     * the Main method of the game
     * args[0] - round count
     * args[1] - size
     * args[2] - win streak
     * args[3] - renderer type
     * args[4] - player 1 name
     * args[5] - player 2 name
     */
    public static void main(String[] args) {
        BrickBreakerGameManager game = new BrickBreakerGameManager("Bricker",
                new Vector2(SCREEN_WIDTH, SCREEN_LENGTH));
        game.run();
    }
}
