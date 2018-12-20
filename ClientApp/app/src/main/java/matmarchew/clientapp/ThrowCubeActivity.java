package matmarchew.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ThrowCubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throw_cube);

        Client client = ClientHandler.getClient();

        CustomJSONObject json = new CustomJSONObject();
        json.put(Messages.ACTION_TYPE, Messages.THROW_CUBE);
        client.sendMessage(json);

        finish();
    }
}
