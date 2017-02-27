package gmap.JSONParser;

import java.awt.*;
import java.util.ArrayList;

public class AreaToDraw {

    private ArrayList<ArrayList<Point>> area;
    private String key;

    AreaToDraw(ArrayList<ArrayList<Point>> area, String key) {
        this.area = area;
        this.key = key;
    }

    public AreaToDraw(ArrayList<ArrayList<Point>> area) {
        this.area = area;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<ArrayList<Point>> getArea() {
        return area;
    }

    public String getKey() {
        return key;
    }
}
