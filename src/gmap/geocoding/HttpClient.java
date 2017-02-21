package gmap.geocoding;

import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class HttpClient {


    JSONObject getJSON(final String URL) {
        try {
            return new JSONObject(getText(URL));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getText(final String URL) {
        String text = "";

        try {
            HttpURLConnection connection = ((HttpURLConnection) new URL(URL).openConnection());

            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {
                text += line;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
