package tweet;

public class Tweet {

    private String mText;
    private float mLat;
    private float mLon;

    public String getText() {
        return mText;
    }

    public float getLat() {
        return mLat;
    }

    public float getLon() {
        return mLon;
    }

    public Tweet(String text, float lat, float lon) {
        this.mText = text;
        this.mLat = lat;
        this.mLon = lon;
    }

}
