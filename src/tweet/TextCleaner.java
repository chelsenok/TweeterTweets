package tweet;

public abstract class TextCleaner {

    public static String clean(String tweet) {
        String result = tweet;

        String[] dirt = new String[] { "@", "http://", "https://" };
        for (String el :
                dirt) {
            result = result.replaceAll(el + "[^ ]* ", "");
            result = result.replaceAll(el + "[^ ]*", "");
        }

        return result;
    }
}
