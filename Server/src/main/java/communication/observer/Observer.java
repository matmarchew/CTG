package communication.observer;

import communication.WebPageSocket;

public abstract class Observer {
    protected void sendMessageToWeb(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebPageSocket.sendMessageToWebPage(message);
    }
}
