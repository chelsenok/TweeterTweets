package gmap.polygon.Local;

import gmap.JSONParser.AreaToDraw;
import gmap.JSONParser.Reader;

import java.awt.*;
import java.util.ArrayList;

public class Location {

    private static Location sInstance;
    private static ArrayList<AreaToDraw> sAreas;
    private static final String FILE_NAME = "res/states.json";

    private Location() {
        Reader reader = new Reader();
        sAreas = reader.readPoints(FILE_NAME);
    }

    public static Location getInstance() {
        if (sInstance == null) {
            sInstance = new Location();
        }
        return sInstance;
    }

    public String getLocation(float lat, float lon) {
        String result = "";

        for (AreaToDraw area :
                sAreas) {
            for (ArrayList<Point> points :
                    area.getArea()) {
                Polygon polygon = new Polygon();
                for (Point point :
                        points) {
//                    polygon.addPoint(point.getX(), point.getY());
                }
            }
        }

        return result;
    }
}
