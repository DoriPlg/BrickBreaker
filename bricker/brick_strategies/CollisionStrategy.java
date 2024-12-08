package bricker.brick_strategies;

import danogl.GameObject;

public interface CollisionStrategy {
    /**
     * This method is called when a collision is detected between two objects.
     * @param thisObj The object that is calling the method, to which the strategy is assigned.
     * @param otherObj The object that thisObj collided with.
     */
    void onCollision(GameObject thisObj, GameObject otherObj);


}
