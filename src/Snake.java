public class Snake {
    private final int height; // Private values of each snake.
    private final Square head;

    public Snake(int height, Square head){ // Constructor of snake.
        this.height = height;
        this.head = head;
    }
    public Square getHead (){// This method allows us to get the position(square) of the head.
        return this.head;
    }

    public Square destination() {// This method allows us to get the position(square) of a player after he used a snake.
        return new Square(head.getIndex()-height);
    }
}
