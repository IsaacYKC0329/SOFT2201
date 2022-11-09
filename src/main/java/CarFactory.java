public class CarFactory implements VehicleFactory{

    @Override
    public Vehicle create(String color, int numOfPassengers) {
        return new Car(numOfPassengers, color);
    }

}
