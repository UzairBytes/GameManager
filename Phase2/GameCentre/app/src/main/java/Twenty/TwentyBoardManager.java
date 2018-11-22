package Twenty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
     * Holds a stack of Boards, with each Board representing a specific game state.
     */
    private Stack<TwentyBoard> gameStates;

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

        // Initialize the board as all blank tiles.
        this.size = size;
        List<TwentyTile> tiles = new ArrayList<>();
        final int numTiles = size * size;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tiles.add(new TwentyTile(0, 0));
        }

        // Create the board, and specify number of rows, columns
        this.twentyBoard = new TwentyBoard(tiles, size, size);

        // Create a new GameFile, and initialize it with this blank board.
        TwentyGameFile gameFile = new TwentyGameFile(this.twentyBoard, Instant.now().toString());

        // Add this new GameFile to the current active account's list of GameFiles.
        AccountManager.activeAccount.addGameFile(gameFile);
        this.gameFile = gameFile;
        this.gameStates = this.gameFile.getGameStates();
        this.numMoves = gameFile.numMoves;
        this.maxUndos = gameFile.maxUndos;
    }

    /**
     * Checks if a move is valid, and if so moves & merges the tiles in that direction
     * as much as possible
     * @param dir: character which indicated the direction of the swipe
     * Preconditions: dir is an element of: {'U', 'D', 'L', 'R'}
     */
    public void touchMove(char dir){
        TwentyTile tile1, tile2;
        if(dir == 'U' && isValidMove(false)){
            mergeTilesInDir('U');
        }else if(dir == 'D' && isValidMove(false)){
            mergeTilesInDir('D');
        }else if(dir == 'L' && isValidMove(true)){
            mergeTilesInDir('L');
        }else if(dir == 'R' && isValidMove(true)){
            mergeTilesInDir('R');
        }else{
            // Not a valid move, game is finished here.
        }
    }

    /**
     * Given a direction, move the tiles in the board in that direction as much as possible.
     * @param dir: character which indicated the direction of the swipe
     * Preconditions: dir is an element of: {'U', 'D', 'L', 'R'}
     * Postconditions: The board will be altered so all tiles are moved as much as possible in
     *      the direction dir. Also all tiles of equal id will collapse into one tile.
     */
    private void mergeTilesInDir(char dir){
        TwentyTile tile1, tile2;
        int tile1Row = 0, tile2Row = 0, tile1Col = 0, tile2Col = 0;
        // This algorithm must be done three times for correctness.
        for(int i = 0; i<3; i++){
            for(int j = 0; j < this.twentyBoard.getNumRows(); j++){
                for(int k = this.twentyBoard.getNumCols() - 1; k > 0; k--){
                    if(dir == 'U'){
                        tile1Row = k-1;
                        tile1Col = j;
                        tile2Row = k;
                        tile2Col = j;
                    }else if(dir == 'D'){
                        tile1Row = k;
                        tile1Col = j;
                        tile2Row = k-1;
                        tile2Col = j;
                    }else if(dir == 'L'){
                        tile1Row = j-1;
                        tile1Col = k;
                        tile2Row = j;
                        tile2Col = k;
                    }else{
                        tile1Row = j;
                        tile1Col = k;
                        tile2Row = j-1;
                        tile2Col = k;
                    }
                    tile1 = (TwentyTile)this.twentyBoard.getTile(tile1Row, tile1Col);
                    tile2 = (TwentyTile)this.twentyBoard.getTile(tile2Row, tile2Col);
                    if(tile1.getId() == tile2.getId()){
                        this.twentyBoard.mergeTiles(tile1Row, tile1Col, tile2Row, tile2Col);
                    }else if(tile1.getId() == 0){
                        this.twentyBoard.swapTiles(tile1Row, tile1Col, tile2Row, tile2Col);
                    }
                }
            }
        }
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
}