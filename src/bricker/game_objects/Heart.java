package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Heart extends GameObject {

    private static final float HEART_SPEED = 100;
    private static final float HEART_SPEED_X = 0;

    private LifeCount lifeCount;
    private float windowUpperLimit;
    private GameObjectCollection gameObjects;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     *                      the GameObject will not be rendered.
     */
    public Heart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                 GameObjectCollection gameObjects,
                 LifeCount lifeCount, float windowUpperLimit) {
        super(topLeftCorner, dimensions, renderable);
        this.gameObjects = gameObjects;
        this.lifeCount = lifeCount;
        this.windowUpperLimit = windowUpperLimit;
        this.setVelocity(new Vector2(HEART_SPEED_X,HEART_SPEED));
    }

    /**
     * Defines the objects that the heart should collide with
     */
    @Override
    public boolean shouldCollideWith(GameObject other){
        if(other.getTag().equals(Paddle.PADDLE)){
            return true;
        }
        return false;
    }

    /**
     * Defines the objects behaviour on collision
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        lifeCount.incrementLife();
        gameObjects.removeGameObject(this);
    }

    /**
     * Updates the state of the heart with each frame
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(this.getTopLeftCorner().y()>windowUpperLimit){
            gameObjects.removeGameObject(this);
        }
    }
}
