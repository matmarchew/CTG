package rules.players;

import java.io.*;
import java.net.Socket;

public class PlayerSocket {
    private final Socket socket;

    public PlayerSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendMessageToAndroid(String message) {
        try {
            OutputStreamWriter playerStreamOutput = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bW = new BufferedWriter(playerStreamOutput);
            PrintWriter pW = new PrintWriter(bW, true);
            if (pW != null && !pW.checkError())
                pW.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessageFromAndroid() {
        String message = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = in.readLine();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
