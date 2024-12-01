package src.bricker.game_objects;

import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class FauxBall extends Ball{

    public FauxBall(Vector2 startLoc, Renderable renderable) {
        super(startLoc, renderable, null);
    }
}
