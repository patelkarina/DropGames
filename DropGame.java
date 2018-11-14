import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * Supports games in which a checker-like piece is dropped into
 * one of the slots at a top, and the piece dropped as low as
 * it can.  It cannot move to a location in which another piece
 * already occupies.  It is used by games like FourNeighbors and
 * FourInALine.
 * 
 * @author   Susan King
 * @author
 * @version  July 10, 2010
 */
public class DropGame
{
    private Grid<Piece> theGrid;

    /**
     * Creates a gui in which to play the game.
     */
    public DropGame(Grid<Piece> gr)
    {
        theGrid = gr;
    }

    /** 
     * Retrieves the location a Piece object can drop to
     * at the bottom of a column.
     * 
     * Precondition: 0 <= column < theGrid.getNumCols()
     * 
     * @return       null if no empty locations exist in the column;
     *               otherwise, the empty location with the
     *               largest row index within the specified column;
     */
    public Location dropLocationForColumn(int column)
    {
        for (int r = theGrid.getNumRows() - 1; r >= 0; r--)
        {
            Location nextLoc = new Location(r, column);
            if (theGrid.get(nextLoc) == null)
            {
                return nextLoc;
            }
        }
        return null;
    }

    /**
     * Tests if new piece has four or more surrounding
     * neighbors of the same color.
     *
     * Precondition:     0 <= column < theGrid.getNumCols()
     * 
     * @param column      the column position the user is trying to drop
     *                    a Piece object
     * @param pieceColor  the color being matched
     * 
     * @return <code>true</code> if dropping a Piece object of the given
     *                   color into the specified column matches color with 
     *                   four neighbors;
     *         <code>false</code> otherwise
     */
    public boolean countNeighbors(int column, Color pieceColor)
    {
        Location loc = dropLocationForColumn(column);
        if (loc == null)
        {
            return false;
        }

        ArrayList <Piece> neighbors = theGrid.getNeighbors(loc);
        // ArrayList<Piece> neighbors = ...;
        int colorCount = 0;

        for (int i = 0; i < neighbors.size(); i++)
        {
            Piece p = neighbors.get(i);
            if (p.getColor().equals(pieceColor))
            {
                colorCount++;
            }
        }
        // loop to determine if neighbors have the same color
        return (colorCount >= 4);
    }

    /**
     * Tests whether there are four or more of the same color
     * in a row based upon a location.
     * 
     * Precondition:     0 <= column < theGrid.getNumCols()
     * 
     * @return <code>true</code> if dropping a Piece object of the 
     *                   given color into the specified column 
     *                   matches color with four in a line;
     *         <code>false</code> otherwise
     */
    public boolean checkAllDirectionsForFour(Location dropLoc, Color pieceColor)
    {
        /* ... write code to count how many Piece objects are in a row:
         * ... horizontally, vertically, or diagonal (both directions).
         * 
         * You will want to write support method(s) to do this, so plan
         * this well.  This structure might help you.  It includes useful
         * debug statements.

        // Check the vertical, horizontal, and both diagonals.  
        // Pick the maximum.
        int backDiagonal = ...
        int diagonal     = ...
        int vertical     = ...
        int horizontal   = ...
        int count        = Math.max (Math.max(backDiagonal,diagonal),
        Math.max(vertical,horizontal) );
        System.out.println("\nPiece Color "    + pieceColor   + 
        "\n\tVertical   = " + vertical     +
        "\n\tHorizontal = " + horizontal   +
        "\n\tBack Diagon= " + backDiagonal +
        "\n\tDiagonal   = " + diagonal);             
         */
        // remove this when you write this method

        // only need to check if 3 in a row from the current loc (last move made)

         // only need to check if 3 in a row from the current loc (last move made)
        int dir = Location.AHEAD, consecutive = 0;
        Location nextLoc;
        if (dropLoc == null)
        {
            return false;
        }
        
      
        for (int line = 0; line < 4; line++)
        {
            consecutive = 1;
            for (int i = 0; i < 2; i++)
            {   nextLoc = dropLoc.getAdjacentLocation(dir);
                while (theGrid.isValid(nextLoc) && theGrid.get(nextLoc)!= null && theGrid.get(nextLoc).getColor().equals(pieceColor))
                {
                    consecutive++;
                    nextLoc = nextLoc.getAdjacentLocation(dir);
                }
                dir += Location.HALF_CIRCLE;
            }
            if (consecutive >= 4)
            { return true;
            }
            dir += Location.HALF_RIGHT;
        }
        return false;
    }
}
