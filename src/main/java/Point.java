import java.time.ZonedDateTime;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;


/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Rhea Prakash
 */
public class Point {
  // Constants useful for bounds checking, etc

  
  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  private ZonedDateTime time;
  private double longitude;
  private double lattitude;
  private double elevation;

  // Constructor checks validation of lattitude and Longitude and initializes variables
  public Point( ZonedDateTime t, double lon, double lat, double elev) {
   
    if(lon < MIN_LONGITUDE || lon > MAX_LONGITUDE) {
       throw new GPSException("Longitude invalid");
    }
    if(lat < MIN_LATITUDE || lat > MAX_LATITUDE) {
      throw new GPSException("Lattitude invalid");
   }

   this.time = t;
   this.lattitude = lat;
   this.longitude = lon;
   this.elevation = elev;

  }

  // Returns Time
  public ZonedDateTime getTime() {
      return this.time;

  }

  // Returns Lattitude
  public double getLatitude() {

    return this.lattitude;

  }

  // Returns Longitude
  public double getLongitude() {

    return this.longitude;
    
  }

  // Returns Elevation
  public double getElevation() {
    return this.elevation;
    
  }

  // Returns the point as a string
  public String toString() {

    return String.format("(%.5f, %.5f), %.1f m", getLongitude(), getLatitude(), getElevation());

  }

  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
