import search.Search;
import search.SearchListener;

public class Main {

    private static final SearchListener listener = (statuses, from) -> {
        System.out.println("Get " + statuses.size() + " from " + from);
//        d
    };

    public static void main(String[] args) {
//        Double strings = new AssignRating().assignRatingToTweet("@PaoloAsteggiano love");
//        System.out.println("Final value " + strings);
        new Search("trump", listener).start();
    }
}
