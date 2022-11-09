public class ElectricCarFactory implements VehicleFactory{

    @Override
    public Vehicle create(String color, int numOfPassengers) {
        return new ElectricCar(numOfPassengers, color);
    }

}
