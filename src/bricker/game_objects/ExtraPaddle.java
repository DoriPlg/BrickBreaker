package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;

// not taking heart  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class ExtraPaddle extends Paddle {

    private int collisionCounter ;
    private static final String EXTRA_PADDLE = "extra paddle";


    /**
     Constructor of the extra Paddle class
     */
    public ExtraPaddle(Renderable renderable, UserInputListener inputListener, float windowWidth)  {
        super(renderable, inputListener, windowWidth);
        this.collisionCounter = 0;
    }

    /**
     Method that helps count the number of collisions
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision){
        super.onCollisionEnter(other, collision);
        this.setTag(EXTRA_PADDLE);
        if(other instanceof Ball ){ // e valabil si puck ???
            this.collisionCounter=this.collisionCounter+1;
        }

    }

    /**
     Method that returns the number of collisions
     */
    public int getCollisionCounter(){
        return this.collisionCounter;
    }

}
