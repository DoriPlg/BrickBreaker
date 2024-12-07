package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.BrickBreakerGameManager;

public class DoubleCollisionStartegy extends BasicCollisionStrategy implements CollisionStrategy {


    private static final int UPPER_LIMIT_STRATEGIES = 3 ;

    private final BrickBreakerGameManager gameManager;
    private final CollisionStrategy[] collisionStrategies;

    /**
     * Constructor for the DoubleCollisionStartegy
     * Should generate the different strategies that will be used
     * @param gameManager the game manager that will be used in the strategies
     */
    public DoubleCollisionStartegy(BrickBreakerGameManager gameManager){
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
     * Special action for the DoubleCollisionStartegy
     * Should call the special action of each strategy
     * @param thisObj the object that will be used in the special action
     * @param otherObj the other object that will be used in the special action
     */
    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        for(CollisionStrategy strategy : collisionStrategies){
            if(strategy!=null){
                strategy.specialAction(thisObj,otherObj);
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
    public boolean onCollision(GameObject thisObj, GameObject otherObj){
        if(super.onCollision(thisObj,otherObj)){
            specialAction(thisObj, otherObj);
            return true;
        }
        return false;
    }
}
