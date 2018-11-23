package Sliding;

import java.util.ArrayList;

/**
 * SlidingBoardSolvable checks a SlidingBoard to determine if it is solvable.
 *
 * Solvability of a sliding tiles board based on
 * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
 */
public class SlidingBoardSolvable {

    /**
     * The board to be checked
     */
    private SlidingBoard Board;

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
     * @param BoardToBeChecked is a SlidingBoard.
     */
    protected SlidingBoardSolvable(SlidingBoard BoardToBeChecked){
        this.Board = BoardToBeChecked;
        this.BoardSize = Board.numTiles();
        this.inversions = 0;
        this.tiles = new ArrayList<>(Board.getTilesList());
    }

    /**
     * Calculates the number of inversions of board.
     * An inversion is when a tile precedes another
     * tile with a lower number on it assuming the tiles are arranged
     * in a row.
     *
     * @return int number of inversions.
     */
    private int calculateInversions(){
        for (int i = 0; i < tiles.size(); i++){
            for (int j = 0; j < tiles.size(); j++){
                if (tiles.get(i).getId() > tiles.get(j).getId()){
                    inversions++;

                }
            }
        } return inversions;
    }

    /**
     * Returns true if the number of inversions of board are even.
     *
     * @return boolean true or false.
     */
    private boolean isInversionsEven(){
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
     * @return int position.
     */
    private int positionOfBlankTile(){

        for (int i = 0; i < tiles.size(); i++){
            if (tiles.get(i).getId() == (tiles.size() - 1)){
                position = i;
            }
        } return position;
    }

    /**
     * Return true if the blank SlidingTile is on an odd row from the bottom of the grid.
     *
     * @return boolean true or false.
     */
    // TODO Implement this algorithmically. This works for board size 3-5, but it is shitty.
    private boolean isBlankTileOnOddRow(){
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
    public boolean isBoardSolvable(){
        if (!isBoardSizeEven()){
            return isInversionsEven();
        } else{
            if (!isBlankTileOnOddRow()){
                return !isInversionsEven();
            } else{
                return isInversionsEven();
            }
        }
    }


}
