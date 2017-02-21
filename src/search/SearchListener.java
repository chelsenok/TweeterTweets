package search;

import tweet.Tweet;

public interface SearchListener {

    int onTweetReady(Tweet tweet);

}
