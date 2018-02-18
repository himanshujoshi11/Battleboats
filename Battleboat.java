//Written by joshi271
public class Battleboat {

    private int size = 3;
    private boolean orientation; // false <-> horizontal, true <-> vertical
    private Cell[] spaces = new Cell[3];

    //Battleboat constructor where the battleboat is assigned a random orientation.
    public Battleboat() {
        double random = Math.random();
        if (random >= 0.5) {
            orientation = false;
        } else {
            orientation = true;
        }
    }


    //Returns orientation of a battleboat.
    public boolean get_orientation(){
        return orientation;
    }


    //Returns size of a battleboat.
    public int get_size(){
        return size;

    }


    //Return the space of a battleboat.
    public Cell[] get_spaces(){
        return spaces;

    }
    //Set spaces of a battleboat.
    public void set_spaces(int s, Cell X){
        spaces[s]= X;
    }
}
