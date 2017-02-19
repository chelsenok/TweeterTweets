package search;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class SearchAPI {

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

    private static final int SESSION_TWEETS_NUMBER = 10;

    private static Twitter sTwitter;

    private SearchListener mListener;
    private String mQuery;

    static {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("1DbQ9cgiYi83QvRlZYN6G0EhB")
                .setOAuthConsumerSecret("FFvrv9QZdOIGLNnzxpxEFtSkBgrDWph6TvFpC1QwMoYoY2Ztx1")
                .setOAuthAccessToken("385498014-3ODBkhcxlIO7qthICAJLJ2MzJkdGqR0pMkMgChqC")
                .setOAuthAccessTokenSecret("j6WMid9xGO8QVBSkwUlvRDbpYieR6BBqkBm27YCZmEIAq");
        TwitterFactory tf = new TwitterFactory(cb.build());
        sTwitter = tf.getInstance();
    }

    public SearchAPI(String query, SearchListener listener) {
        mListener = listener;
        mQuery = query;
    }

    public void start() {
        for (UnitedStatesZones zone :
                UnitedStatesZones.values()) {
            new Thread(() -> {

                Query twitterQuery = new Query(mQuery);
                twitterQuery.setGeoCode(zone.geoLocation(), zone.radius(), Query.Unit.km);
                twitterQuery.setCount(SESSION_TWEETS_NUMBER);

                long lastID = Long.MAX_VALUE;
                while (true) {
                    try {
                        List<Status> newStatuses = sTwitter.search(twitterQuery).getTweets();

                        for (Status t : newStatuses) {
                            if (t.getId() < lastID) {
                                lastID = t.getId();
                            }
                            if (t.getGeoLocation() != null) {
                                mListener.onStatusesReady(t);
                                System.out.println(zone.name());
                            }
                        }

                        twitterQuery.setMaxId(lastID - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }).start();
        }
    }
}
