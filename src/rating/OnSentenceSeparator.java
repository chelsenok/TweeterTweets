package rating;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class OnSentenceSeparator {

    public static ArrayList<String> separate(String str) {
        Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher reMatcher = re.matcher(str);
        ArrayList<String> array = new ArrayList<>();
        while (reMatcher.find()) {
            array.add(reMatcher.group());
        }

        return array;
    }

}
