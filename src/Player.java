public class Player {
    private static final int VICTORY_CASE = 100;  // Static values
    private static final int STARTING_CASE = 1;

    private String name; // Private values
    private GamePiece piece;
    private Square position;

    public Player(String name, GamePiece piece) {// Constructor of player.
        this.name = name;
        this.piece = piece;
        Square new_position = new Square(1);
        this.position = new_position;
    }

    public String getName() { //This method allows us to get the name of the player.
        return name;
    }

    public GamePiece getPiece() {// This method allows us to get the piece (to check if we could add players...).
        return this.piece;
    }

    public Square getPosition() {// This method allows us to get the position of a player.
        return this.position;
    }

    public void addNewPosition (int dieThrow){ // This method allows us to add a new position by checking
        // the old position and print "the path..."
        if(position.getIndex() + dieThrow > VICTORY_CASE) {
            int x = (position.getIndex() + dieThrow) % VICTORY_CASE;
            System.out.print(" The path to the next square: "+ position.getIndex() + " -> ");
            this.position = new Square(VICTORY_CASE - x);
            System.out.print(this.position.getIndex());
        }
        else if (position.getIndex() + dieThrow < STARTING_CASE){
            System.out.print(" The path to the next square: "+ position.getIndex() +  " -> 1" );
            this.position = new Square(STARTING_CASE);
        }
        else {
            System.out.print(" The path to the next square: "+ position.getIndex() + " -> ");
            this.position = new Square(position.getIndex() + dieThrow);
            System.out.print(this.position.getIndex());
        }
    }
    public void addNewPositionFromSnakeOrLadder (Square newPosition){// This method allows us to print
        // the new position by snake or ladder without "the path" and update the position.
        this.position = newPosition;
        System.out.print(" -> "+ newPosition.getIndex());
    }

    public void changeName(String newName){//This method allows us to change the name of a player
        // in the array (for the alphabetic order).
        this.name = newName;
    }
}
