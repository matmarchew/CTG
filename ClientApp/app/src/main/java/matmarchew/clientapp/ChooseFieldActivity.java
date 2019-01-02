package matmarchew.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ChooseFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_field);

        String page = getIntent().getStringExtra(Messages.PAGE);
        CustomJSONObject json = new CustomJSONObject();
        json.getJSONObjectFromString(getIntent().getStringExtra("JSON"));
        JSONArray jsonArray = json.getJSONArray(Messages.FIELD_NUMBER);

        Map<String, Button> buttons = new HashMap<>();
        buttons.put("0", findViewById(R.id.FIELD0));
        buttons.put("1", findViewById(R.id.FIELD1));
        buttons.put("2", findViewById(R.id.FIELD2));
        buttons.put("3", findViewById(R.id.FIELD3));
        buttons.put("4", findViewById(R.id.FIELD4));
        buttons.put("5", findViewById(R.id.FIELD5));
        buttons.put("6", findViewById(R.id.FIELD6));
        buttons.put("7", findViewById(R.id.FIELD7));
        buttons.put("8", findViewById(R.id.FIELD8));
        buttons.put("9", findViewById(R.id.FIELD9));
        buttons.put("10", findViewById(R.id.FIELD10));
        buttons.put("11", findViewById(R.id.FIELD11));
        buttons.put("12", findViewById(R.id.FIELD12));
        buttons.put("13", findViewById(R.id.FIELD13));
        buttons.put("14", findViewById(R.id.FIELD14));
        buttons.put("15", findViewById(R.id.FIELD15));

        manageUsedButtons(jsonArray, buttons);

        for (String number : buttons.keySet()) {
            onClick(buttons.get(number), number, page);
        }
    }

    private void manageUsedButtons(JSONArray jsonArray, Map<String, Button> buttons) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                String number = jsonArray.getString(i);
                Button button = buttons.remove(number);
                button.setBackground(getResources().getDrawable(R.drawable.set_used_button));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void onClick(Button button, String number, String page) {
        button.setOnClickListener(v -> {
            CustomJSONObject json = new CustomJSONObject();
            json.put(Messages.ACTION_TYPE, Messages.PUT_DESERT_TILE);
            json.put(Messages.FIELD_NUMBER, number);
            json.put(Messages.PAGE, page);
            ClientHandler.getClient().sendMessage(json);
            finish();
        });
    }
}
