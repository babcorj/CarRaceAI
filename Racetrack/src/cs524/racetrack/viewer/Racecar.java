package cs524.racetrack.viewer;

import java.awt.Color;
import java.awt.Point;

//============================================================================================================================================================
/**
 * Defines a racecar.
 * 
 * @author Dan Tappan [22.02.13]
 */
public class Racecar
{
   /** the identifier */
   private final String _id;

   /** the anchor */
   private final Point _anchor;

   /** the color */
   private final Color _color;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Creates a racecar.
    * 
    * @param id - the identifier
    * @param color - the color
    * @param x - the initial <i>x</i> position
    * @param y - the initial <i>y</i> position
    */
   public Racecar(final String id, final Color color, final int x, final int y)
   {
      assert (id != null);
      assert !id.isEmpty();
      assert (color != null);

      _id = id;

      _color = color;

      _anchor = new Point(x, y);
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Gets the anchor coordinate.
    * 
    * @return the coordinate
    */
   public Point getAnchor()
   {
      return _anchor;
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Gets the color.
    * 
    * @return the color
    */
   public Color getColor()
   {
      return _color;
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Gets the identifier.
    * 
    * @return the identifier
    */
   public String getID()
   {
      return _id;
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * {@inheritDoc}
    */
   @Override
   public String toString()
   {
      return ("id=" + _id + " color=" + _color + " anchor=" + _anchor);
   }
}
