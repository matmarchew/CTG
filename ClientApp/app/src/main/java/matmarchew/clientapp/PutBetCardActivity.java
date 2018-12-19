package matmarchew.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class PutBetCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_bet_card);

        CustomJSONObject json = ClientHandler.getClient().receiveMessage();

        //String message = "{\"" + Messages.BET_CARD + "\":[{\"" + Messages.COLOR + "\":\"ORANGE\"}, {\"" + Messages.COLOR + "\":\"GREEN\"}]}";
        JSONArray jsonArray = json.getJSONArray(Messages.BET_CARD);

        Map<String, Button> buttons = new HashMap<>();
        buttons.put("ORANGE", findViewById(R.id.ORANGE_BET_CARD));
        buttons.put("BLUE", findViewById(R.id.BLUE_BET_CARD));
        buttons.put("YELLOW", findViewById(R.id.YELLOW_BET_CARD));
        buttons.put("GREEN", findViewById(R.id.GREEN_BET_CARD));
        buttons.put("WHITE", findViewById(R.id.WHITE_BET_CARD));

        manageUsedButtons(jsonArray, buttons);

        for (String color : buttons.keySet()) {
            onClick(buttons.get(color), color);
        }
    }

    private void manageUsedButtons(JSONArray jsonArray, Map<String, Button> buttons) {
        for (int i = 0; i < jsonArray.length(); i++) {
            CustomJSONObject json = new CustomJSONObject();
            json.getJSONObjectFromJSONArray(jsonArray, i);
            String color = json.getString(Messages.COLOR);
            Button button = buttons.remove(color);
            button.setBackground(getResources().getDrawable(R.drawable.set_used_button));
        }
    }

    private void onClick(Button button, String color) {
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChooseStackActivity.class);
            intent.putExtra(Messages.COLOR, color);
            startActivity(intent);
            finish();
        });
    }
}
