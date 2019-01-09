package communication;

public class WebPageSocketForTest extends WebPageSocket {
    private final CustomJSONObject json;

    public WebPageSocketForTest() {
        this.json = new CustomJSONObject();
    }

    public void sendMessageToWebPage(String message) {
        json.getJSONObjectFromString(message);
    }

    public CustomJSONObject getJson() {
        return json;
    }
}
