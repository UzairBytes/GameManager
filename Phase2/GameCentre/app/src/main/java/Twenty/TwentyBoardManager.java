package Twenty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fall2018.csc2017.CoreClasses.BoardManager;
import fall2018.csc2017.CoreClasses.SlidingBoard;
import phase1.AccountManager;
import phase1.SlidingGameFile;

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
     * Manage a new blank board.
     * @param size: the size of this new board.
     */
    @SuppressWarnings("unchecked")
    public TwentyBoardManager(int size) {

        // Initialize the board as all blank tiles.
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
     * Changes the board based off of a 'swipe' in a direction.
     * @param dir: character which indicated the direction of the swipe
     * Preconditions: dir is an element of: {'U', 'D', 'L', 'R'}
     * Postconditions: The board will be altered so all tiles are moved as much as possible in
     *      the direction dir. Also all tiles of equal id will collapse into one tile.
     */
    public void touchMove(char dir){
        if(dir == 'U' && isValidMove(false)){
//            for(int row = 0; row < this.twentyBoard.getNumRows(); row++){
//                for(int col = this.twentyBoard.getNumCols() - 1; col > 0; col--){
//                    if(this.twentyBoard.getTile)
//                }
//            }
        }else if(dir == 'D' && isValidMove(false)){

        }else if(dir == 'L' && isValidMove(true)){

        }else if(dir == 'R' && isValidMove(true)){

        }
    }

    /* Checks if a swipe results in a change in this TwentyBoard
     * @param horizDir boolean determining if the swipe is in the horizonal direction.
     *         If not, that implies that the swipe is in the vertical direction.
     */
    public boolean isValidMove(boolean horizDir){
        return this.twentyBoard.isCollapsable(horizDir);
    }
}