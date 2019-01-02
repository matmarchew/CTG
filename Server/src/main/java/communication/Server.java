package communication;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public final int serverPort;
    private final ServerSocket serverSocket;

    public Server(int serverPort) {
        this.serverPort = serverPort;
        this.serverSocket = createServerSocket();
    }

    private ServerSocket createServerSocket() {
        try {
            return new ServerSocket(serverPort);
        } catch (IOException e) {
            return null;
        }
    }

    public Socket getNewClient() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            return null;
        }
    }
}
