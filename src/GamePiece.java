public class GamePiece {
    private Color color;

    public GamePiece (Color colorPiece){ //Constructor of game piece.
        this.color = colorPiece;
    }

    public Color getColor(){//This method returns the color of the game piece.
        return color;
    }
}