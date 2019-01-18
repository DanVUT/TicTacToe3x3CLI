import java.util.ArrayList;
import java.util.Scanner;

/*Game class takes care of the main game functionalities like game loop as well as checking the game status*/
public class Game {
    //instance representating the game playing board
    private GameBoard board = new GameBoard();

    //method that draws the CLI playing board and chooses the first turn player
    public void start(){
        int winningState = 0;
        board.redrawBoard();
        int playerFirst = 1 + (int)(Math.random() * ((100)));

        if(playerFirst <= 50) {
            playerGameLoop();
        }
        else{
            computerGameLoop();
        }
    }

    //Game loop in case player goes first
    private void playerGameLoop(){
        int winningState = 0;
        while(true) {
            playerMove();
            board.redrawBoard();
            winningState = checkWinner();

            if(winningState == 1){
                System.out.println("Player won.");
                break;
            }
            if(winningState == 2){
                System.out.println("Computer won.");
                break;
            }
            if(winningState == -1){
                System.out.println("Draw.");
                break;
            }

            computerMove();
            board.redrawBoard();
            winningState = checkWinner();

            if(winningState == 1){
                System.out.println("Player won.");
                break;
            }
            if(winningState == 2){
                System.out.println("Computer won.");
                break;
            }
            if(winningState == -1){
                System.out.println("Draw.");
                break;
            }
        }
    }
    //Game loop in case computer goes first
    private void computerGameLoop(){
        int winningState = 0;
        while(true) {
            computerMove();
            board.redrawBoard();
            winningState = checkWinner();

            if(winningState == 1){
                System.out.println("Player won.");
                break;
            }
            if(winningState == 2){
                System.out.println("Computer won.");
                break;
            }
            if(winningState == -1){
                System.out.println("Draw.");
                break;
            }

            playerMove();
            board.redrawBoard();
            winningState = checkWinner();

            if(winningState == 1){
                System.out.println("Player won.");
                break;
            }
            if(winningState == 2){
                System.out.println("Computer won.");
                break;
            }
            if(winningState == -1){
                System.out.println("Draw.");
                break;
            }
        }
    }

    //method implementing the player move. Player chooses the playing board cell he wants to place a symbol on
    public void playerMove(){
        int tmpInt;
        while(true) {
            System.out.println("Vyberte pole:");
            Scanner scanner = new Scanner(System.in);
            tmpInt = scanner.nextInt();
            if(tmpInt >=0 && tmpInt <= 8){
                break;
            }
        }
        board.chooseCell(tmpInt, CellsState.xState);
    }

    //Makes a random computer turn
    public void computerMove(){
        board.chooseRandomCell(CellsState.oState);
    }

    //Method checking whether somebody wins or whether it is a draw
    public int checkWinner(){
        ArrayList<BoardCell> state = board.getBoardState();
        int winningState = 0;
        winningState = checkHorisontals(state);
        if(winningState != 0) {
            return winningState;
        }
        winningState = checkVerticals(state);
        if(winningState != 0){
            return winningState;
        }
        winningState = checkDiagonals(state);
        if(winningState != 0){
            return winningState;
        }
        winningState=checkDraw();
        if(winningState != 0){
            return winningState;
        }
        return 0;
    }
    //method looking for 3 same symbols in horisontal lines
    private int checkHorisontals(ArrayList<BoardCell> state){
        CellsState tmp = CellsState.noState;
        for(int i = 0; i <=6; i=i+3){
            for(int j = i; j<i+2; j++){
                if(state.get(j).getState() == state.get(j+1).getState()){
                    tmp = state.get(j).getState();
                    continue;
                }
                else{
                    tmp = CellsState.noState;
                    break;
                }
            }
            if(tmp != CellsState.noState){
                break;
            }
        }
        if(tmp == CellsState.noState){
            return 0;
        }
        else if(tmp == CellsState.xState){
            return 1;
        }
        else if(tmp == CellsState.oState){
            return 2;
        }
        return 0;
    }

    //Method looking for 3 same symbols in vertical lines
    private int checkVerticals(ArrayList<BoardCell> state){
        CellsState tmp = CellsState.noState;
        for(int i=0; i<3; i++){
            for(int j=i; j<i+6; j=j+3){
                if(state.get(j).getState() == state.get(j+3).getState()){
                    tmp=state.get(j).getState();
                    continue;
                }
                else{
                    tmp=CellsState.noState;
                    break;
                }
            }
            if(tmp != CellsState.noState){
                break;
            }
        }

        if(tmp == CellsState.noState){
            return 0;
        }
        else if(tmp == CellsState.xState){
            return 1;
        }
        else if(tmp == CellsState.oState){
            return 2;
        }
        return 0;
    }

    //Method looking for 3 same symbols on diagonal lines
    private int checkDiagonals(ArrayList<BoardCell> state){
        CellsState tmp = CellsState.noState;
        if((state.get(0).getState() == state.get(4).getState()) && (state.get(4).getState() ==state.get(8).getState())){
            tmp = state.get(0).getState();
        }
        else if((state.get(2).getState() == state.get(4).getState()) && (state.get(4).getState() ==state.get(6).getState())){
            tmp = state.get(2).getState();
        }

        if(tmp == CellsState.noState){
            return 0;
        }
        else if (tmp == CellsState.xState){
            return 1;
        }
        else if (tmp == CellsState.oState){
            return 2;
        }
        return 0;
    }

    //Method that checks whether all playing cells were used
    private int checkDraw(){
        if(board.getUsedCells() == 9){
            return -1;
        }
        return 0;
    }
}
