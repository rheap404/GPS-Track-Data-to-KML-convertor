# GPS Track Data to KML Converter

The GPS Track Data to KML Converter is a Java-based command-line utility designed to convert GPS track data recorded by a GPS sensor into KML (Keyhole Markup Language) format. This utility is developed as part of the Advanced task for the COMP1721 Coursework 1 assignment.
<br>
GPS track data often contains a series of geographical coordinates, timestamps, and elevation information that, when visualized, can represent a path or journey on a map. The KML format is widely supported by mapping tools, such as Google Earth, allowing you to visualize the path on a map.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Usage](#usage)


## Features

GPS Track Data Conversion: The core functionality of this utility lies in its ability to seamlessly convert raw GPS track data, captured by a GPS sensor, into a standardized KML format. 

Information: Provides findings of the lowest and highest points, calculating distances and average speeds, and handling exceptions for insufficient data

Process Timestamped GPS Points: The utility is designed to handle GPS track data that includes crucial timestamp information. Each recorded point in your input file is associated with a timestamp that reflects when the data was captured.

Geographical Coordinates and Elevation: The converter processes each GPS data point, extracting vital information such as longitude, latitude, and elevation. This rich dataset allows the utility to accurately plot your path over varying terrains and altitudes. 

Visualization: Generate a KML file that can be imported into mapping tools like Google Earth for visualizing the GPS track on a map. This powerful feature empowers you to explore your GPS track in a highly interactive and visually engaging manner.

Potential Educational and Analytical Applications: Researchers, adventurers, and educators can leverage the utility to analyze paths taken during specific time periods, study elevation changes, or even compare multiple journeys on the same map.


## Usage

To use the converter, follow these steps:

1. Clone the repository:
   
    git clone https://github.com/rhep404/gps-track-to-kml-converter.git

3. Navigate to the Project Directory:

     cd gps-track-to-kml-converter
   
3. Compile the Java Files: Compile the Java files in the project directory:

     javac *.java

4. Run the Converter: Execute the converter with the input and output file paths as arguments:

     java ConvertToKML input.txt output.kml

Replace input.txt with the path to your GPS track data file and output.kml with the desired name for the generated KML file.


## Example GPS Track Data
Assuming your input GPS track data file (input.txt) follows the same format as mentioned in the provided code: <br>
2022-02-17T09:52:39Z,-1.547720,53.803941,69.8 <br>
2022-02-17T09:53:31Z,-1.548531,53.804616,72.5 <br>
2022-02-17T09:54:29Z,-1.549418,53.805238,68.1 <br>
2022-02-17T09:55:31Z,-1.550828,53.805469,70.5 <br>

<img width="1294" alt="KML File- Google Map" src="https://github.com/rheap404/GPS-Track-Data-to-KML-convertor/assets/100610565/5b42154b-ec06-454c-aa5b-c498dda4c0ef">




The converter will process this data and create a KML file (output.kml) that represents the path on a map.
