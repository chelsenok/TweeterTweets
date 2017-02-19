package rating;

import java.util.LinkedHashMap;

public class AssignRating {

    private final String DICTIONARY_NAME = "sentiments.csv";

    private final LinkedHashMap<String, Double> sentiments;

    // TODO: 17.02.2017 make list of on/at/in/the/a to concatenate words into collocation
    // TODO: 19.02.2017 count retweets and likes
    public AssignRating() {
        sentiments = new Reader().readSentiments(DICTIONARY_NAME);
    }

    public double assignRatingToTweet(String text) {

        String[] tweetWords = new ParseTweet().cleanTweet(text.toLowerCase());

        double value = 0;
        for (String str : tweetWords) {
            value += getWordAssign(str);
        }

        return value;
    }

    private double getWordAssign(String str) {
        try{
            return sentiments.get(str);
        } catch (NullPointerException exc) {
            return 0;
        }
    }
}
