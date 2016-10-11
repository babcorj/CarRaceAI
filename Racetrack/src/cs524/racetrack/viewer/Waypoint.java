package cs524.racetrack.viewer;

import java.awt.Point;

//============================================================================================================================================================
/**
 * Defines a waypoint on the racetrack.
 * 
 * @author Dan Tappan [22.02.13]
 */
public class Waypoint
{
   /** the identifier */
   private final String _id;

   /** the x coordinate */
   private final Point _anchor;

   /** the inside edge of the track at this waypoint */
   private Point _edgeInside;

   /** the outside edge of the track at this waypoint */
   private Point _edgeOutside;

   /** the track width */
   private final int _trackWidth;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Creates a waypoint object. The units are arbitrary, but for our task, they map as pixel coordinates with (0,0) at the top left of the window.
    * 
    * @param id - the identifier
    * @param x - the <i>x</i> position
    * @param y - the <i>y</i> position
    * @param trackWidth - track width
    */
   public Waypoint(final String id, final int x, final int y, final int trackWidth)
   {
      assert (id != null);
      assert !id.isEmpty();
      assert (trackWidth > 0) : trackWidth;

      _id = id;

      _anchor = new Point(x, y);

      _trackWidth = trackWidth;
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Calculates the inner and outer edges of this waypoint.
    * 
    * @param waypointPrev - the previous waypoint
    * @param waypointNext - the next waypoint
    */
   public void calculateEdges(final Waypoint waypointPrev, final Waypoint waypointNext)
   {
      assert (waypointPrev != null);
      assert (waypointNext != null);

      assert ((_edgeInside == null) && (_edgeOutside == null)) : "edges already calculated";

      int deltaX = (waypointPrev.getAnchor().x - waypointNext.getAnchor().x);
      int deltaY = (waypointPrev.getAnchor().y - waypointNext.getAnchor().y);

      double angleDegrees = Math.toDegrees(Math.atan2(deltaY, deltaX));

      double angleTangentPlus = Math.toRadians(angleDegrees + 90);
      double angleTangentMinus = Math.toRadians(angleDegrees - 90);

      int trackWidthHalf = (_trackWidth / 2);

      int insideX = ((int) (Math.cos(angleTangentPlus) * trackWidthHalf) + _anchor.x);
      int insideY = ((int) (Math.sin(angleTangentPlus) * trackWidthHalf) + _anchor.y);

      int outsideX = ((int) (Math.cos(angleTangentMinus) * trackWidthHalf) + _anchor.x);
      int outsideY = ((int) (Math.sin(angleTangentMinus) * trackWidthHalf) + _anchor.y);

      _edgeInside = new Point(insideX, insideY);
      _edgeOutside = new Point(outsideX, outsideY);
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
    * Gets the inside edge point. The edges must have been calculated before call this; otherwise, an assertion error occurs.
    * 
    * @return the edge point
    */
   public Point getEdgeInside()
   {
      assert (_edgeInside != null) : "edges not calculated yet";

      return _edgeInside;
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Gets the inside edge point. The edges must have been calculated before call this; otherwise, an assertion error occurs.
    * 
    * @return the edge point
    */
   public Point getEdgeOutside()
   {
      assert (_edgeOutside != null) : "edges not calculated yet";

      return _edgeOutside;
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
    * Gets the track width at this point.
    * 
    * @return the width
    */
   public int getTrackWidth()
   {
      return _trackWidth;
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * {@inheritDoc}
    */
   @Override
   public String toString()
   {
      return ("id=" + _id + " anchor=" + _anchor + " trackWidth=" + _trackWidth + " edgeInside=" + _edgeInside + " edgeOutside=" + _edgeOutside);
   }
}
