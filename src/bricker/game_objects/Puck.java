package src.bricker.game_objects;

import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Puck extends Ball{

    public Puck(Vector2 startLoc, Renderable renderable, Sound collissionSound) {
        super(startLoc, renderable, null, collissionSound);
    }
}
