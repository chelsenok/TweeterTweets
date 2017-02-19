import twitter4j.*;
import twitter4j.Status;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Main {

    public static void main(String... args) {
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
