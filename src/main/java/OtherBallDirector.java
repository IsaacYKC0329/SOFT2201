public class OtherBallDirector {

    private OtherBallBuilder ballBuilder;

    public OtherBallDirector(OtherBallBuilder ballBuilder){
        this.ballBuilder = ballBuilder;
    }

    public void constructBall(){
        ballBuilder.setColor();
        ballBuilder.setPosX();
        ballBuilder.setPosY();
        ballBuilder.setVelX();
        ballBuilder.setVelY();
        ballBuilder.setMass();
    }

}
