import search.SearchListener;
import search.SearchLocal;

public class Main {

    private static SearchListener listener = tweet -> new Thread(() -> {
//        do work
        System.out.println(tweet.getText());
    }).start();

    public static void main(String... args) {
//        new SearchAPI("friend", listener).start();
        new SearchLocal(SearchLocal.Query.AllTweets, listener).start();
    }
}
