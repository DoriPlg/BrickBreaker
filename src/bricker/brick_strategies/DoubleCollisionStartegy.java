package src.bricker.brick_strategies;

import danogl.GameObject;
import src.bricker.BrickBreakerGameManager;

public class DoubleCollisionStartegy extends BasicCollisionStrategy implements CollisionStrategy {


    private static final int UPPER_LIMIT_STRATEGIES = 3 ;

    private final BrickBreakerGameManager gameManager;
    private final CollisionStrategy[] collisionStrategies;

    /**
     * constructor
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

    @Override
    public void specialAction(GameObject thisObj, GameObject otherObj) {
        for(CollisionStrategy strategy : collisionStrategies){
            if(strategy!=null){
                strategy.specialAction(thisObj,otherObj);
            }
        }
    }

    @Override
    public boolean onCollision(GameObject thisObj, GameObject otherObj){
        if(super.onCollision(thisObj,otherObj)){
            specialAction(thisObj, otherObj);
            return true;
        }
        return false;
    }
}
