package src.bricker.brick_strategies;

import danogl.GameObject;

public interface CollisionStrategy {
    /**
     * This method is called when a collision is detected between two objects.
     * @param thisObj The object that is calling the method, to which the strategy is assigned.
     * @param otherObj The object that thisObj collided with.
     * @return True if the collision resulted in the destruction of the object, false otherwise.
     */
    boolean onCollision(GameObject thisObj, GameObject otherObj);

    /**
     * This method is called to activate a special action when a collision is detected between two objects.
     * @param thisObj The object that is calling the method, to which the strategy is assigned.
     * @param otherObj The object that thisObj collided with.
     */
    void specialAction(GameObject thisObj, GameObject otherObj);
}
