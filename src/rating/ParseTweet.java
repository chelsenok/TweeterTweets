package rating;

import java.util.ArrayList;
import java.util.Arrays;

class ParseTweet {

//        private final String URL_REGEX = "/^(https?:\\/\\/)?([\\w\\.]+)\\.([a-z]{2,6}\\.?)(\\/[\\w\\.]*)*\\/?$/";
    private final String URL_REGEX = "http";
    private final String LOGIN = "@";

    String[] cleanTweet(String text) {
        text = leaveLetters(text);
        String words[] = text.split(" ");
        words = removeDirt(words);
        return words;
    }

    private String leaveLetters(String text) {
        return text.replaceAll("[^'a-zA-Z@ ]", "");
    }

    private String[] removeDirt(String[] text) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(text));

        strings.removeIf(s -> s.contains(URL_REGEX));
        strings.removeIf(s -> s.contains(LOGIN));

        return strings.toArray(new String[]{});
    }
}
