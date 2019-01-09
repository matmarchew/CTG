package communication.observer;

import communication.WebPageSocket;

public abstract class Observer {
    private final WebPageSocket webPageSocket;

    protected Observer(WebPageSocket webPageSocket) {
        this.webPageSocket = webPageSocket;
    }

    protected void sendMessageToWeb(String message) {
        webPageSocket.sendMessageToWebPage(message);
    }
}
