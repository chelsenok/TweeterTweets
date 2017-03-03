package gmap.JSONParser;

import java.util.ArrayList;

public class Offseter {

    private int mOffsetX;
    private int mOffsetY;
    private String mKey;
    private ArrayList<Offseter> mOffseters;

    public int getOffsetX() {
        return mOffsetX;
    }

    public int getOffsetY() {
        return mOffsetY;
    }

    public String getKey() {
        return mKey;
    }

    public ArrayList<Offseter> getOffseters() {
        return mOffseters;
    }

    public Offseter(int offsetX, int offsetY, String key, ArrayList<Offseter> offseters) {
        mOffsetX = offsetX;
        mOffsetY = offsetY;
        mKey = key;
        mOffseters = offseters;
    }
}
