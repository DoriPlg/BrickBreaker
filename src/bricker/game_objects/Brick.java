package src.bricker.game_objects;
import danogl.GameObject;
import danogl.util.Vector2;


public class Brick extends GameObject {
    private static final int BRICK_WIDTH = 10;
    private static final int BRICK_HEIGHT = 5;
    public Brick(int x, int y) {
        super(new Vector2(x, y), new Vector2(BRICK_WIDTH, BRICK_HEIGHT), null);
    }

    public void onCollisionEnter(GameObject other) {
    }
}
