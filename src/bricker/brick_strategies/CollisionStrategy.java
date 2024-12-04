package src.bricker.brick_strategies;

import danogl.GameObject;

public interface CollisionStrategy {
    boolean onCollision(GameObject thisObj, GameObject otherObj);
}
