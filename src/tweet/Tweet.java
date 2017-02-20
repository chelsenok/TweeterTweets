package tweet;

public class Tweet {

    private String text;
    private double lat;
    private double lon;
    private int likes;
    private int retweets;

    public int getLikes() {
        return likes;
    }

    public int getRetweets() {
        return retweets;
    }

    public String getText() {
        return text;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public Tweet(String text, double lat, double lon, int likes, int retweets) {
        this.text = text;
        this.lat = lat;
        this.lon = lon;
        this.likes = likes;
        this.retweets = retweets;
    }

}
