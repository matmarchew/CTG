package matmarchew.clientapp;

public class Client {
    private final ClientSocket clientSocket;
    private boolean isReadyToSend;

    public Client(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
        isReadyToSend = true;
    }

    public CustomJSONObject receiveMessage() {
        String message = clientSocket.receiveMessage();
        CustomJSONObject json = new CustomJSONObject();
        json.getJSONObjectFromString(message);
        changeState(json);
        return json;
    }

    public void sendMessage(CustomJSONObject json) {
        if(isReadyToSend) {
            clientSocket.sendMessage(json.toString());
            changeState(json);
        }
    }

    private void changeState(CustomJSONObject json) {
        isReadyToSend = json.getString(Messages.STATE).equals(Messages.START);
    }

    public boolean isReady() {
        return isReadyToSend;
    }
}
