public class Scooter implements Vehicle {
    private final String colour;
    private final int numOfpassengers;

    public Scooter(String colour, int numOfpassengers) {
        this.colour = colour;
        this.numOfpassengers = numOfpassengers;
    }

    @Override
    public String toString() {
        return "This is a " + colour + " scooter.";
    }
}
