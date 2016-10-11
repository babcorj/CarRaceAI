package cs524.racetrack.viewer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

//============================================================================================================================================================
/**
 * Displays a sample racetrack viewer.
 * 
 * @author Dan Tappan [22.02.13]
 */
public class RacetrackViewerTest
{
   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * The tester main method.
    * 
    * @param arguments - the arguments, which are not used
    */
   public static void main(final String[] arguments)
   {
      RacetrackViewerTest test = new RacetrackViewerTest();

      test.runTest();
   }

   // ---------------------------------------------------------------------------------------------------------------------------------------------------------
   /**
    * Builds a racetrack like the one on the board.
    */
   public void runTest()
   {
      int trackWidth = 50;

      List<Waypoint> waypoints = new ArrayList<>();

      waypoints.add(new Waypoint("p1", 172, 600, (int) (trackWidth * 3.0)));
      waypoints.add(new Waypoint("p2", 800, 600, (int) (trackWidth * 1.5)));
      waypoints.add(new Waypoint("p3", 834, 596, (int) (trackWidth * 1.3)));
      waypoints.add(new Waypoint("p4", 870, 582, trackWidth));
      waypoints.add(new Waypoint("p5", 888, 547, trackWidth));
      waypoints.add(new Waypoint("p6", 888, 453, trackWidth));
      waypoints.add(new Waypoint("p7", 861, 349, trackWidth));
      waypoints.add(new Waypoint("p8", 819, 312, (int) (trackWidth * 1.5)));
      waypoints.add(new Waypoint("p9", 747, 300, (int) (trackWidth * 2.0)));
      waypoints.add(new Waypoint("p10", 493, 300, (int) (trackWidth * 2.0)));
      waypoints.add(new Waypoint("p11", 415, 275, trackWidth));
      waypoints.add(new Waypoint("p12", 358, 219, trackWidth));
      waypoints.add(new Waypoint("p13", 316, 138, trackWidth));
      waypoints.add(new Waypoint("p14", 289, 109, trackWidth));
      waypoints.add(new Waypoint("p15", 250, 100, trackWidth));
      waypoints.add(new Waypoint("p16", 187, 100, (int) (trackWidth * 1.2)));
      waypoints.add(new Waypoint("p17", 142, 115, trackWidth));
      waypoints.add(new Waypoint("p18", 112, 156, trackWidth));
      waypoints.add(new Waypoint("p19", 100, 220, trackWidth));
      waypoints.add(new Waypoint("p20", 100, 350, (int) (trackWidth * 0.5)));
      waypoints.add(new Waypoint("p21", 100, 500, (int) (trackWidth * 2.0)));
      waypoints.add(new Waypoint("p22", 120, 570, (int) (trackWidth * 2.9)));

      Racecar racecar1 = new Racecar("R1", Color.PINK, 200, 600);

      List<Racecar> racecars = new ArrayList<>();

      racecars.add(racecar1);

      RacetrackViewer viewer = new RacetrackViewer(waypoints, racecars);

      viewer.update();
   }
}
