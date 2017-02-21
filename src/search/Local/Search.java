package search.Local;

import org.jetbrains.annotations.Nullable;
import search.SearchListener;
import tweet.Tweet;
import tweet.TweetParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class Search implements TweetParser {

    private Query mQuery;
    private SearchListener mListener;

    public Search(Query query, SearchListener listener) {
        mQuery = query;
        mListener = listener;
    }

    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(mQuery.path()))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (mListener.onTweetReady(get(line)) == 1) {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public Tweet get(String line) {String text;
        double lat;
        double lon;
        try {
            String[] parts = line.split("\t");

            String[] geo = parts[0].replace("[", "").replace("]", "").split(", ");
            lat = Double.parseDouble(geo[0]);
            lon = Double.parseDouble(geo[1]);

            return new Tweet(parts[3], lat, lon);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
