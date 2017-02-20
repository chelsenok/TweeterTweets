import gmap.geocoding.AreaLevel;
import rating.AssignRating;
import search.Local.Query;
import search.Local.Search;
import search.SearchListener;
import tweet.TextCleaner;

public class Main {

    private static int count = 0;

    private static SearchListener listener = tweet -> {
        if (tweet != null) {
            new Thread(() -> {

                String cleanText = TextCleaner.clean(tweet.getText());
                System.out.println(cleanText);
                count++;
                System.out.println(count);

                //coordinates goes to GMAP for State Verifying -> returns state

                //cleanText goes to RATING -> returns grade

                //rating with state with likes and retweets goes to MANAGER -> returns new state grade

                //new state grade goes to GMAP for drawing
            }).start();
        }
    };

    public static void main(String... args) {
//        double value = new AssignRating().assignRatingToTweet("Nick is twitting about this message");
//        System.out.println(value);
        new AssignRating().assignRatingToTweet("@Her https://t.co/adlkj look here, and @You look out http://bloomberg.com");
//        new Search("friend", listener).start();
//        new Search(Query.Sandwich, listener).start();
    }
}