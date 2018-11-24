package Sliding;

import java.util.ArrayList;
import java.util.List;

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
     * List of Sliding tiles.
     */
    private ArrayList<SlidingTile> tiles;

    /**
     * Position of blank tile ArrayList of Sliding tiles.
     */
    private int position;

    /**
     * Constructor for SlidingBoardSolvable
     *
     * @param TilesToBeChecked is a SlidingBoard.
     */
    protected SlidingBoardSolvable(List<SlidingTile> TilesToBeChecked){
        this.BoardSize = TilesToBeChecked.size();
        this.inversions = 0;
        this.tiles = new ArrayList<>(TilesToBeChecked);
        this.position = 0;
    }

    /**
     * Calculates the number of inversions of board.
     * An inversion is when a tile precedes another
     * tile with a lower number on it assuming the tiles are arranged
     * in a row.
     *
     */
    private void calculateInversions(){
        for (int i = 0; i < tiles.size() - 1; i++){
            if (tiles.get(i).getId() != tiles.size()) {
                for (int j = i + 1; j < tiles.size(); j++) {
                    if (tiles.get(j).getId() != tiles.size()){
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
    private void positionOfBlankTile(){
        for (int i = 0; i < tiles.size(); i++){
            if (tiles.get(i).getId() == (tiles.size())){
                position = i+1;
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
        positionOfBlankTile();
        if (BoardSize == 9){
            return (position > 2 && position < 6);
        }
        else if (BoardSize == 16){
            return (position < 4) || (position > 7 && position < 12);
        } else{
            return (position > 4 && position < 10) || (position > 14 && position < 20);
        }
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
    protected void setTiles(List<SlidingTile> newTiles){
        this.tiles = new ArrayList<>(newTiles);
    }
}
