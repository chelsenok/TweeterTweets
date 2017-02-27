package gmap.JSONParser;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Reader {

    private ArrayList<AreaToDraw> prevQuery;
    private String prevFileName;

    public ArrayList<AreaToDraw> readPoints(String fileName) {
        if (!prevFileName.equals(fileName)) {
            prevFileName = fileName;
            try (Scanner scanner = new Scanner(new FileReader(fileName))) {
                prevQuery = parseJson(scanner.nextLine());

            } catch (FileNotFoundException | JSONException e) {
                prevQuery = null;
            }
        }

        return prevQuery;
    }

    private ArrayList<AreaToDraw> parseJson(String text) throws JSONException {
        JSONObject states = new JSONObject(text);
        Iterator keys = states.keys();
        ArrayList<AreaToDraw> USA = new ArrayList<>();
        while (keys.hasNext()) {
            String key = keys.next().toString();

            int offsetX = 1200;
            int offsetY = -500;

            int sizeZoom = 17;

            switch (key) {
                case "AK":
                    offsetY += 150;
                    break;
                case "HI":
                    offsetY -= 200;
                    break;
                default:
            }

            JSONArray statesPoints = states.getJSONArray(key);
            ArrayList<ArrayList<Point>> stateCoordinate = new ArrayList<>();
            for (int z = 0; !statesPoints.isNull(z); z++) {
                JSONArray stateIslands = statesPoints.getJSONArray(z);
                ArrayList<Point> stateIslandsPoints = new ArrayList<>();
                try {
                    for (int q = 0; !stateIslands.isNull(q); q++) {
                        JSONArray arrayState = stateIslands.getJSONArray(q);
                        String X = arrayState.get(0).toString();
                        String Y = arrayState.get(1).toString();
                        stateIslandsPoints.add(new Point((int) (1800 - Math.abs(Double.valueOf(X)) * sizeZoom) + offsetX, (int) (Math.abs(100 - Double.valueOf(Y)) * sizeZoom) + offsetY));
                    }
                } catch (JSONException ignored) {
                }
                stateCoordinate.add(stateIslandsPoints);
            }
            USA.add(new AreaToDraw(stateCoordinate, key));

        }
        return USA;
    }
}