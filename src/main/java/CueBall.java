import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Line;



public class CueBall extends Line implements BallBuilder {

    double power = 0;

    public CueBall(Ball cb) {
        super();
        super.setStrokeWidth(5);
        super.setStartX(cb.getCenterX() - 150);
        super.setStartY(cb.getCenterY());
        super.setEndX(cb.getCenterX());
        super.setEndY(cb.getCenterY());
    }

    public void setPower(Line line) {
        Point2D start = new Point2D(line.getStartX(), line.getStartY());
        Point2D end = new Point2D(line.getEndX(), line.getEndY());
        power = start.distance(end) * 3;
        if (power > 900) {
            power = 900;
        }
    }

    void shoot(Ball A, Line B) {
        double x1 = A.getPosX();
        double x2 = B.getEndX();
        double y1 = A.getPosY();
        double y2 = B.getEndY();

        double velX = -(x2 - x1) / 40;
        double velY = -(y2 - y1) / 40;

        if (A.getVelX() == 0 && A.getVelY() == 0) {
            A.setVelX(velX);
            A.setVelY(velY);
        }


    }

    public double getPower() {
        return power;
    }

    @Override
    public void setColor() {

    }

    @Override
    public void setPosX() {

    }

    @Override
    public void setPosY() {

    }

    @Override
    public void setVelX() {

    }

    @Override
    public void setVelY() {

    }

    @Override
    public void setMass() {

    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}







