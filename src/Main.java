import gmap.geocoding.AreaLevel;
import gmap.geocoding.JSONResponse;
import gmap.geocoding.Location;
import search.Search;
import search.SearchListener;

public class Main {

    private static final SearchListener listener = status -> {
//        do work
    };

    public static void main(String[] args) {
        JSONResponse location = new Location().getLocation(-28.565576, 131.797518, AreaLevel.AREA_LEVEL_1);
        if(location.getStatusCodes().toString() == "OK") {
            System.out.println(location.getStateLongName());
            System.out.println(location.getStateShortName());
        } else {
            System.out.println("ERROR: " + location.getStatusCodes());
        }
//        new Search("car", listener).start();
    }
}
