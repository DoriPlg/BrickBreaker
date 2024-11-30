package src.bricker.game_objects;
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

    public Paddle(Renderable renderable, UserInputListener inputListener) {
        super(
                new Vector2(START_PADDLE_POS_X,START_PADDLE_POS_Y),
                new Vector2(START_PADDLE_WIDTH, START_PADDLE_HEIGHT),
                renderable
        );
        this.inputListener = inputListener;
    }

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
        setVelocity(moveDir.mult(MOVEMENT_SPEED));
    }
}
