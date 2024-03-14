public class Die {
    private int lowerBound; // The private values of the die (maximum value, and minimum value)
    private int upperBound;

    public Die(int lowerBound, int upperBound){ // Constructor of die with the specific max and min values.
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Die(){//  Constructor of die with regular value (1,6).
        this(1, 6);
    }

    public int Roll(){//  This method allows us to pick a number between the lower and the upper bound.
        return (Main.rnd.nextInt(upperBound - lowerBound+1)+lowerBound);
    }
}
