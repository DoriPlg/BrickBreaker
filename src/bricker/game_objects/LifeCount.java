package src.bricker.game_objects;
import danogl.GameObject;
import danogl.util.Vector2;

public class LifeCount extends GameObject {
    private static final int INITIAL_LIVES = 5;
    private int lives;

    public LifeCount(int x, int y) {
        super(new Vector2(x, y), new Vector2(0,0), null);
        lives = INITIAL_LIVES;
    }

}
