public class MotorBikeFactory implements VehicleFactory{

    @Override
    public Vehicle create(String color, int numOfPassengers) {
        return new Motorbike(color, numOfPassengers);
    }


}
