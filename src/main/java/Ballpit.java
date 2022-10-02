import javafx.geometry.Point2D;

import java.util.ArrayList;

import static java.lang.Math.abs;


public class Ballpit {

    private double radius = 10;


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




    public boolean checkWallCollisionX(Ball ball, Table table) {
        double left = table.bounds.getMinX();
        double right = table.bounds.getMaxX();
        if (abs(right - ball.getPosX()) <= radius || abs( ball.getPosX()-left) <= radius) {

            return true;
        }
        else
            return false;
    }

    public boolean checkWallCollisionY(Ball ball, Table table) {
        double top = table.bounds.getMinY();
        double bot = table.bounds.getMaxY();
        if (abs(bot - ball.getPosY()) <= radius || abs(ball.getPosY() - top) <= radius) {

            return true;
        }
        else
            return false;
    }


    }



