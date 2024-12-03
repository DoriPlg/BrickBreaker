package src.bricker.brick_strategies;

public class CollisionFactory {

    private static final String EXTRA_BALL = "ball";
    private static final String EXTRA_PADDLE = "paddle";
    private static final String HEART = "heart";
    private static final String TURBO = "turbo";
    private static final String DOUBLE = "double";

    /**
     * builds players according to the input
     */
    public static CollisionStrategy buildCollisionStrategy(String type){
        CollisionStrategy collisionStrategy;
        switch(type){
            case EXTRA_BALL:
                collisionStrategy = new MoreBallsCollisionStrategy();
                break;
            case EXTRA_PADDLE:
                collisionStrategy = new MorePaddleCollisionStrategy();
                break;
            case HEART:
                collisionStrategy = new HeartCollisionStartegy();
                break;
            case DOUBLE:
                collisionStrategy = new DoubleCollisionStartegy();
                break;
            default:
                collisionStrategy = null;
                break;
        }
    return collisionStrategy;
    }
}
