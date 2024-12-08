package bricker.brick_strategies;

import danogl.GameObject;
import bricker.main.BrickerGameManager;

public class DoubleCollisionStartegy extends BasicCollisionStrategy implements CollisionStrategy {


    private static final int UPPER_LIMIT_STRATEGIES = 3 ;

    private final BrickerGameManager gameManager;
    private final CollisionStrategy[] collisionStrategies;

    /**
     * Constructor for the DoubleCollisionStartegy
     * Should generate the different strategies that will be used
     * @param gameManager the game manager that will be used in the strategies
     */
    public DoubleCollisionStartegy(BrickerGameManager gameManager){
        super(gameManager);
        this.gameManager = gameManager;
        this.collisionStrategies = new CollisionStrategy[UPPER_LIMIT_STRATEGIES];
        int i = 0;
        int j = 2;
        while(i<j && i<UPPER_LIMIT_STRATEGIES){
            CollisionStrategy strategy = gameManager.randomCollisionStrategy();
            if(strategy instanceof DoubleCollisionStartegy){
                j++;
            }
            else{
                collisionStrategies[i]=strategy;
                i++;
            }
        }
    }


    /**
     * On collision for the DoubleCollisionStartegy
     * Should call the on collision of each strategy
     * @param thisObj the object that will be used in the on collision
     * @param otherObj the other object that will be used in the on collision
     * @return true if the collision was successful, false otherwise
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj){
        super.onCollision(thisObj,otherObj);
        for(CollisionStrategy strategy : collisionStrategies){
            if(strategy!=null){
                strategy.onCollision(thisObj,otherObj);
            }
        }
    }
}
