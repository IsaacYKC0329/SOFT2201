public class UteFactory implements VehicleFactory{

    @Override
    public Vehicle create(String color, int numOfPassengers) {
        return new Ute(color, numOfPassengers);
    }

}

