package src.bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

public class BasicCollisionStrategy implements CollisionStrategy {
    public BasicCollisionStrategy() {}

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj, GameObjectCollection objects) {}
}
