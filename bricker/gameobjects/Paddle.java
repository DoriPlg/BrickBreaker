package bricker.gameobjects;
import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

public class Paddle extends GameObject {
    private static final int START_PADDLE_WIDTH = 200;
    private static final int START_PADDLE_HEIGHT = 20;
    private static final int START_PADDLE_POS_Y = START_PADDLE_HEIGHT+1;
    private static final int START_PADDLE_POS_X = 0;
    private static final int MOVEMENT_SPEED = 300;
    private final UserInputListener inputListener;
    private final float windowWidth;

    public static final String PADDLE = "paddle";


    /**
     * Constructor for the Paddle class
     * @param renderable - the renderable object
     * @param inputListener - the input listener
     * @param windowWidth - the width of the window
     */
    public Paddle(Renderable renderable,
                  UserInputListener inputListener,
                  float windowWidth) {
        super(
                new Vector2(START_PADDLE_POS_X,START_PADDLE_POS_Y),
                new Vector2(START_PADDLE_WIDTH, START_PADDLE_HEIGHT),
                renderable
        );
        this.setTag(PADDLE);
        this.inputListener = inputListener;
        this.windowWidth = windowWidth;
    }


    /**
     * Updates the paddle's position based on the user input
     * @param deltaTime - the time since the last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 moveDir = Vector2.ZERO;
        if(inputListener.isKeyPressed(KeyEvent.VK_LEFT)){
            moveDir = moveDir.add(Vector2.LEFT);
        }
        if(inputListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            moveDir = moveDir.add(Vector2.RIGHT);
        }
        if(this.getTopLeftCorner().x() <= 0){
            moveDir = Vector2.ZERO;
            setTopLeftCorner(getTopLeftCorner().add(Vector2.RIGHT));
        } else if (this.getTopLeftCorner().x() + this.getDimensions().x() >= windowWidth) {
            moveDir = Vector2.ZERO;
            setTopLeftCorner(getTopLeftCorner().subtract(Vector2.RIGHT));
        }
        setVelocity(moveDir.mult(MOVEMENT_SPEED));
    }
}
