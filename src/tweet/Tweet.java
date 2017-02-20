package tweet;

public class Tweet {

    private String text;
    private double lat;
    private double lon;

    public String getText() {
        return text;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public Tweet(String text, double lat, double lon) {
        this.text = text;
        this.lat = lat;
        this.lon = lon;
    }

}
