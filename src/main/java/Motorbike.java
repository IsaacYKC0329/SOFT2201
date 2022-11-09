public class Motorbike implements Vehicle {
    private final String colour;

    private final int numOfpassengers;

    public Motorbike(String colour, int numOfpassengers) {
        this.colour = colour;
        this.numOfpassengers = 2;
    }

    @Override
    public String toString() {
        return "This is a " + colour + " motorbike.";
    }
}
