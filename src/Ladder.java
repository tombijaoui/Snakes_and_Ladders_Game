public class Ladder {
    private final Square bottom;// The private values of each ladder.
    private final int height;

    public Ladder(int height,Square bottom){ // Constructor of ladder.
        this.bottom = bottom;
        this.height = height;
    }
    public Square getBottom(){// This method allows us to get the bottom of the ladder (square).
        return this.bottom;
    }

    public Square nextSquare(){// This method allows us to get the position (square) of a player after
        // he used the ladder.
        return new Square(bottom.getIndex() + height);
    }
}

