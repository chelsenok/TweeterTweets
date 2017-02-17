package rating;

import jdk.nashorn.api.scripting.URLReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ParseTweet {

//    private final String URL_REGEX = "/^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$/";
    private final String URL_REGEX = "http";

    String[] cleanTweet(String text) {
        text = leaveLetters(text);
        String words[] = text.split(" ");
        words = removeLinks(words);
        return words;
    }

    private String leaveLetters(String text){
        return text.replaceAll("[^'a-zA-Z ]", "");
    }

    private String[] removeLinks(String[] text) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(text));

        strings.removeIf(s -> s.contains(URL_REGEX));

        return strings.toArray(new String[]{});
    }
}
