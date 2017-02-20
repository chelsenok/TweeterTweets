package rating;

import java.util.ArrayList;
import java.util.Arrays;

abstract class TextCleaner {

    private static final String URL_REGEX = "http";
    private static final String LOGIN = "@";

    public static String[] clean(String text) {
        text = leaveLetters(text);
        String words[] = text.split(" ");
        words = removeDirt(words);
        return words;
    }

    private static String leaveLetters(String text) {
        return text.replaceAll("[^'a-zA-Z@ ]", "");
    }

    private static String[] removeDirt(String[] text) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(text));

        strings.removeIf(s -> s.contains(URL_REGEX));
        strings.removeIf(s -> s.contains(LOGIN));

        return strings.toArray(new String[]{});
    }
}
