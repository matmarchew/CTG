package matmarchew.clientapp;

public class Client {
    private final ClientSocket clientSocket;
    private boolean isReadyToSend;

    public Client(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        isReadyToSend = false;
    }

    public CustomJSONObject receiveMessage() {
        String message = clientSocket.receiveMessage();
        CustomJSONObject json = new CustomJSONObject();
        json.getJSONObjectFromString(message);
        changeState(json);
        return json;
    }

    public void sendMessage(CustomJSONObject json) {
        changeState(json);
        if(isReadyToSend) clientSocket.sendMessage(json.toString());
    }

    private void changeState(CustomJSONObject json) {
        String state = json.getString(Messages.STATE);
        if(state.equals(Messages.START)) {
            isReadyToSend = true;
        }

        if(state.equals(Messages.MOVE)) {
            isReadyToSend = false;
        }
    }

    public boolean isReady() {
        return isReadyToSend;
    }
}
