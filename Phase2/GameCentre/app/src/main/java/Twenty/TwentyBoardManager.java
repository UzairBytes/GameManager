package Twenty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Sliding.SlidingBoard;
import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.BoardManager;
import phase1.AccountManager;

public class TwentyBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    protected TwentyBoard twentyBoard;

    /**
     * The 2048 Game File holding the data for this board.
     */
    private TwentyGameFile gameFile;

    /**
     * Keeps track of board size
     */
    private int size;


    /**
     * Manage a new blank board.
     * @param size: the size of this new board.
     */
    @SuppressWarnings("unchecked")
    public TwentyBoardManager(int size) {
        this.size = size;
        TwentyTile boardTiles[][] = new TwentyTile[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                boardTiles[i][j] = new TwentyTile(0,0);
            }
        }

        // Create the board, and specify number of rows, columns
        this.twentyBoard = new TwentyBoard(boardTiles, size, size);

        // Create a new GameFile, and initialize it with this blank board.
        TwentyGameFile gameFile = new TwentyGameFile(this.twentyBoard, Instant.now().toString());

        // Add this new GameFile to the current active account's list of GameFiles.
        AccountManager.activeAccount.addGameFile(gameFile);
        this.gameFile = gameFile;
        this.gameStates = this.gameFile.getGameStates();
        this.numMoves = gameFile.numMoves;
        this.maxUndos = gameFile.maxUndos;
        this.twentyBoard.generateRandomTile();
        save(this.twentyBoard);
    }

    /**
     * Checks if a move is valid, and if so moves & merges the tiles in that direction
     * as much as possible
     * @param dir: character which indicated the direction of the swipe
     * Preconditions: dir is an element of: {'U', 'D', 'L', 'R'}
     */
    public void touchMove(char dir){
        TwentyBoard newBoard = twentyBoard.createDeepCopy();
        this.twentyBoard = newBoard;
        System.out.println("Direction: " + dir);
        System.out.println("Before the move:");
        for(int i =0; i<3; i++){
            System.out.println(this.twentyBoard.getTile(i,0).id + " " +this.twentyBoard.getTile(i,1).id + " "+this.twentyBoard.getTile(i,2).id);
        }
        System.out.println("During the move!");
        boolean boardChanged = false;
        if(dir == 'U' && isValidMove(false)){
            boardChanged = mergeTilesInDir('U');
        }else if(dir == 'D' && isValidMove(false)){
            boardChanged = mergeTilesInDir('D');
        }else if(dir == 'L' && isValidMove(true)){
            boardChanged = mergeTilesInDir('L');
        }else if(dir == 'R' && isValidMove(true)){
            boardChanged = mergeTilesInDir('R');
        }

        if(boardChanged){
            this.twentyBoard.generateRandomTile();
            super.addUndos();
        }
        System.out.println("After the move:");
        for(int i =0; i<3; i++){
            System.out.println(this.twentyBoard.getTile(i,0).id + " " +this.twentyBoard.getTile(i,1).id + " "+this.twentyBoard.getTile(i,2).id);
        }
        save(this.twentyBoard);
        setChanged();
        notifyObservers();
    }

    /**
     * Given a direction, move the tiles in the board in that direction as much as possible.
     * @param dir: character which indicated the direction of the swipe
     * Preconditions: dir is an element of: {'U', 'D', 'L', 'R'}
     * Postconditions: The board will be altered so all tiles are moved as much as possible in
     *      the direction dir. Also all tiles of equal id will collapse into one tile.
     */
    private boolean mergeTilesInDir(char dir){
        TwentyTile tile1, tile2;
        int tile1Row = 0, tile2Row = 0, tile1Col = 0, tile2Col = 0;
        int sqBoardSize = this.twentyBoard.getNumRows();
        boolean boardChanged = false;
        // This algorithm must be done three times for correctness.
        // Although it is O(N^2) with NxN board, the board typically won't be greater than 5x5.
        for(int i = 0; i<sqBoardSize-1; i++){
            for(int j = 0; j < sqBoardSize; j++){
                for(int k = 0; k < sqBoardSize - 1; k++){
                    if(dir == 'U'){
                        tile1Row = k;
                        tile1Col = j;
                        tile2Row = k+1;
                        tile2Col = j;
                    }else if(dir == 'D'){
                        tile1Row = k+1;
                        tile1Col = j;
                        tile2Row = k;
                        tile2Col = j;
                    }else if(dir == 'L'){
                        tile1Row = j;
                        tile1Col = k;
                        tile2Row = j;
                        tile2Col = k+1;
                    }else if(dir == 'R'){
                        tile1Row = j;
                        tile1Col = k+1;
                        tile2Row = j;
                        tile2Col = k;
                    }
                    tile1 = (TwentyTile)this.twentyBoard.getTile(tile1Row, tile1Col);
                    tile2 = (TwentyTile)this.twentyBoard.getTile(tile2Row, tile2Col);

                    if(tile1.getId() == 0){
                        this.twentyBoard.swapTiles(tile1Row, tile1Col, tile2Row, tile2Col);
                        if(tile2.getId() != 0){
                            boardChanged = true;
                        }
                        System.out.println("ran 1");
                    } else if(tile1.getId() == tile2.getId()) {
                        this.twentyBoard.mergeTiles(tile1Row, tile1Col, tile2Row, tile2Col);
                        boardChanged = true;
                        System.out.println("ran 2");
                    }
                }
            }
        }
        return boardChanged;
    }

    /* Getter for the GameFile that this board manager exists in.
     * @return the game file this board manager exists in.
     */
    public TwentyGameFile getGameFile(){
        return this.gameFile;
    }

    /* Gauges whether or not the game is complete, i.e, no more possible moves can be made. */
    public boolean gameComplete(){
        return !isValidMove(false) && !isValidMove(true);
    }

    /* Checks if a swipe results in a change in this TwentyBoard
     * @param horizDir boolean determining if the swipe is in the horizontal direction.
     *         If not, that implies that the swipe is in the vertical direction.
     */
    public boolean isValidMove(boolean horizDir){
        return this.twentyBoard.isCollapsable(horizDir);
    }

    /**
     * Accesses the private value size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Accesses the protected Board
     */
    public TwentyBoard getBoard(){
        return this.twentyBoard;
    }

    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(TwentyBoard board) {
        super.save(board);
        this.gameFile = (TwentyGameFile) AccountManager.activeAccount.getActiveGameFile();
        this.gameStates = this.gameFile.getGameStates();
        this.twentyBoard = board;

        System.out.println("Board saved!");
    }

    /**
     * Switches the board back one move, if the user has undos left
     */
    @Override
    public Board undo() {
        if (this.remainingUndos > 0 && this.gameStates.size() > 1) {
            this.twentyBoard = (TwentyBoard) super.undo();
        }else{
            System.out.println("EmptyStackError!");
        }
        System.out.println("After the undo:");
        for(int i =0; i<3; i++){
            System.out.println(this.twentyBoard.getTile(i,0).id + " " +this.twentyBoard.getTile(i,1).id + " "+this.twentyBoard.getTile(i,2).id);
        }
        setChanged();
        notifyObservers();

        return this.twentyBoard;
    }

    /**
     *
     * Returns the calculated score
     */
    @Override
    public int score() {
        int finalScore = 0;
        for (int i = 0; i < this.twentyBoard.getNumRows(); i++){
            for (int j = 0; j < this.twentyBoard.getNumCols(); j++){
                finalScore += this.twentyBoard.getTile(i,j).getId();
            }
        }
        return finalScore;
    }


    public void setMaxUndos(int maxUndoValue) {
        this.gameFile.setMaxUndos(maxUndoValue);
        this.gameFile.setRemainingUndos(0);
        this.maxUndos = maxUndoValue;
        this.remainingUndos = maxUndoValue;
    }
}