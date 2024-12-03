package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

public interface CollisionStrategy {
    public void  onCollision(GameObject thisObj, GameObject otherObj, GameObjectCollection objects);
}
