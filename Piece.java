import java.awt.Color;

/**
 * Indicates an individual piece of a game, like a checker piece.
 * 
 * @author  Susan King
 * @author  
 * @version July 10, 2011
 */
public class Piece
{
   private Color color;

   /**
    * Creates a game piece of a specific color.
    */
   public Piece(Color color)
   {
       this.color = color;
   }

   /**
    * Retrieves the color of this piece.
    * 
    * @return color of this piece
    * */
   public Color getColor()
   {
      return color;
   }
}
