import java.util.ArrayList;

/*Class implementing GameBoard logics*/
public class GameBoard {
    //cellsArray variable represents all cells within gameboard
    private ArrayList<BoardCell> cellsArray = new ArrayList<BoardCell>();
    //freeCells variable represents all free cells. It is a help variable for Computer Player for randomising.
    private ArrayList<BoardCell> freeCells = new ArrayList<BoardCell>();
    //A used cells counter it is a help variable for checking whether the game was a draw
    private int usedCells;
    //GameBoard constructor
    public GameBoard(){
        for(int i = 0; i<9; i++){
            BoardCell tmpCell = new BoardCell();
            cellsArray.add(tmpCell);
            freeCells.add(tmpCell);
            this.usedCells = 0;
        }
    }


    //Method that draws a GameBoard state as CLI representation
    public void redrawBoard(){
        System.out.println();
        System.out.println();
        System.out.println();
        for(int i = 0; i<3; i++){
            redrawCells(i);
            System.out.println();
            if(i<2) {
                redrawLine();
            }
        }
    }
    //Sub method for redrawBoard
    public void redrawCells(int line){
        for(int i = line*3; i<(line*3)+3; i++){
            if(cellsArray.get(i).getState() == CellsState.noState) {
                System.out.print(i);
            }
            else if (cellsArray.get(i).getState() == CellsState.xState){
                System.out.print('X');
            }
            else{
                System.out.print('O');
            }
            if((i%3 < 2)){
                System.out.print('|');
            }
        }
    }
    //Submethod for redrawBoard
    public void redrawLine(){
        System.out.println("-----");
    }

    //Submethod for ChooseCell. Clears player's last used cell, so Computer will not try to use it
    private void clearUsedCells(){
        for(int i = 0; i<freeCells.size(); i++){
            if(freeCells.get(i).getState() == CellsState.xState){
                freeCells.remove(i);
            }
        }
    }

    //Method that will change chosen player's cell's state
    public void chooseCell(int cell, CellsState state){
        if(cellsArray.get(cell).getState() == CellsState.noState) {
            cellsArray.get(cell).setState(state);
            clearUsedCells();
            this.usedCells++;
        }
        else{
            return;
        }
    }

    //Method that will randomly choose a cell for computer player
    public void chooseRandomCell(CellsState state){
        int cell = 0 + (int)(Math.random() * ((freeCells.size() - 0)));
        freeCells.get(cell).setState(state);
        freeCells.remove(cell);
        this.usedCells++;
    }

    //Method for getting a Board state
    public ArrayList<BoardCell> getBoardState(){
        return cellsArray;
    }

    //Method for getting a number of used cells
    public int getUsedCells(){
        return this.usedCells;
    }
}
