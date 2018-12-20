package matmarchew.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class GetBetTileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bet_tile);

        CustomJSONObject json = new CustomJSONObject();
        json.getJSONObjectFromString(getIntent().getStringExtra("JSON"));

        JSONArray jsonArray = json.getJSONArray(Messages.BET_TILES);

        Map<String, Button> buttons = new HashMap<>();
        buttons.put("ORANGE", findViewById(R.id.ORANGE_BET_TILE));
        buttons.put("BLUE", findViewById(R.id.BLUE_BET_TILE));
        buttons.put("YELLOW", findViewById(R.id.YELLOW_BET_TILE));
        buttons.put("GREEN", findViewById(R.id.GREEN_BET_TILE));
        buttons.put("WHITE", findViewById(R.id.WHITE_BET_TILE));

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
            String value = json.getString(Messages.VALUE);
            Button button = buttons.get(color);
            if(value.equals("0")) {
                button.setBackground(getResources().getDrawable(R.drawable.set_used_button));
                buttons.remove(color);
            }
            button.setText(button.getText() + "\n value: " + value);
        }
    }

    private void onClick(Button button, String color) {
        button.setOnClickListener(v -> {
            CustomJSONObject json = new CustomJSONObject();
            json.put(Messages.ACTION_TYPE, Messages.GET_BET_TILE);
            json.put(Messages.COLOR, color);
            ClientHandler.getClient().sendMessage(json);
            finish();
        });
    }
}
