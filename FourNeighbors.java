import java.awt.Color;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

/**
 * Plays a game in which 2 players try to be the first
 * to drop a Piece object that is surrounded by 4 other
 * Piece objects of the same color.
 * 
 * @author  Susan King
 * @author
 * @version July 10, 2011
 */
public class FourNeighbors extends World<Piece>
{
    private DropGame game;
    private Color currentColor;
    private boolean gameOver;

    /**
     * Sets up a game with a gui with a particular size.
     */
    public FourNeighbors(int rows, int cols)
    {
        super(new BoundedGrid<Piece>(rows, cols));
        game = new DropGame(getGrid());
        currentColor = Color.WHITE;
        flipTurn();
    }

    /**
     * Changes whose turn it is.
     */
    public void flipTurn()
    {
        if (currentColor.equals(Color.WHITE))
        {
            currentColor = Color.BLACK;
            setMessage("Black's turn");
        }
        else
        {
            currentColor = Color.WHITE;
            setMessage("White's turn");
        }         
    }

    /**
     * When the current player clicks and attempts to place a piece, this 
     * method see whether it is a legal move and, if so, places a piece 
     * and calls countNeighbors
     * 
     * @param loc location passed from the GUI where the use just clicked
     * 
     * @return <code>true</code> if location clicked was valid; 
     *         <code>false</code> otherwise or game is over
     */
    @Override
    public boolean locationClicked(Location loc)
    {
        if (gameOver) 
            return false;
        Location dropLoc = game.dropLocationForColumn(loc.getCol());
        if (dropLoc == null) 
            setMessage("Illegal drop location, try again");
        else
        {
            if (game.countNeighbors(loc.getCol(), currentColor))
            {
                setMessage("Congratulations--you won");
                gameOver = true;
            }
            getGrid().put(dropLoc, new Piece(currentColor));
        }
        if (!gameOver) flipTurn();
        return true;
    }
           
    /**
     * Creates and positions a robot to test code.
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args)
    {
        FourNeighbors world = new FourNeighbors(4, 6);
        world.show();
    }
}

