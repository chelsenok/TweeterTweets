import search.SearchListener;

public class Main {

    private static int count = 0;

    private static SearchListener listener = tweet -> {
        if (tweet != null) {
            new Thread(() -> {

                //coordinates goes to GMAP for State Verifying -> returns state

                //cleanText goes to RATING -> returns grade

                //rating with state with likes and retweets goes to MANAGER -> returns new state grade

                //new state grade goes to GMAP for drawing
            }).start();
        }
    };

    public static void main(String... args) {

    }
}