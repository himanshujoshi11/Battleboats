//Written by joshi271
public class Board {
    private int num_rows;
    private int num_columns;
    private int num_boats;
    private Battleboat boats[];
    private Cell board[][];
    private boolean debugMode;
    private Cell[] temp;


    // TODO: Place Battleboats appropriately on board and add them to the board's boats

    //Board constructor which creates a board of m by n and decides how many battleboats on the board based on how big it is.
    //Initializes battleboat and a 2D board array.
    public Board(int m , int n, boolean debugMode) {
        this.num_rows = m;
        this.num_columns = n;
        this.debugMode = debugMode;
        if (m == 3 || n == 3) {
            num_boats = 1;
        } else if (m > 3 && m <= 5 || n > 3 && n <= 5) {
            num_boats = 2;
        } else if (m > 5 && m <= 7 || n > 5 && n <= 7) {
            num_boats = 3;
        } else if (m > 7 && m <= 9 || n > 7 && n <= 9) {
            num_boats = 4;
        } else if (m > 9 && m <= 12 || n > 9 && n <= 12) {
            num_boats = 6;
        }
        boats = new Battleboat[num_boats];
        board = new Cell[m][n];
        // Creating a cell object for every space in board and adding them to the list
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Cell cellObject = new Cell(i, j, ' ');
                board[i][j] = cellObject;
            }
        }
        int a = 0;

// Creates and Sets battleboats onto the board.
        while (a < num_boats) {
            Battleboat new_boat = new Battleboat();
            double random1 = (m) * Math.random();
            double random2 = (n) * Math.random();
            int x = (int) (Math.floor(random1));
            int y = (int) (Math.floor(random2));
            int space_counter = 0;
            if (new_boat.get_orientation() == false) {    //Horizontal Boat
                for (int b = 0; b < 3; b++) {
                    if (x + 2 < m) {
                        if (board[x + b][y].get_status() == ' ') {
                            space_counter++;
                        }
                    }
                }
                if (space_counter == 3) { // condition to make sure there are no other boats in that location

                    for (int b = 0; b < 3; b++) { //finalizes and adds the cell objects to all the lists
                        board[x + b][y].set_status('B');
                        new_boat.set_spaces(b, board[x + b][y]);
                        boats[a] = new_boat;
                    }
                    a++;
                }
            }
            else if (new_boat.get_orientation() == true) {   //Vertical boat
                for (int b = 0; b < 3; b++) {
                    if (y + 2 < n) {
                        if (board[x][y + b].get_status() == ' ') {
                            space_counter++;
                        }
                    }
                }
                if (space_counter == 3) {
                    for (int b = 0; b < 3; b++) {
                        board[x][y + b].set_status('B');
                        new_boat.set_spaces(b, board[x][y + b]);
                        boats[a] = new_boat;
                    }
                    a++;
                }

            }
        }
    }


    //Obscures a character if the game is not being played in debug mode
    private char debug(boolean debugMode, char c){
        if(debugMode){
            return c;
        }
        else{
            switch(c){
                case 'H':
                    c = 'H';
                    break;
                case 'M':
                    c = 'M';
                    break;
                default:
                    c = ' ';
                    break;
            }
            return c;
        }
    }

    //Prints a Board object in a way that makes sense to the player
    public String toString(){

        String boardString = "\t";
        for (int j = 0; j < num_columns-1; j++){
            boardString += j + " |" + "\t";
        }

        boardString += num_columns-1;

        for(int i = 0; i < num_rows; i++){
            boardString+= "\n" + i + "\t";
            for (int j = 0; j < num_columns; j++){
                boardString += debug(debugMode, board[i][j].get_status()) + "\t";
            }
        }

        boardString += "\n";
        return boardString;
    }

    //Takes a user's guess and returns result. Changes status to appropritate value if applicable.
    public int guess(int r, int c){
        if ((r<0||r>=this.num_rows) || (c<0||c>=this.num_columns)){
            return 0;
            //"Penalty: Out of Bounds";
        }
        else if (board[r][c].get_status()== ' ') {
            board[r][c].set_status('M');
            return 1;
            //"Miss";
        }
        else if(board[r][c].get_status()== 'B'){
            board[r][c].set_status('H');
            return 2;
            //"Hit";
        }
        else {
            return 3;
            //"Penalty: Redundant Guess";
        }
    }


    //Calculates and returns the remaining unnsunk boats.
    public int unsunkBoats(){
    int unsunk_count = 0;

    for (int start=0; start<num_boats; start++) {
        int cell_check = 0;
        temp = boats[start].get_spaces();
        for (int check = 0; check < 3; check++) {
            if (temp[check].get_status() =='B') {
                cell_check++;
            }

        }
        if (cell_check>=1){unsunk_count++;}
    }
    return unsunk_count;

    }
}
