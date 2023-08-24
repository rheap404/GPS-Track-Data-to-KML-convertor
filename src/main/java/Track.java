/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Rhea Prakash
 */

 import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;


import java.time.ZonedDateTime;

public class Track {

  List<Point> lines = new ArrayList<Point>();

  Track() {}

  Track(String filename) throws FileNotFoundException {

    this.readFile(filename);
   
  }

  // Reads the given file and splits the data based on placement of ','
  /**
   * @param filename
   * @throws FileNotFoundException
   */

  public void readFile (String filename) throws FileNotFoundException  {
    
   lines.clear();
  
   File obj= new File(filename);
   try (Scanner read = new Scanner(obj)) {
    
   read.nextLine();

    while (read.hasNextLine()) {

     String data = read.nextLine();
     String[] section = data.split(",");

     if( section.length < 4 ) {
     throw new GPSException("Invalid number of inputs");}

     Point point = new Point(ZonedDateTime.parse(section[0]), Double.parseDouble(section[1]), Double.parseDouble(section[2]), Double.parseDouble(section[3]));
     this.add(point);

   }
  
  read.close();
 
  } catch (NumberFormatException e) {
     e.printStackTrace();
    }
  }


  public void add(Point point) {
    lines.add(point);
  }

   
  public Point get(int index) {

    if(index>3 || index<0)
    throw new GPSException("Invalid index");

    if( this.size()== 0 )
    throw new GPSException("Empty Track");

    return lines.get(index);

  }

 
  public int size() {
   return lines.size();
  }

  
 // Method uses a loop to search for the lowest point.
 // It compares the first elevation value to the others and stores the smaller one.
  public Point lowestPoint() {
   
    if( this.size()== 0 )
     throw new GPSException("Empty Track");

    Point temp = lines.get(0);
    double min = temp.getElevation();
    int store = 0;
    
    for (int i = 0; i < lines.size(); i++) {
     temp = lines.get(i);
     
     if( temp.getElevation()< min) {
       min = temp.getElevation();
        store = i;

       }
    }
    
    temp = lines.get(store);
     
    return temp;

  }


  // Method uses a loop to search for the highest point.
 // It compares the first elevation value to the others and stores the higher one. 
  public Point highestPoint() {

    if( this.size()== 0 )
    throw new GPSException("Empty Track");

    Point temp = lines.get(0);
    double max = temp.getElevation();
    
    int store = 0;
     for (int i = 0; i < lines.size(); i++) {
       temp = lines.get(i);
       if( temp.getElevation()> max) {
        max = temp.getElevation();
        store = i;
       }

      }
     temp = lines.get(store);
     
    return temp;

  }


  // Method creates two points and uses a loop over ArrayList lines
  // And uses greatCirlceDistance function to calculate distance between those points and adds it to the total
  public double totalDistance() {

    Point p;
    Point q;
    double totalDistance=0.0;

    for(int i=0; (i+1)<lines.size(); i++ ) {

     p = lines.get(i);
     q = lines.get(i+1);

     double distance = Point.greatCircleDistance(p, q);
     totalDistance+=distance;
    
    }

   if (totalDistance==0)
   throw new  GPSException("Less than two points");
    
   return totalDistance;

  }


  // Method ChronoUnit to calculate time between the first and last point
  // Average speed is calculated by dividing the total distance by time 
  public double averageSpeed() {
    
    Point p;
    Point q;
    double avgSpeed;
    double time;
    
    p = lines.get(0);
    q = lines.get(lines.size()-1);

    if (p == q) 
     throw new GPSException("Less than two points");

    time = ChronoUnit.SECONDS.between(p.getTime(), q.getTime());
    avgSpeed= this.totalDistance()/time;
    
    return avgSpeed;

  }
  

  // Method writes into the given file in KMl file format
  // Using a loop to write coordinates of every point into KML file
  public void writeKMl(String filename){
    try {
      FileWriter KML = new FileWriter(filename);
      
      String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
      "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"+
      "<Document>\n"+
      "<Placemark>\n"+
      "<LineString>\n"+
      "coordinates>\n";

      KML.write(kmlstart); 
  
      for (int i = 0; i < lines.size(); i++) {
      
       Point temp = lines.get(i);
       String kmlelement = temp.getLongitude()+","+temp.getLatitude()+"\n" ;

       KML.write(kmlelement);

      }

      KML.write("</coordinates>\n");
      KML.write("</LineString>\n");

      String Line = "<Style>\n"+
       "<LineStyle>\n"+ 
       "<color>#ff0000ff</color>\n"+
       "<width>2</width>\n"+
       "</LineStyle>\n"+
       "</Style>\n";

      KML.write(Line);
    
      KML.write("</Placemark>\n");
      KML.write("</Document>\n");
      KML.write("</kml>");

      KML.close();
  
     } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
