package gmap.JSONParser;


import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

public class AreaToDraw {

    private ArrayList<ArrayList<Point>> mArea;
    private String mName;
    private Color mColor;

    AreaToDraw(ArrayList<ArrayList<Point>> area, String key) {
        this.mArea = area;
        this.mName = key;
        mColor = Color.rgb(240, 240, 240);
    }

    public AreaToDraw(ArrayList<ArrayList<Point>> area) {
        this.mArea = area;
    }

    public void setName(String key) {
        this.mName = key;
    }

    public ArrayList<ArrayList<Point>> getArea() {
        return mArea;
    }

    public String getName() {
        return mName;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }

    public boolean setColor(float bottom, float top, float value) {
        if (value > top || value < bottom) return false;
        else {
            if(value > 0) {
                int color = (int)(value/top*255);
                mColor = Color.rgb(color, 255, color);
            }
            else {
                int color = (int)(Math.abs(value/bottom)*255);
                mColor = Color.rgb(255, color, color);
            }
        }
        return true;
    }

    public static AreaToDraw getAreaByName(String name, ArrayList<AreaToDraw> list) {
        for (AreaToDraw area:
             list) {
            if(area.getName().equals(name)) {
                return area;
            }
        }
        return null;
    }
}
