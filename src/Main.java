import search.Search;
import search.SearchListener;

public class Main {

    private static final SearchListener listener = status -> {
//        do work
    };

    public static void main(String[] args) {
//        Double strings = new AssignRating().assignRatingToTweet("@PaoloAsteggiano love");
//        System.out.println("Final value " + strings);
        new Search("dish", listener).start();
    }
}
