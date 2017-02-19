import gmap.geocoding.AreaLevel;
import gmap.geocoding.JSONResponse;
import gmap.geocoding.Location;
import search.Search;
import search.SearchListener;

import java.util.Objects;

public class Main {

    private static final SearchListener listener = status -> {
//        do work
    };

    public static void main(String[] args) {
        JSONResponse location = new Location().getLocation(36.174269, -95.192394, AreaLevel.AREA_LEVEL_1);
        if (Objects.equals(location.getStatusCodes().toString(), "OK")) {
            System.out.println(location.getStateLongName());
            System.out.println(location.getStateShortName());
        } else {
            System.out.println("ERROR: " + location.getStatusCodes().toString());
        }

        //new Search("dish", listener).start();
    }
}
