package search.API;

import search.SearchListener;
import tweet.Tweet;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.HashMap;
import java.util.List;

public class Search {

    private static final int SESSION_TWEETS_NUMBER = 200;

    private static HashMap<UnitedStatesZones, Twitter> mZoneTwitterMap;

    private SearchListener mListener;
    private String mQuery;

    static {
        String[][] keys = new String[][] {
                new String[] {
                        "1DbQ9cgiYi83QvRlZYN6G0EhB",
                        "FFvrv9QZdOIGLNnzxpxEFtSkBgrDWph6TvFpC1QwMoYoY2Ztx1",
                        "385498014-3ODBkhcxlIO7qthICAJLJ2MzJkdGqR0pMkMgChqC",
                        "j6WMid9xGO8QVBSkwUlvRDbpYieR6BBqkBm27YCZmEIAq"
                },
                new String[] {
                        "kckzrpA9tluf90LxTpbCtqYXz",
                        "I4tOgYFpb0rWcmgIKRmveiMX5GgawGFG0frm1OPZXDZdiP2R4S",
                        "833740043906867201-DH2M9YckikXvjSN3UvwIA9gRdFWRQbW",
                        "YI0DVZhcaXOrivHQJX2S5nRjMdTxo1vbGsMKq1PzI6yfy"
                },
                new String[] {
                        "1h2HqkBxUrcc7aLGq1z4nb4be",
                        "WmYH05Y8r4yZWkxP3cxTOrf2a6LrpB8uRlY9kSnp1QQ0v7PArS",
                        "818233763188576263-Ua3qzYPvvHVdIo2hOzXLWJ2CdvsLSVB",
                        "jChptqsWVvghEZWhznSXkMKJ1kdMPIqkDhUwPUyd1cvIS"
                }
        };

        mZoneTwitterMap = new HashMap<>();
        int count = 0;
        for (UnitedStatesZones zone :
                UnitedStatesZones.values()) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(keys[count % keys.length][0])
                    .setOAuthConsumerSecret(keys[count % keys.length][1])
                    .setOAuthAccessToken(keys[count % keys.length][2])
                    .setOAuthAccessTokenSecret(keys[count % keys.length][3]);
            mZoneTwitterMap.put(zone, new TwitterFactory(cb.build()).getInstance());
            count++;
        }
    }

    public Search(String query, SearchListener listener) {
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
                        List<Status> newStatuses = mZoneTwitterMap.get(zone).search(twitterQuery).getTweets();

                        for (Status t : newStatuses) {
                            if (t.getId() < lastID) {
                                lastID = t.getId();
                            }
                            if (t.getGeoLocation() != null) {
                                mListener.onTweetReady(new Tweet(
                                        t.getText(),
                                        t.getGeoLocation().getLatitude(),
                                        t.getGeoLocation().getLongitude()
                                ));
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
