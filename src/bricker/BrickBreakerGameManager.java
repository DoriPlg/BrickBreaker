package src.bricker;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.brick_strategies.BasicCollisionStrategy;
import src.bricker.brick_strategies.CollisionStrategy;
import src.bricker.game_objects.Ball;
import src.bricker.game_objects.Brick;
import src.bricker.game_objects.LifeCount;
import src.bricker.game_objects.Paddle;

import java.awt.event.KeyEvent;
import java.util.Random;

public class BrickBreakerGameManager extends GameManager {


    private static Vector2 WINDOW_DIMENSIONS ;
    private static float BRICK_ROW_NUMBER = 7;
    private static float BRICK_COL_NUMBER = 8;
    private static Ball ball = null;
    private LifeCount lifeCount;
    private static final int STARTING_NUMBER_OF_LIVES = 3;
    private static Vector2 CENTER = null;
    private static ImageReader imageReader;
    private static SoundReader soundReader;
    private static WindowController windowController;

    private final static int SCREEN_WIDTH = 700;
    private final static int SCREEN_LENGTH = 500;
    private final static Renderable NON_RENDERABLE = null;
    private final static String BALL_IMAGE = "assets/ball.png";
    private final static String PADDLE_IMAGE = "assets/paddle.png";
    private final static String BRICK_IMAGE = "assets/brick.png";
    private final static String HEART_IMAGE = "assets/heart.png";
    private final static String COLLISION_SOUND = "assets/blop.wav";
    private final static String BACKGROUND_IMAGE = "assets/DARK_BG2_small.jpeg";
    private static final float BALL_SPEED = 150; // still a problem , should the manager now this ?
    private UserInputListener inputListener;
//    private Brick[] brickArray;



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
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.windowController = windowController;
        this.inputListener = inputListener;
        WINDOW_DIMENSIONS = windowController.getWindowDimensions();
        CENTER = WINDOW_DIMENSIONS.mult((float)0.5);
        windowController.setTargetFramerate(80);
        //ball
        createBall(imageReader,soundReader);
        //paddle
        createPaddle(imageReader,inputListener);
        //createBuffers
        createBuffers();
        //create Bricks
        brickBuilder(imageReader);
        //hearts
        createLifeCount(imageReader);
        //background
        createBackground(imageReader);
    }



    /**
     * This method updates the game's objects status .
     * @param
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float ballHeight = ball.getCenter().y();
//        if (Brick.getLastDestroyed() >= 0){
//            gameObjects().removeGameObject(brickArray[Brick.getLastDestroyed()]);
//        }

        checkGameEnd(ballHeight);
    }


    /**
     * the Main method of the game
     * args[0] - number of bricks in row(col count)
     * args[1] - number of rows
     * args[2] - win streak
     * args[3] - renderer type
     * args[4] - player 1 name
     * args[5] - player 2 name
     */
    public static void main(String[] args) {
        BrickBreakerGameManager game = new BrickBreakerGameManager("Bricker",
                new Vector2(SCREEN_WIDTH, SCREEN_LENGTH));
        if(args.length!=0){
            BRICK_COL_NUMBER = Integer.parseInt(args[0]);
            BRICK_ROW_NUMBER = Integer.parseInt(args[1]);
        }
        game.run();
    }



    /**
     * This method builds the  paddle of the game.
     */
    private void createPaddle(ImageReader imageReader, UserInputListener inputListener) {
        Renderable paddleImg =
                imageReader.readImage(PADDLE_IMAGE, false);
        Paddle paddle = new Paddle(paddleImg, inputListener, WINDOW_DIMENSIONS.x());
        paddle.setCenter(
                new Vector2(WINDOW_DIMENSIONS.x()/2, WINDOW_DIMENSIONS.y() - 30)
        );
        gameObjects().addGameObject(paddle);
    }



    /**
     * This method builds the default ball of the game.
     */
    private void createBall(ImageReader imageReader, SoundReader soundReader) {
        Renderable ballImg =
                imageReader.readImage(BALL_IMAGE, true);
        Sound collisionSound = soundReader.readSound(COLLISION_SOUND);
        Ball ball = new Ball(CENTER, ballImg, collisionSound); // !!!!!!!!!!!!!!!! nu e Ball ball
        this.ball = ball;
        gameObjects().addGameObject(ball);
    }

    /**
     * This method builds the walls of the game.
     */
    private void createBuffers(){
        //create right
        GameObject rightBuffer = new GameObject(
                new Vector2(WINDOW_DIMENSIONS.x(), 0),
                new Vector2(1, WINDOW_DIMENSIONS.y()),
                NON_RENDERABLE
        );
        gameObjects().addGameObject(rightBuffer);

        //create left
        GameObject leftBuffer = new GameObject(
                new Vector2(-1, 0),
                new Vector2(1, WINDOW_DIMENSIONS.y()),
                NON_RENDERABLE
        );
        gameObjects().addGameObject(leftBuffer);
        //create top
        GameObject topBuffer = new GameObject(
                new Vector2(0, -1),
                new Vector2(WINDOW_DIMENSIONS.x(), 1),
                NON_RENDERABLE
        );
        gameObjects().addGameObject(topBuffer);

    }

    /**
     * This method builds all the bricks for the game.
     */
    private void brickBuilder(ImageReader imageReader) {
//        brickArray = new Brick[(int)(BRICK_ROW_NUMBER * BRICK_COL_NUMBER)];
        float brickWidth = WINDOW_DIMENSIONS.x()/BRICK_COL_NUMBER;
        float newBrickCoordinate = 0;
//        int index = 0;
        Vector2 brickSize = new Vector2(
                brickWidth,
                Brick.BRICK_HEIGHT
        );
        for (int i = 0; i < BRICK_COL_NUMBER; i++) {
            for (int j = 0; j < BRICK_ROW_NUMBER; j++) {
                Renderable brickImg = imageReader.readImage(BRICK_IMAGE,
                        false);
                newBrickCoordinate = i * brickWidth;
                CollisionStrategy strategy = getColisionSrategy();
                Brick brick = new Brick(
                        new Vector2(newBrickCoordinate, j * Brick.BRICK_HEIGHT),
                        brickSize,
                        brickImg,
                        strategy
//                        index
                );
//                brickArray[index++] = brick;
                gameObjects().addGameObject(brick);
            }
        }
    }

    private CollisionStrategy getColisionSrategy() {
        return new BasicCollisionStrategy(this);
//        Random rnd = new Random();
//        if (rnd.nextBoolean()) {
//        }
//        else {
//            int rndInt = rnd.nextInt(5);
//
//            return null;
//        }
    }

    /**
     * This method builds all the hearts for the game.
     */
    private void createLifeCount(ImageReader imageReader) {
        Renderable lifeImg = imageReader.readImage(HEART_IMAGE, true);
        this.lifeCount = new LifeCount(
                new Vector2(5,WINDOW_DIMENSIONS.y()-30),
                lifeImg,
                STARTING_NUMBER_OF_LIVES
        );
        gameObjects().addGameObject(lifeCount,Layer.UI);
    }

    /**
     * This method creates the background.
     */
    private void createBackground(ImageReader imageReader) {
        Renderable backgroundImage = imageReader.readImage(BACKGROUND_IMAGE, false);
        GameObject background = new GameObject(new Vector2(0, 0),new Vector2(WINDOW_DIMENSIONS.x(),
                WINDOW_DIMENSIONS.y()),backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /**
     * This method checks whether the game should end or restart
     */
    private void checkGameEnd(float ballHeight) {
        String prompt = null;
        if(lifeCount.getLives()>0){
            if(ballHeight>WINDOW_DIMENSIONS.y()) {
                lifeCount.decrementLife();
                recenterBall(imageReader,soundReader);
            }
        }
        else {
            prompt = "lose";
        }
        if(Brick.getBrickCounter() == 0 || winButtonPressed()){
            prompt = "win";
        }
        if (prompt != null) {
            prompt = String.format("You %s! Play again?", prompt);
            gameEndPrompt(prompt);
        }
    }

    private boolean winButtonPressed() {
        return inputListener.isKeyPressed(KeyEvent.VK_W);
    }

    /**
     * This method repositions the ball
     */
    private void recenterBall(ImageReader imageReader,SoundReader soundReader) { // maybe it would be better to delete and recreate ?(time complexity ?)
        gameObjects().removeGameObject(ball);
        createBall(imageReader,soundReader);
    }

    /**
     * Shows the desired game end prompt
     */
    private void gameEndPrompt(String prompt) {
        if(windowController.openYesNoDialog(prompt)){
            windowController.resetGame();
        }
        else{
            windowController.closeWindow();
        }
    }

    public void remove(GameObject toRemove) {
        gameObjects().removeGameObject(toRemove);
    }
}
