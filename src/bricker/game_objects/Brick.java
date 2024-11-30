package src.bricker.game_objects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


public class Brick extends GameObject {
    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 30;
    private final GameObjectCollection allObjects;

    public Brick(Vector2 position, Renderable renderable, GameObjectCollection allObjects) {
        super(position, new Vector2(BRICK_WIDTH, BRICK_HEIGHT), renderable);
        this.allObjects = allObjects;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other instanceof Ball) {
            allObjects.removeGameObject(this);
        }
    }
}
