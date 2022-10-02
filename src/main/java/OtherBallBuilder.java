public class OtherBallBuilder implements BallBuilder{

    private Ball ball;
    private String color;
    private double posX;
    private double posY;
    private double velX;
    private double velY;
    private double mass;

    @Override
    public void setColor() {

        this.color = color;

    }

    @Override
    public void setPosX() {

        this.posX = posX;

    }

    @Override
    public void setPosY() {

        this.posY = posY;


    }

    @Override
    public void setVelX() {

        this.velX = velX;


    }

    @Override
    public void setVelY() {

        this.velY = velY;

    }

    @Override
    public void setMass() {

        this.mass = mass;


    }
}
