import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Table extends Rectangle {
    private Color color;
    private double friction;

    public Bounds bounds;


    public Table() {
        this.color = getColor();
        this.friction = getFriction();
        this.bounds = getBoundsInLocal();
        ArrayList<Object> pockets = new ArrayList<>();
        Pocket topLeft = new Pocket(0,0, Paint.valueOf("black"));
        Pocket topMid = new Pocket(0,0,Color.BLACK);
        Pocket topRight = new Pocket(0,0,Color.BLACK);
        Pocket botLeft = new Pocket(0,0,Color.BLACK);
        Pocket botMid = new Pocket(0,0,Color.BLACK);
        Pocket botRight = new Pocket(0,0,Color.BLACK);
        pockets.add(topLeft);
        pockets.add(topMid);
        pockets.add(topRight);
        pockets.add(botLeft);
        pockets.add(botMid);
        pockets.add(botRight);

    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.setFill(color);
    }

    public double getFriction() {
        return friction;
    }



    public void setFriction(double friction) {
        double offset;
        if (friction <1){
            offset = 1-(friction/100-0.003) ;
        }

        double d = 0.993;

        this.friction = d;

    }


}
