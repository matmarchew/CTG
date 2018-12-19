package matmarchew.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class ChooseStackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_stack);

        String color = getIntent().getStringExtra(Messages.COLOR);

        Button winnerStack = findViewById(R.id.WINNER_STACK);
        Button loserStack = findViewById(R.id.LOSER_STACK);

        onClick(winnerStack, color, Messages.WINNER_STACK);
        onClick(loserStack, color, Messages.LOSER_STACK);
    }

    private void onClick(Button button, String color, String stack) {
        button.setOnClickListener(v -> {
            CustomJSONObject json = new CustomJSONObject();
            json.put(Messages.STATE, Messages.MOVE);
            json.put(Messages.ACTION_TYPE, Messages.PUT_BET_CARD);
            json.put(Messages.COLOR, color);
            json.put(Messages.STACK, stack);
            ClientHandler.getClient().sendMessage(json);
            finish();
        });
    }
}
