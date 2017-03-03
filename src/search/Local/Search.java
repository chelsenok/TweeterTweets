package search.Local;

import org.jetbrains.annotations.Nullable;
import search.SearchListener;
import sun.misc.FloatingDecimal;
import tweet.Tweet;
import tweet.TweetParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class Search implements TweetParser {

    private Query mQuery;
    private SearchListener mListener;
    private Thread mThread;

    public Thread getThread() {
        return mThread;
    }

    public Search(Query query, SearchListener listener) {
        mQuery = query;
        mListener = listener;
    }

    public void start() {
        new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new FileReader(mQuery.path()))) {
                for (String line; (line = br.readLine()) != null; ) {
                    if (mListener.onTweetReady(get(line)) == 1) {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    @Nullable
    @Override
    public Tweet get(String line) {String text;
        float lat;
        float lon;
        try {
            String[] parts = line.split("\t");

            String[] geo = parts[0].replace("[", "").replace("]", "").split(", ");
            lat = Float.parseFloat(geo[0]);
            lon = Float.parseFloat(geo[1]);

            return new Tweet(parts[3], lat, lon);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
