package cs524.racetrack.viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

//============================================================================================================================================================
/**
 * Generates a realtime view of a simple racetrack with cars.
 * 
 * @author Dan Tappan [22.02.13]
 */
@SuppressWarnings("serial")
public class RacetrackViewer extends JFrame
{
   /** the frame size */
   private final Dimension FRAME_SIZE = new Dimension(1000, 700);

   /** whether to display the waypoint identifiers */
   private static final boolean DISPLAY_ID = true;

   /** whether to display the waypoint dots */
   private static final boolean DISPLAY_DOT = true;

   /** whether to display the track centerline */
   private static final boolean DISPLAY_CENTERLINE = true;

   /** whether to display the waypoint tangents */
   private static final boolean DISPLAY_TANGENTS = true;

   /** whether to display the racecars */
   private static final boolean DISPLAY_RACECARS = true;

   /** whether to display the racecar identifiers */
   private static final boolean DISPLAY_RACECAR_ID = true;

   /** the waypoint circle diameter */
   private static final int WAYPOINT_SIZE = 6;

   /** the racecar diameter */
   private static final int RACECAR_SIZE = 15;

   /** the racetrack waypoints */
   private final List<Waypoint> _waypoints;

   /** the racecars */
   private final List<Racecar> _racecars;

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Creates a racetrack viewer.
    * 
    * @param waypoints - the waypoints
    * @param racecars - the racecars, which may be empty
    */
   public RacetrackViewer(final List<Waypoint> waypoints, final List<Racecar> racecars)
   {
      assert (waypoints != null);
      assert !waypoints.isEmpty();
      assert (racecars != null);

      _waypoints = Collections.unmodifiableList(waypoints);

      _racecars = Collections.unmodifiableList(racecars);

      calculateEdges();

      // build and display the frame
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      getContentPane().setBackground(Color.BLACK);
      setSize(FRAME_SIZE);
      setVisible(true);
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Calculates the track edges from the waypoints.
    */
   private void calculateEdges()
   {
      final int waypointCount = _waypoints.size();

      for (int iWaypoint = 0; iWaypoint < waypointCount; ++iWaypoint)
      {
         int waypointPrevIndex = ((iWaypoint > 0) ? (iWaypoint - 1) : (waypointCount - 1));

         Waypoint waypointPrev = _waypoints.get(waypointPrevIndex);

         int waypointNextIndex = ((iWaypoint < (waypointCount - 1)) ? (iWaypoint + 1) : 0);

         Waypoint waypointNext = _waypoints.get(waypointNextIndex);

         Waypoint waypoint = _waypoints.get(iWaypoint);

         waypoint.calculateEdges(waypointPrev, waypointNext);
      }
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Paints the racetrack and cars.
    * 
    * @param graphics - the graphics context
    */
   @Override
   public void paint(final Graphics graphics)
   {
      assert (graphics != null);

      super.paint(graphics);

      renderRacetrack(graphics);

      if (DISPLAY_RACECARS)
      {
         renderRacecars(graphics);
      }
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Renders the cars.
    * 
    * @param graphics - the graphics context
    */
   private void renderRacecars(final Graphics graphics)
   {
      assert (graphics != null);

      for (Racecar racecar : _racecars)
      {
         int x = racecar.getAnchor().x;
         int y = racecar.getAnchor().y;

         int racecarSizeHalf = (RACECAR_SIZE / 2);

         graphics.setColor(racecar.getColor());

         graphics.fillRect((x - racecarSizeHalf), (y - racecarSizeHalf), RACECAR_SIZE, RACECAR_SIZE);

         if (DISPLAY_RACECAR_ID)
         {
            graphics.drawString(racecar.getID(), (x - racecarSizeHalf), (y - racecarSizeHalf));
         }
      }
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Renders the racetrack.
    * 
    * @param graphics - the graphics context
    */
   private void renderRacetrack(final Graphics graphics)
   {
      assert (graphics != null);

      final int waypointCount = _waypoints.size();

      for (int iWaypoint = 0; iWaypoint < waypointCount; ++iWaypoint)
      {
         Waypoint waypoint = _waypoints.get(iWaypoint);

         int waypointNextIndex = ((iWaypoint < (waypointCount - 1)) ? (iWaypoint + 1) : 0);

         Waypoint waypointNext = _waypoints.get(waypointNextIndex);

         renderSegment(graphics, waypoint, waypointNext);
      }
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Renders a track segment connecting two waypoints.
    * 
    * @param graphics - the graphics context
    * @param waypointStart - the start waypoint
    * @param waypointEnd - the end waypoint
    */
   private void renderSegment(final Graphics graphics, final Waypoint waypointStart, final Waypoint waypointEnd)
   {
      assert (graphics != null);
      assert (waypointStart != null);
      assert (waypointEnd != null);

      // render the tangent line at the current waypoint
      if (DISPLAY_TANGENTS)
      {
         graphics.setColor(new Color(0, 128, 0));

         Point edgeInside = waypointEnd.getEdgeInside();
         Point edgeOutside = waypointEnd.getEdgeOutside();

         graphics.drawLine(edgeInside.x, edgeInside.y, edgeOutside.x, edgeOutside.y);
      }

      // render the track edges
      graphics.setColor(Color.GREEN);

      Point startEdgeInside = waypointStart.getEdgeInside();
      Point startEdgeOutside = waypointStart.getEdgeOutside();

      Point endEdgeInside = waypointEnd.getEdgeInside();
      Point endEdgeOutside = waypointEnd.getEdgeOutside();

      graphics.drawLine(startEdgeInside.x, startEdgeInside.y, endEdgeInside.x, endEdgeInside.y);
      graphics.drawLine(startEdgeOutside.x, startEdgeOutside.y, endEdgeOutside.x, endEdgeOutside.y);

      int startX = waypointStart.getAnchor().x;
      int startY = waypointStart.getAnchor().y;

      int endX = waypointEnd.getAnchor().x;
      int endY = waypointEnd.getAnchor().y;

      // render the centerline
      if (DISPLAY_CENTERLINE)
      {
         graphics.setColor(Color.GRAY);

         graphics.drawLine(startX, startY, endX, endY);
      }

      // render the waypoint dot
      if (DISPLAY_DOT)
      {
         int waypointSizeHalf = (WAYPOINT_SIZE / 2);

         graphics.setColor(Color.YELLOW);

         graphics.drawOval((startX - waypointSizeHalf), (startY - waypointSizeHalf), WAYPOINT_SIZE, WAYPOINT_SIZE);
      }

      // render the waypoint identifier
      if (DISPLAY_ID)
      {
         graphics.setColor(Color.RED);

         graphics.drawString(waypointStart.getID(), startX, startY);
      }
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Updates the display with the current state of the model.
    */
   public void update()
   {
      repaint();
   }
}
