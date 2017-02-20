package tweet;

public class Tweet {

    private String mText;
    private double mLat;
    private double mLon;

    public String getText() {
        return mText;
    }

    public double getLat() {
        return mLat;
    }

    public double getLon() {
        return mLon;
    }

    public Tweet(String text, double lat, double lon) {
        this.mText = text;
        this.mLat = lat;
        this.mLon = lon;
    }

}
