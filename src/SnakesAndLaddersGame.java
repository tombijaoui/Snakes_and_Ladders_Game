public class SnakesAndLaddersGame {
    private static final int VICTORY_CASE = 100;// All the static values of the class.
    private static final int STARTING_CASE = 1;
    private static final int NO_MORE_PLAYERS = 5;
    private static final int MIN_PLAYERS = 2;
    private static final int NO_VICTORY = - 1;
    private static final String END = "end";
    private static final String PLAYER = "player";
    private static final String LADDER = "ladder";
    private static final String SNAKE = "snake";

    private final Die die; // All the private variable that we'll use during our code.
    private Player[] players;
    private Snake[] snakes;
    private Ladder[] ladders;
    private int indexPlayers;
    private int indexSnakes;
    private int indexLadders;


    public SnakesAndLaddersGame(int lowerBound, int upperBound) { // Constructor of snakes and ladders game
        // with a specific die.
        this.die = new Die(lowerBound, upperBound);
        players = new Player[5];
        snakes = new Snake[100];
        ladders = new Ladder[100];
        indexPlayers = 0;
        indexSnakes = 0;
        indexLadders = 0;
    }

    public SnakesAndLaddersGame() {// Constructor of snakes and ladders with the regular die (1,6).
        this.die = new Die();
        players = new Player[5];
        snakes = new Snake[100];
        ladders = new Ladder[100];
        indexPlayers = 0;
        indexSnakes = 0;
        indexLadders = 0;
    }

    public boolean checkSnake(int height, Square head) {// This method allows us to check if we could add a snake,
        // in this square.
        if (head.getIndex() == VICTORY_CASE) {
            System.out.println("You cannot add a snake in the last square!\n");
            return false;
        }
        if (head.getIndex() > VICTORY_CASE || head.getIndex() < STARTING_CASE) {
            System.out.println("The square is not within the board's boundaries!");
            return false;
        }
        for (int i = 0; i < indexSnakes; i++) {
            if (snakes[i].getHead().getIndex() == head.getIndex()) {
                System.out.println("This square already contains a head of a snake!\n");
                return false;
            }}

        for (int i = 0; i < indexLadders; i++) {
            if (ladders[i].getBottom().getIndex()== head.getIndex()) {
                System.out.println("This square contains a bottom of a ladder!");
                return false;
            }}

        if (head.getIndex() - height < STARTING_CASE) {
            System.out.println("The snake is too long!");
            return false;
        }
        return true;
    }

    public void addSnake(int height,Square head){// This method allows us to check if we could add a snake and
        // add a snake to our array of snakes if ti is possible.
        if(checkSnake(height,head)) {
            Snake newSnake = new Snake(height , head);
            snakes[indexSnakes]=newSnake;
            this.indexSnakes++;
        }
    }

    public boolean checkLadder(int height, Square bottom) {// This method allows us to check if we could add a
        // ladder in this square.
        if(bottom.getIndex() > VICTORY_CASE ||bottom.getIndex() < STARTING_CASE) {
            System.out.println("The square is not within the board's boundaries!");
            return false;
        }

        if((bottom.getIndex() + height) > VICTORY_CASE) {
            System.out.println("The ladder is too long!");
            return false;
        }

        for (int i = 0; i < indexLadders; i++) {
            if (ladders[i].getBottom().getIndex()== bottom.getIndex()) {
                System.out.println("This square already contains a bottom of a ladder!");
                return false;
            }}

        for (int i = 0; i < indexSnakes; i++) {
            if (snakes[i].getHead().getIndex()== bottom.getIndex()) {
                System.out.println("This square contains a head of a snake!");
                return false;
            }}
        return true;
    }

    public void addLadder( int height, Square bottom ){//This method allows us to check if we could add a ladder and
        // add the ladder in our array of ladders if it is possible.
        if(checkLadder(height,bottom)) {
            Ladder newLadder = new Ladder(height,bottom);
            ladders[indexLadders]=newLadder;
            this.indexLadders++;
        }
    }

    public boolean checkPlayer(String name ,GamePiece gamePiece) {//This method allows us to check if we could add
        // a player (with a specific game piece).
        if(indexPlayers == NO_MORE_PLAYERS) {
            System.out.println("The maximal number of players is five!");
            return false;
        }
        boolean checkPiece = true;
        boolean checkName = true;

        for (int i = 0; i < indexPlayers; i++) {
            if(players[i].getName().equals(name)) {
                checkName = false;
            }
            if(players[i].getPiece().getColor().equals(gamePiece.getColor())) {
                checkPiece = false;
            }
        }
        if(!checkPiece && !checkName) {
            System.out.println("The name and the color are already taken!");
            return false;}

        if(!checkPiece) {
            System.out.println("The color is already taken!");
            return false;}

        if(!checkName) {
            System.out.println("The name is already taken!");
            return false;
        }
        return true;
    }

    public void addPlayer(String name , GamePiece gamePiece){//This method allows us to check if we could add a player
        // and add a player in our array of players if it is possible.
        if(checkPlayer(name, gamePiece)) {
            Player newPlayer = new Player(name,gamePiece);
            players[indexPlayers] = newPlayer;
            this.indexPlayers++;
        }
    }

    public void initializeGame(){ // This method allows us to initialize the game : while the user don't write
        // "end" we check/enter all the object that the user want to add (and at the end we sort our array
        // in an alphabetic order).
        System.out.println("Initializing the game...");
        String addSomething = "";
        while(!addSomething.equals(END)|| indexPlayers < MIN_PLAYERS)
        {addSomething = Main.scanner.nextLine();

            if(addSomething.equals(END) && indexPlayers < MIN_PLAYERS)
            {System.out.println("Cannot start the game, there are less than two players!");
                continue;
            }

            if(addSomething.contains(PLAYER))
            {
                String[] arrOfStr = addSomething.split(" ");
                Color color = Enum.valueOf(Color.class, arrOfStr[3]);
                GamePiece piece = new GamePiece(color);
                addPlayer(arrOfStr[2],piece);
            }
            if(addSomething.contains(LADDER))
            {
                String[] arrOfStr = addSomething.split(" ");
                int resultBottom = Integer.parseInt(arrOfStr[3]);
                int resultHeight = Integer.parseInt(arrOfStr[2]);
                Square square = new Square(resultBottom);
                addLadder(resultHeight,square);
            }
            if(addSomething.contains(SNAKE))
            {
                String[] arr_of_str = addSomething.split(" ");
                int resultHeight = Integer.parseInt(arr_of_str[2]);
                int resultHead = Integer.parseInt(arr_of_str[3]);
                Square square = new Square(resultHead);
                addSnake(resultHeight, square);
            }
        }
        toSortArray();
    }

    public int compareStrings(String word1, String word2){// This method allows us to compare two strings
        // (we use that to order the array of players in an alphabetic order)
        for(int i = 0; i < Math.min(word1.length(), word2.length()); i++)
        {
            if((int)word1.charAt(i) != (int)word2.charAt(i))
                return (int)word1.charAt(i) - (int)word2.charAt(i);
        }
        if(word1.length() != word2.length())
            return word1.length() - word2.length();
        else
            return 0;
    }

    public String[] stringArraySort(String[] words) {// This method allows us,thanks to "compareStrings",
        // to compare an array of strings and return the array with the good order.
        for(int i = 0; i < indexPlayers - 1; i++) {
            for(int j = i + 1; j < indexPlayers; j++) {
                if(compareStrings(words[i], words[j]) > 0)
                {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
        return words;
    }

    public void toSortArray(){// This method allows us to sort the array in the alphabetic order.
        String[] arrayPlayersName = new String[5];
        for (int i = 0; i < indexPlayers; i++) {
            arrayPlayersName[i] = players[i].getName();
        }
        String[] sortedArr = stringArraySort(arrayPlayersName);
        for (int i = 0; i < indexPlayers; i++) {
            players[i].changeName(sortedArr[i]);
        }
    }

    public int checkVictory (){// This method allows us to check if a player is in case 100 and to return his index
        // if he's on it.
        for (int i = 0; i < indexPlayers; i++) {
            if(players[i].getPosition().getIndex() == VICTORY_CASE) {
                return i;
            }
        }
        return NO_VICTORY;
    }

    public void sumUpPlayers() { // This method allows us to print the position of all the players.
        System.out.println("\nPlayers positions on the board:");
        for(int i = 0; i< indexPlayers; i++) {
            System.out.println(players[i].getName() + " is in square number "+ players[i].getPosition().getIndex());
        }
    }

    public String start(){//This method allows us to start the game : while anybody doesn't win, it continues
        // until someone is on the final case and it breaks the while loop.
        int round = 1;
        GameBoard gameBoard = new GameBoard(ladders, indexLadders,snakes, indexSnakes);
        while (checkVictory() == NO_VICTORY)
        {
            System.out.println("------------------------- Round number " + (round) + " -------------------------");
            for(int i = 0; i < indexPlayers; i++) {
                int dieThrow = die.Roll();
                System.out.print(players[i].getName() + " rolled " + dieThrow + ".");
                players[i].addNewPosition(dieThrow);
                while(players[i].getPosition().getIndex() != gameBoard.nextSquare(players[i].getPosition()).getIndex())
                {
                    players[i].addNewPositionFromSnakeOrLadder(gameBoard.nextSquare(players[i].getPosition()));
                }
                System.out.print("\n");
                if(checkVictory() != NO_VICTORY)
                {
                    break;
                }
            }
            round++;
            sumUpPlayers();
        }
        return players[checkVictory()].getName();
    }
}


