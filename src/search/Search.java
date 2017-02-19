package search;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class Search {

    private enum UnitedStatesZones {
        Center {
            @Override
            GeoLocation geoLocation() {
                return new GeoLocation(40.23, -97.44);
            }

            @Override
            double radius() {
                return 2800;
            }
        },
        Alaska {
            @Override
            GeoLocation geoLocation() {
                return new GeoLocation(64.88, -154.19);
            }

            @Override
            double radius() {
                return 1700;
            }
        },
        Hawaii {
            @Override
            GeoLocation geoLocation() {
                return new GeoLocation(20.96, -157.13);
            }

            @Override
            double radius() {
                return 350;
            }
        };

        abstract GeoLocation geoLocation();

        abstract double radius();
    }

    private static final int GREAT_TWEETS_NUMBER = Integer.MAX_VALUE;
    private static final int SESSION_TWEETS_NUMBER = 100;

    private static Twitter sTwitter;

    private SearchListener mListener;
    private String mQuery;

    static {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("vE8tdIeIUwK1zXnIomehuZKd5")
                .setOAuthConsumerSecret("UPvyfZg2WMNf69a5uJqtXuYnNXlXI5ADc2EaHnypzxIrn0zmi6")
                .setOAuthAccessToken("385498014-3ODBkhcxlIO7qthICAJLJ2MzJkdGqR0pMkMgChqC")
                .setOAuthAccessTokenSecret("j6WMid9xGO8QVBSkwUlvRDbpYieR6BBqkBm27YCZmEIAq");
        TwitterFactory tf = new TwitterFactory(cb.build());
        sTwitter = tf.getInstance();
    }

    public Search(String query, SearchListener listener) {
        mListener = listener;
        mQuery = query;
    }

    public void start() {
        for (UnitedStatesZones zone :
                UnitedStatesZones.values()) {
//            new Thread(() -> {

                Query twitterQuery = new Query(mQuery);
                twitterQuery.setGeoCode(zone.geoLocation(), zone.radius(), Query.Unit.km);
                twitterQuery.setCount(SESSION_TWEETS_NUMBER);

                List<Status> tweets = new ArrayList<>();
                long lastID = Long.MAX_VALUE;

                while (tweets.size() < GREAT_TWEETS_NUMBER) {
                    try {
                        QueryResult result = sTwitter.search(twitterQuery);
                        List<Status> newStatuses = result.getTweets();

                        for (Status t : newStatuses) {
                            if (t.getId() < lastID) {
                                lastID = t.getId();
                            }
                            if (t.getGeoLocation() != null) {
                                tweets.add(t);
                                if (tweets.size() == SESSION_TWEETS_NUMBER) {
                                    mListener.onStatusesReady(new ArrayList<>(tweets), zone.name());
                                    tweets.clear();
                                }
                            }
                        }
                        twitterQuery.setMaxId(lastID - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        mListener.onStatusesReady(new ArrayList<>(tweets), zone.name());
                        return;
                    }
                }
//            }).start();
        }
    }
}
