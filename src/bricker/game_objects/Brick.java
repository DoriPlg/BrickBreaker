package src.bricker.game_objects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


public class Brick extends GameObject {
    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 30;
    public Brick(Vector2 position, Renderable renderable) {
        super(position, new Vector2(BRICK_WIDTH, BRICK_HEIGHT), renderable);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other instanceof Ball) {
            setTopLeftCorner(new Vector2(-BRICK_WIDTH, -BRICK_HEIGHT));
        }
    }
}
