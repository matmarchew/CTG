package communication;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebPageSocket {
    public void sendMessageToWebPage(String message) {
        try {
            URL url = new URL("http://172.30.0.4:8080/status/");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            httpCon.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
            out.write(message);
            out.flush();
            out.close();
            httpCon.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
