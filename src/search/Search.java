package search;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Search {

    private final GeoLocation US_GEOLOCATION = new GeoLocation(37.1, -95.7);
    private final double RADIUS = 2800;
    private final Query.Unit UNIT = Query.Unit.km;

    private static Configuration sConfiguration;

    private StatusListener mListener;
    private String mSince;
    private String mUntil;
    private String[] mQueries;

    static {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("vE8tdIeIUwK1zXnIomehuZKd5")
                .setOAuthConsumerSecret("UPvyfZg2WMNf69a5uJqtXuYnNXlXI5ADc2EaHnypzxIrn0zmi6")
                .setOAuthAccessToken("385498014-3ODBkhcxlIO7qthICAJLJ2MzJkdGqR0pMkMgChqC")
                .setOAuthAccessTokenSecret("j6WMid9xGO8QVBSkwUlvRDbpYieR6BBqkBm27YCZmEIAq");
        sConfiguration = cb.build();
    }

    //YYYY-MM-DD
    public Search(String since, String until, String... queries) {
        mSince = since;
        mUntil = until;
        mQueries = queries;
    }

    public void addListener(StatusListener listener) {
        mListener = listener;
    }

    public void start() {
        for (String query :
                mQueries) {
            Query twitterQuery = new Query(query);
            twitterQuery.setSince(mSince);
            twitterQuery.setUntil(mUntil);
            twitterQuery.setCount(100);
            twitterQuery.setGeoCode(US_GEOLOCATION, RADIUS, UNIT);

            TwitterFactory tf = new TwitterFactory(sConfiguration);
            Twitter twitter = tf.getInstance();

            try {
                List<Status> statuses = twitter.search(twitterQuery).getTweets();
                System.out.println(statuses.size());
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
    }

}
