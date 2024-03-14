public class GameBoard {
    private static final int SIZE_BOARD = 100;
    private Square[] board;

    public GameBoard(Ladder ladders[],int indexLadders ,Snake snakes[], int indexSnakes){//constructor : it's a
        // histogram which depends on the ladders and snakes, the value of GameBoard[i]= to the next square
        // after i : if there is no ladder and no snakes on i : GameBoard[i] = i
        // else GameBoard[i] = the top of the ladders / the tail of the snake (in square).
        board = new Square[SIZE_BOARD + 1];
        for(int i = 1; i <= SIZE_BOARD; i++) {
            this.board[i]= new Square(i);
            for(int y = 0;y < indexLadders;y++) {
                if(i==ladders[y].getBottom().getIndex()) {
                    this.board[i]=ladders[y].nextSquare();
                }
            }
            for(int y = 0; y < indexSnakes; y++)
            {
                if(i==snakes[y].getHead().getIndex())
                {
                    this.board[i]=snakes[y].destination();
                }
            }
        }
    }

    public Square nextSquare(Square position){  //This method returns the next Square after the square 'position'.
        return board[position.getIndex()];
    }
}