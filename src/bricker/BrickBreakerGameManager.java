package src.bricker;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.brick_strategies.*;
import src.bricker.game_objects.*;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Random;

/**
 * Manages the Brick Breaker game, including initializing game objects,
 * updating game state, and handling game events.
 */
public class BrickBreakerGameManager extends GameManager {
    private static Vector2 WINDOW_DIMENSIONS ;
    private static float BRICK_ROW_NUMBER = 7;
    private static float BRICK_COL_NUMBER = 8;
    private Ball ball = null;
    private LifeCount lifeCount;
    private Renderable lifeImg;
    private ExtraPaddle extraPaddle;
    private static final int STARTING_NUMBER_OF_LIVES = 3;
    private static Vector2 CENTER = null;
    private static ImageReader imageReader;
    private static SoundReader soundReader;
    private static WindowController windowController;
    private static UserInputListener inputListener;
    private static final String PADDLE = "paddle";
    private static final String EXTRA_PADDLE = "extra paddle";

    private final static int MAX_LIVES_NUMBER = 4;
    private final static int SCREEN_WIDTH = 700;
    private final static int SCREEN_LENGTH = 500;
    private final static Renderable NON_RENDERABLE = null;
    private static final String TURBO_IMAGE = "assets/redball.png";
    private static final String PUCK_IMAGE = "assets/mockBall.png";
    private final static String BALL_IMAGE = "assets/ball.png";
    private final static String PADDLE_IMAGE = "assets/paddle.png";
    private final static String BRICK_IMAGE = "assets/brick.png";
    private final static String HEART_IMAGE = "assets/heart.png";
    private final static String COLLISION_SOUND = "assets/blop.wav";
    private final static String BACKGROUND_IMAGE = "assets/DARK_BG2_small.jpeg";
    private static final String[] strategyArray = {"ball","paddle","turbo","heart","double"};


    //    private static final float BALL_SPEED = 150; // still a problem , should the manager know this ?
    private final HashSet<Puck> puckSet = new HashSet<>();
//    private Brick[] brickArray;



    /**
     * Default Constructor.
     */
    public BrickBreakerGameManager(String windowTitle, Vector2 windowDimensions) {
        super(windowTitle, windowDimensions);
    }


    /**
     * This method intializes the game.
     * @param imageReader - the image reader
     * @param soundReader - the sound reader
     * @param inputListener - the input listener
     * @param windowController - the window controller
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        this.lifeImg = imageReader.readImage(HEART_IMAGE, true);
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        BrickBreakerGameManager.imageReader = imageReader;
        BrickBreakerGameManager.soundReader = soundReader;
        BrickBreakerGameManager.windowController = windowController;
        BrickBreakerGameManager.inputListener = inputListener;
        WINDOW_DIMENSIONS = windowController.getWindowDimensions();
        CENTER = WINDOW_DIMENSIONS.mult((float)0.5);
        windowController.setTargetFramerate(80);
        //ball
        createBall();
        //paddle
        Vector2 center = new Vector2(WINDOW_DIMENSIONS.x()/2, WINDOW_DIMENSIONS.y() - 30);
        createPaddle(center,PADDLE);
        //createBuffers
        createBuffers();
        //create Bricks
        brickBuilder();
        //hearts
        createLifeCount();
        //background
        createBackground();
    }



    /**
     * This method updates the game's objects status .
     * @param deltaTime - the time passed since the last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        float ballHeight = ball.getCenter().y();
        if(this.extraPaddle!=null){
            if(this.extraPaddle.getCollisionCounter()>=4){
                remove(extraPaddle);
            }
        }
        checkPucks();
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
    private void createPaddle(Vector2 center, String type) { //maybe it should be better to separate extra
        Paddle paddle ;
        Renderable paddleImg =
                imageReader.readImage(PADDLE_IMAGE, false);
        if(type.equals(EXTRA_PADDLE)){
            this.extraPaddle = new ExtraPaddle(paddleImg, inputListener, WINDOW_DIMENSIONS.x());
            this.extraPaddle.setCenter(center);
            gameObjects().addGameObject(this.extraPaddle);
        }
        else {
            paddle = new Paddle(paddleImg, inputListener, WINDOW_DIMENSIONS.x());
                 // !!!!!!!!!!!!!!!!!
            paddle.setCenter(center);
            gameObjects().addGameObject(paddle);
        }
    }



    /**
     * This method builds the default ball of the game.
     */
    private void createBall() {
        Renderable ballImg =
                imageReader.readImage(BALL_IMAGE, true);
        Renderable turboImg =
                imageReader.readImage(TURBO_IMAGE, true);
        Sound collisionSound = soundReader.readSound(COLLISION_SOUND);
        Ball ball = new Ball(CENTER, ballImg, turboImg,collisionSound); // !!!!!!!!!!!!!!!! nu e Ball ball
        this.ball = ball;
        ball.setTag("ball");
        gameObjects().addGameObject(ball);
    }

    /**
     * This method builds the walls of the game.
     */
    // could improved with loop and finals for the position of the buffer
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
    private void brickBuilder() {
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
                Brick brick = new Brick(
                        new Vector2(newBrickCoordinate, j * Brick.BRICK_HEIGHT),
                        brickSize,
                        brickImg,
                        getCollisionStrategy()
                );
                gameObjects().addGameObject(brick);
            }
        }
    }


    /**
     * Determines and returns a collision strategy for the game.
     * This method randomly selects between a basic collision strategy
     * and a randomly chosen collision strategy.
     *
     * @return a CollisionStrategy object, either a BasicCollisionStrategy
     *         or a randomly chosen collision strategy.
     */
    private CollisionStrategy getCollisionStrategy() {
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            return new BasicCollisionStrategy(this);
        }
        else {
            return randomCollisionStrategy();
        }
    }

    /**
     * This method returns a random collision strategy for the game.
     * @return a CollisionStrategy object, randomly chosen from the
     *         available collision strategies.
     */
    public CollisionStrategy randomCollisionStrategy() {
        Random rnd = new Random();
        int randInt = rnd.nextInt(5);
        return CollisionFactory.buildCollisionStrategy(strategyArray[randInt],this);
    }

    /**
     * This method builds all the hearts for the game.
     */
    private void createLifeCount() {
        this.lifeCount = new LifeCount(
                new Vector2(5,WINDOW_DIMENSIONS.y()-30),
                this.lifeImg,
                STARTING_NUMBER_OF_LIVES
        );
        gameObjects().addGameObject(lifeCount,Layer.UI);
    }

    /**
     * This method creates the background.
     */
    private void createBackground() {
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
                System.out.println(lifeCount.getLives());
                recenterBall();
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

    /**
     * This method checks if the win button is pressed.
     * @return true if the win button is pressed, false otherwise.
     */
    private boolean winButtonPressed() {
        return inputListener.isKeyPressed(KeyEvent.VK_W);
    }

    /**
     * This method repositions the ball
     */
    private void recenterBall() { // maybe it would be better to delete and recreate ?(time complexity ?)
        gameObjects().removeGameObject(ball);
        createBall();
    }
    // ai metoda remove pt ce e mai sus !!!!!!!!!!!!
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

    /**
     * Removes a game object from the game.
     * @param toRemove - the game object to remove
     * @return true if the object was successfully removed, false otherwise.
     */
    public boolean remove(GameObject toRemove) {
        return gameObjects().removeGameObject(toRemove);
    }


    /**
     * Creates a specified number of pucks and adds them to the game objects.
     * @param numberOfBalls - the number of pucks to create.
     */
    public void makePucks(int numberOfBalls) {
        Vector2 start = windowController.getWindowDimensions().mult(0.5f).
                add(Puck.PUCK_SIZE.mult(-0.5f));
        Renderable puckImg = imageReader.readImage(PUCK_IMAGE, true);
        Sound collisionSound = soundReader.readSound(COLLISION_SOUND);

        for (int i = 0; i < numberOfBalls; i++) {
            Puck puck = new Puck(start, puckImg, collisionSound);
            gameObjects().addGameObject(puck);
            puckSet.add(puck);
        }
    }

    /**
     * Checks the status of the pucks and removes them if they are out of bounds.
     */
    private void checkPucks() {
        for(Puck p : puckSet){
            if (p.getTopLeftCorner().y() < 0){
                puckSet.remove(p);
                gameObjects().removeGameObject(p);
            }
        }
    }

    /**
     * Build a new paddle
     */
    public void makeExtraPaddle(){
        if(this.extraPaddle==null) {
            Vector2 extraPaddleCenter = new Vector2(WINDOW_DIMENSIONS.x() / 2,
                    WINDOW_DIMENSIONS.y() / 2);
            createPaddle(extraPaddleCenter, EXTRA_PADDLE);
        }
    }

    /**
     * Builds a new heart
     */
    public void makeHeart(Vector2 center) {
        if(lifeCount.getLives()<4){
             Heart heart = new Heart(
                    center,new Vector2(20,20),
                    this.lifeImg,this.gameObjects(),this.lifeCount, WINDOW_DIMENSIONS.y());
            heart.setCenter(center);
            gameObjects().addGameObject(heart);
        }
    }
}