package matmarchew.clientapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket extends AsyncTask<String, String, String>{
    private final String serverUrl;
    private final int serverPort;
    private Socket server;

    public ClientSocket(String serverUrl, int serverPort) {
        this.serverUrl = serverUrl;
        this.serverPort = serverPort;
    }

    private Socket connectToServer(String serverUrl, int serverPort) {
        try {
            return new Socket(serverUrl, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            return new Socket();
        }
    }

    public String receiveMessage() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void sendMessage(String message) {
        try {
            while(server == null);
            OutputStreamWriter playerStreamOutput = new OutputStreamWriter(server.getOutputStream());
            BufferedWriter bW = new BufferedWriter(playerStreamOutput);
            PrintWriter pW = new PrintWriter(bW, true);
            Thread send = new Thread(() -> pW.println(message));
            send.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        server = connectToServer(serverUrl, serverPort);
        while(true) ClientHandler.getClient().receiveMessage();
    }
}
