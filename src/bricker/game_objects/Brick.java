package src.bricker.game_objects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import src.bricker.brick_strategies.CollisionStrategy;


public class Brick extends GameObject {
//    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 15;
    private static int lastDestroyed = -1;


    private final int index;
    private static int brickCounter = 0;
    private final CollisionStrategy collisionStrategy;

    public Brick(Vector2 position, Vector2 size, Renderable renderable, CollisionStrategy collisionStrategy,int index) {
        super(position, size, renderable);
        this.collisionStrategy = collisionStrategy;
        this.brickCounter++;
        this.index = index;
    }

    public static int getBrickCounter() {
        return brickCounter;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other instanceof Ball) {
            brickCounter--;
            lastDestroyed = index;
        }
    }

    public static int getLastDestroyed() {
        return lastDestroyed;
    }
}

