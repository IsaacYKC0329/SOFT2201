import javafx.geometry.Point2D;

import java.util.ArrayList;

public class BlueBallStrategy extends BallStrategy{



    private double radius = 10;

    @Override
    public void handleCollisions(ArrayList<Ball> balls){
        double xDist, yDist;
        for(int i = 0; i < balls.size(); i++){
            Ball A = balls.get(i);
            for(int j = i+1; j < balls.size(); j++){
                Ball B = balls.get(j);
                xDist = A.getCenterX() - B.getCenterX();
                yDist = A.getCenterY() - B.getCenterY();
                double distSquared = xDist*xDist + yDist*yDist;
                if(distSquared <= (2*radius)*(2*radius)){
                    Point2D positionA = new Point2D(A.getPosX(), A.getPosY());
                    Point2D velocityA = new Point2D(A.getVelX(), A.getVelY());
                    Point2D positionB = new Point2D(B.getPosX(), B.getPosY());
                    Point2D velocityB = new Point2D(B.getVelX(), B.getVelY());
                    double massA = A.getMass();
                    double massB = B.getMass();

                    Point2D collisionVector = positionA.subtract(positionB);
                    collisionVector = collisionVector.normalize();

                    double vA = collisionVector.dotProduct(velocityA);
                    double vB = collisionVector.dotProduct(velocityB);

                    if (vB <= 0 && vA >= 0) {
                        velocityA = new Point2D(A.getVelX(), A.getVelY());
                        velocityB = new Point2D(B.getVelX(), B.getVelY());

                    }


                    double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

                    Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
                    Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

                    double newAVelX = velAPrime.getX();
                    double newAVelY = velAPrime.getY();
                    double newBVelX = velBPrime.getX();
                    double newBVelY = velBPrime.getY();

                    A.setVelX(newAVelX);
                    A.setVelY(newAVelY);
                    B.setVelX(newBVelX);
                    B.setVelY(newBVelY);

                }
            }
        }
    }
}
