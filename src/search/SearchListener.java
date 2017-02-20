package search;

import tweet.Tweet;

public interface SearchListener {

    void onTweetReady(Tweet tweet);

}
