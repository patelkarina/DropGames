import java.awt.Color;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

/**
 * FourInALine is like the game Connect 4 in which 2 players
 * are trying to be the first to have 4 Piece objects of the same 
 * color in a consecutive horizontal, vertical or diagonal pattern.
 * 
 * 
 * @author  Susan King
 * @author
 * @version July 10, 2011
 */
public class FourInALine extends World<Piece>
{
    private DropGame game;
    private Color currentColor;
    private boolean gameOver;

    /**
     * Sets up a game with a gui with a particular size.
     */
    public FourInALine(int rows, int cols)
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

    @Override
    public boolean locationClicked(Location loc)
    {
        // ... fill in this 

        // ... NOTE:  You need to move this line:
        // getGrid().put(dropLoc, new Piece(currentColor));   
        // ... before these lines
        //   if (game.checkAllDirectionsForFour(loc , currentColor))
        //   {
        //      setMessage("Congratulations--you won");
        //      gameOver = true;
        //  }   
        if (gameOver) 
            return false;
        Location dropLoc = game.dropLocationForColumn(loc.getCol());
        if (dropLoc == null|| !getGrid().isValid(dropLoc)) 
            setMessage("Illegal drop location, try again");
        else
        {
            getGrid().put(dropLoc, new Piece(currentColor));
            if (game.checkAllDirectionsForFour(dropLoc, currentColor))
            {
                setMessage("Congratulations--you won");
                gameOver = true;
            }
            if (!gameOver) flipTurn();
            return true;
        }
        return false;

    }     

    /**
     * Creates and positions a robot to test code.
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args)
    {
        FourInALine world = new FourInALine(4, 6);
        world.show();
    }
}
