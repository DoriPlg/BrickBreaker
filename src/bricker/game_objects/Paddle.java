package src.bricker.game_objects;
import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Paddle extends GameObject {
    private static final int START_PADDLE_WIDTH = 200;
    private static final int START_PADDLE_HEIGHT = 20;
    private static final int START_PADDLE_POS_Y = START_PADDLE_HEIGHT+1;
    private static final int START_PADDLE_POS_X = 0;
    public Paddle(Renderable renderable) {
        super(
                new Vector2(START_PADDLE_POS_X,START_PADDLE_POS_Y),
                new Vector2(START_PADDLE_WIDTH, START_PADDLE_HEIGHT),
                renderable
        );
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
