package Sliding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.Tile;

/**
 * SlidingBoardSolvable checks a SlidingBoard to determine if it is solvable.
 *
 * Solvability of a sliding tiles board based on
 * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
 */
public class SlidingBoardSolvable {

    /**
     * size of the board.
     */
    private int BoardSize;

    /**
     * The number of inversions in the board.
     */
    private int inversions;

    /**
     * 2D array of tiles.
     */
    private Tile[][] tiles;

    /**
     * column of blank tile ArrayList of Sliding tiles.
     */
    private int column;

    /**
     * ArrayList of tiles.
     */
    private ArrayList<Tile> tileList;

    /**
     * Constructor for SlidingBoardSolvable
     *
     * @param TilesToBeChecked is a SlidingBoard.
     */
    protected SlidingBoardSolvable(Tile[][] TilesToBeChecked){
        this.BoardSize = TilesToBeChecked.length;
        this.inversions = 0;
        this.tiles = TilesToBeChecked;
        this.column = 0;
        this.tileList = new ArrayList<>(this.tileList);
    }

    /**
     * Calculates the number of inversions of board.
     * An inversion is when a tile precedes another
     * tile with a lower number on it assuming the tiles are arranged
     * in a row.
     *
     */
    private void calculateInversions(){
        for (int i = 0; i < tileList.size() - 1; i++){
            if (tileList.get(i).getId() != tileList.size()) {
                for (int j = i + 1; j < tileList.size(); j++) {
                    if (tileList.get(j).getId() != tileList.size()){
                        inversions++;
                    }
                }
            }
        }
    }

    /**
     * Returns true if the number of inversions of board are even.
     *
     * @return boolean true or false.
     */
    private boolean AreInversionsEven(){
        calculateInversions();
        return (inversions % 2 == 0);
    }

    /**
     * Returns true if Board contains an even number oftiles.
     *
     * @return boolean true or false.
     */
    private boolean isBoardSizeEven(){
        return (BoardSize % 2 == 0);
    }

    /**
     * Returns position of Blank tile in board.
     *
     */
    private void columnnOfBlankTile(){
        for (int row = 0; row < BoardSize; row++) {
            for (int col = 0; col < BoardSize; col++) {
                if (tiles[row][col].getId() == tiles.length) {
                    column = col;
                }
            }
        }
    }

    /**
     * Return true if the blank SlidingTile is on an odd row from the bottom of the grid.
     *
     * @return boolean true or false.
     */
    // TODO Implement this algorithmically. This works for board size 3-5, but it is shitty.
    private boolean isBlankTileOnOddRow(){
        columnnOfBlankTile();
        return (BoardSize - this.column) % 2 != 0;
    }

    /**
     * Return true if the board is solvable.
     *
     * @return boolean true or false.
     */
    protected boolean isBoardSolvable(){
        if (!isBoardSizeEven()){
            return AreInversionsEven();
        } else{
            if (!isBlankTileOnOddRow()){
                return !AreInversionsEven();
            } else{
                return AreInversionsEven();
            }
        }
    }

    /**
     * Set list of sliding tiles to newTiles.
     *
     * @param newTiles List of sliding tiles
     */
    protected void setTiles(Tile[][] newTiles){
        this.tiles = newTiles;
    }
}
