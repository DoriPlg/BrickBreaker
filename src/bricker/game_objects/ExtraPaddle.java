package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;


public class ExtraPaddle extends Paddle {

    private int collisionCounter;

    public static final String EXTRA_PADDLE = "extra paddle";


    /**
    * Constructor for the ExtraPaddle class
    * @param renderable - the renderable object
    * @param inputListener - the input listener
    * @param windowWidth - the width of the window
    */
    public ExtraPaddle(Renderable renderable, UserInputListener inputListener, float windowWidth)  {
        super(renderable, inputListener, windowWidth);
        this.setTag(EXTRA_PADDLE);
        this.collisionCounter = 0;
    }

    /**
    * Overrides the onCollisionEnter method from the GameObject class
    * if the other object is a Ball, increments the collisionCounter
    * @param other - the other GameObject
    * @param collision - the collision object
    */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision){
        super.onCollisionEnter(other, collision);
        collisionCounter++;
    }

    /**
     *  Overides the shouldCollide method from the GameObject class
    * @param other - the other GameObject
    * @return true if the other GameObject is a Ball, false otherwise
    */
    @Override
    public boolean shouldCollideWith(GameObject other){
        return other instanceof Ball;
    }


    /**
    * Getter for the collisionCounter field
    * @return the collisionCounter field
    */
    public int getCollisionCounter(){
        return collisionCounter;
    }

}
