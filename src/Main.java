import com.sun.deploy.util.StringUtils;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import rating.AssignRating;
import rating.ParseTweet;
import search.Search;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Double strings = new AssignRating().assignRatingToTweet("@PaoloAsteggiano love it!!!! Thank YOU!!!!");
        System.out.println("Final value " + strings);








//        Search search = new Search("2016-02-10", "2016-02-12", "friend");
//        long time = System.currentTimeMillis();
//        search.start();
//        System.out.println("Hello World!  " + (System.currentTimeMillis() - time));
    }
}
