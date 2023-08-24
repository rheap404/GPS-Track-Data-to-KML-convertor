import java.io.FileNotFoundException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Rhea Prakash
 */
public class TrackInfo {
  
  public static void main(String[] args) throws FileNotFoundException {

     Track track = new Track(args[0]);

     System.out.println(track.size() + " point in track");
     System.out.println("Lowest point is " + track.lowestPoint());
     System.out.println("Highest point is " + track.highestPoint());
     System.out.println("Total distance = " + track.totalDistance());
     System.out.println("Average Speed = " + track.averageSpeed());

  }
}
