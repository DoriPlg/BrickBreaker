package bricker.gameobjects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import bricker.brick_strategies.CollisionStrategy;


public class Brick extends GameObject {
    public static final int BRICK_HEIGHT = 15;
    private static final String EXISTS = "EXISTS";
    public static final String POPPED = "POPPED";

    private static int brickCounter = 0;
    private final CollisionStrategy collisionStrategy;


    /**
     * Constructor for the Brick class
     * @param position the position of the brick
     * @param size the size of the brick
     * @param renderable the renderable for the brick
     * @param collisionStrategy the collision strategy for the brick
     */
    public Brick(Vector2 position, Vector2 size, Renderable renderable, CollisionStrategy collisionStrategy) {
        super(position, size, renderable);
        this.collisionStrategy = collisionStrategy;
        this.brickCounter++;
        setTag(EXISTS);
    }


    /**
     * Getter for the brick counter
     * @return the number of bricks
     */
    public static int getBrickCounter() {
        return brickCounter;
    }



    /**
     * Dictates that a brick should only collide with the ball
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other instanceof Ball;
    }


    /**
     * Handles the collision of the brick with another game object,
     * and decreases the brick counter if the collision is successful
     * (i.e. the brick is destroyed)
     * @param other the other game object
     * @param collision the collision that occurred
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (this.getTag().equals(EXISTS)) {
            brickCounter--;
        }
        collisionStrategy.onCollision(this, other);
    }
}