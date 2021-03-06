package matmarchew.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    CustomJSONObject json = new CustomJSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClientSocket clientSocket = new ClientSocket("192.168.99.100", 4444);
        Client client = new Client(clientSocket);
        ClientHandler.setClient(client);
        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.LOGIN, "1");
        client.sendMessage(json);

        onClick(findViewById(R.id.THROW_CUBE), new Intent(this, ThrowCubeActivity.class));
        onClick(findViewById(R.id.PUT_BET_CARD), new Intent(this, PutBetCardActivity.class));
        onClick(findViewById(R.id.GET_BET_TILE), new Intent(this, GetBetTileActivity.class));
        onClick(findViewById(R.id.PUT_DESERT_TILE), new Intent(this, PutDesertTileActivity.class));
    }

    private void onClick(Button button, Intent intent) {
        button.setOnClickListener(v -> {
            if(ClientHandler.getClient().isReady()) {
                intent.putExtra("JSON", json.toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(() -> json = ClientHandler.getClient().receiveMessage()).start();
    }
}
