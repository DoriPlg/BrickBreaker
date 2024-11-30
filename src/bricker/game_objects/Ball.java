package src.bricker.game_objects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class Ball extends GameObject {
    private int collisions;
    public Ball(int x, int y, ImageReader imageReader) {
        super(new Vector2(x,y), new Vector2(50, 50), null);
        Renderable ballImage =
                imageReader.readImage("assets/assets/ball.png", true);
        this.collisions = 0;
    }

    public void onCollisionEnter(GameObject otherObject, Collision collision) {
        this.collisions++;
    }

    public int getCollisionCounter(){
        return this.collisions;
    }

}
