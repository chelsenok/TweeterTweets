package search;

import tweet.Tweet;

public interface SearchListener {

    //returns operation exit status
    int onTweetReady(Tweet tweet);

}
