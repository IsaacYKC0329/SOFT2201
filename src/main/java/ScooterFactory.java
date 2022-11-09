public class ScooterFactory implements VehicleFactory{

    @Override
    public Vehicle create(String color, int numOfPassengers) {
        return new Scooter(color, numOfPassengers);
    }

}
