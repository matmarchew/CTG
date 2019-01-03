package communication.observer;

import communication.WebPageSocket;

public abstract class Observer {
    protected void sendMessageToWeb(String message) {
        WebPageSocket.sendMessageToWebPage(message);
    }
}
