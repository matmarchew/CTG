package matmarchew.clientapp;

public class ClientHandler {
    private static Client client;

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        ClientHandler.client = client;
    }
}
