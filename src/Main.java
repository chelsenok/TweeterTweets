import search.SearchListener;
import search.SearchAPI;

public class Main {

    private static SearchListener listener = status -> {
//        do work
    };

    public static void main(String... args) {
        new SearchAPI("friend", listener).start();
    }

}
