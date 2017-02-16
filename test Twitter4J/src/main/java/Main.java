import twitter4j.*;
import twitter4j.Status;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Main {

    public static void main(String... args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("5a1aVko1lY1tI63v9j8i5Jkub")
                .setOAuthConsumerSecret("0c4dOURgIx5nBe3ELFpEtc2LHxVbVCPJ1dEsZMzFq96y1y0H8e")
                .setOAuthAccessToken("831584849790111744-rPKZZfgm5Md9BHeu6uNkN89thlG05HI")
                .setOAuthAccessTokenSecret("89ueDpgsD6A7DadRIa8ZKcVrERga6pFwVJd1Kq3QflK8v");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        List<Status> statuses = null;
        long was = 0;
        try {
            was = System.currentTimeMillis();
            Query query = new Query("trump");
            query.setGeoCode(new GeoLocation(37.1, -95.7), 2800, Query.Unit.km);
            query.setCount(100);
            statuses = twitter.search(query).getTweets();
            was = (System.currentTimeMillis() - was) % 1000;
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getUser().getLocation());
        }

        System.out.println("*********************   " + was + "   **************************");
    }

}
