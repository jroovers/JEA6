package rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Jeroen Roovers <jroovers>
 */
public class UrlTester {

    public static boolean testURL(String strUrl, int expectedStatusCode) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.connect();

            if (expectedStatusCode == urlConn.getResponseCode()) {
                return true;
            }
            return false;
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            return false;
        }
    }
}
