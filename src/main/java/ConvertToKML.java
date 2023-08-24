
import java.io.FileNotFoundException;

/**
 * Program to general a KML file from GPS track data stored in a file,
 * for the Advanced task of COMP1721 Coursework 1.
 *
 * @author RHEA PRAKASH
 **/


public class ConvertToKML {
  public static void main(String[] args) throws FileNotFoundException {

     if( args.length != 2 )
     throw new GPSException("Incorrect number of inputs");

     Track track = new Track(args[0]);
     
     track.writeKMl(args[1]);

 }
}
 