package search;

import tweet.TweetParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class SearchLocal {

    public enum Query {
        MyLife {
            @Override
            String path() {
                return "res/my_life.txt";
            }
        },
        Obama {
            @Override
            String path() {
                return "res/obama.txt";
            }
        },
        AllTweets {
            @Override
            String path() {
                return "res/all_tweets.txt";
            }
        },
        MyJob {
            @Override
            String path() {
                return "res/my_job.txt";
            }
        },
        Sandwich {
            @Override
            String path() {
                return "res/sandwich.txt";
            }
        },
        Texas {
            @Override
            String path() {
                return "res/texas.txt";
            }
        };

        abstract String path();
    }

    private Query mQuery;
    private SearchListener mListener;

    public SearchLocal(Query query, SearchListener listener) {
        mQuery = query;
        mListener = listener;
    }

    public void start() {
        try (BufferedReader br = new BufferedReader(new FileReader(mQuery.path()))) {
            for (String line; (line = br.readLine()) != null; ) {
                mListener.onTweetReady(TweetParser.get(line));
            }
        } catch (Exception ignored) {
        }
    }

}
