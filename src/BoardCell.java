
//Class representing a GameBoard's cell
public class BoardCell {
    //Attribute representating whether it is non-used cell or Player's 'X' cell or Computer's 'O' cell
    private CellsState state;
    //Constructor that initialize a cell's state
    public BoardCell(){
        this.state = CellsState.noState;
    }
    //Cell's state getter
    public CellsState getState(){
        return this.state;
    }
    //Cell's state setter
    public void setState(CellsState state){
        this.state = state;
    }
}
