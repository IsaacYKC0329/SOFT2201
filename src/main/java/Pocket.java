import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Pocket {

    private double r = 10;

    public ArrayList<Circle> pockets = new ArrayList<Circle>();



    public Pocket(Table table) {
        double tWidth = table.bounds.getWidth();
        double tHeight = table.bounds.getHeight();
        double top = table.bounds.getMinY();
        double right = table.bounds.getMaxX();
        double bot = table.bounds.getMaxY();
        double left = table.bounds.getMinX();

        Circle topLeft = new Circle(left,top,r);
        Circle topMid = new Circle(left+0.5*tWidth,top,r);
        Circle topRight = new Circle(right,top,r);
        Circle botLeft = new Circle(left,bot,r);
        Circle botMid = new Circle(left+0.5*tWidth,bot,r);
        Circle botRight = new Circle(right,bot,r);

        pockets.add(topLeft);
        pockets.add(topMid);
        pockets.add(topRight);
        pockets.add(botLeft);
        pockets.add(botMid);
        pockets.add(botRight);

    }

    public <Piant> Pocket(int i, int i1, Piant color) {



    }


    public boolean checkIfPocketed(Ball ball){
        boolean isSunk = false;
        double xDist, yDist;
        for(int i = 0; i < pockets.size(); i++){
                Circle pocket = pockets.get(i);
                xDist = pocket.getCenterX() - ball.getCenterX();
                yDist = pocket.getCenterY() - ball.getCenterY();
                double distSquared = xDist*xDist + yDist*yDist;
                if(distSquared <= ((2*r)*(2*r))/2){
                    isSunk = true;
                }
            }
        return isSunk;
    }

    public Paint getColor(Ball ball){
        Paint color = ball.getFill();
        return color;
    }


}
