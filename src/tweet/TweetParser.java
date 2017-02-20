package tweet;

public abstract class TweetParser {

    public static Tweet get(String line) {
        String text = "";
        double lat = 0;
        double lon = 0;
        try {
            String[] parts = line.split("\t");

            String[] geo = parts[0].replace("[", "").replace("]", "").split(", ");
            lat = Double.parseDouble(geo[0]);
            lon = Double.parseDouble(geo[1]);

            text = parts[3].replaceAll("@[^ ]* ", "");
        } catch (Exception ignored) {
        }
        return new Tweet(text, lat, lon);
    }

}
