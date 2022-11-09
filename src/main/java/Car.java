public class Car implements Vehicle {
    private final String colour;
    private final int numPassengers;

    public Car(int numPassengers, String colour) {
        this.numPassengers = numPassengers;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "This is a " + colour + " car that can seat " + numPassengers + " passengers.";
    }
}
