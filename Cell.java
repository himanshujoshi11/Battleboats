//Written by joshi271
public class Cell {
    private int row;
    private int col;
    private char status; // ' ': Empty, 'B': Boat, 'H': Hit; 'M': Miss


    //Returns Status of a cell.
    public char get_status(){
        return status;

    }


    //Sets the status of a cell.
    public void set_status(char c){
        status = c;
    }


    //Cell constructor with a row, column, and its status.
    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;


    }

}
