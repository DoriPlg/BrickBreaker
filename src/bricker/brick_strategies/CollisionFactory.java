package src.bricker.brick_strategies;

import src.bricker.BrickBreakerGameManager;
public class CollisionFactory {

    private static final String EXTRA_BALL = "ball";
    private static final String EXTRA_PADDLE = "paddle";
    private static final String HEART = "heart";
    private static final String TURBO = "turbo";
    private static final String DOUBLE = "double";
    public static final String[] TYPES = {EXTRA_BALL, EXTRA_PADDLE, HEART, TURBO, DOUBLE};

    /**
     * builds players according to the input
     * @param type the type of the strategy to be built
     * @param gameManager the game manager to be used in the strategy
     */
    public static CollisionStrategy buildCollisionStrategy(String type, BrickBreakerGameManager gameManager){
        CollisionStrategy collisionStrategy;
        switch(type){
            case EXTRA_BALL:
                collisionStrategy = new MoreBallsCollisionStrategy(gameManager);
                break;
            case EXTRA_PADDLE:
                collisionStrategy = new MorePaddleCollisionStrategy(gameManager);
                break;
            case HEART:
                collisionStrategy = new HeartCollisionStartegy(gameManager);
                break;
            case DOUBLE:
                collisionStrategy = new DoubleCollisionStartegy(gameManager);
                break;
            case TURBO:
                collisionStrategy = new TurboCollisionStrategy(gameManager);
                break;
            default:
                collisionStrategy = null;
                break;
        }  // hint
    return collisionStrategy;
    }
}
