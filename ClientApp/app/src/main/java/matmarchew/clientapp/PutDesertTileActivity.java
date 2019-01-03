package matmarchew.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class PutDesertTileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_desert_tile);

        CustomJSONObject json = new CustomJSONObject();
        json.getJSONObjectFromString(getIntent().getStringExtra("JSON"));

        String isDesertTileExist = json.getString(Messages.DESERT_TILE);

        if(!isDesertTileExist.equals("true"))
            finish();

        Button oasisPage = findViewById(R.id.OASIS_PAGE);
        Button miragePage = findViewById(R.id.MIRAGE_PAGE);

        Intent intent = new Intent(this, ChooseFieldActivity.class);
        intent.putExtra("JSON", json.toString());

        onClick(oasisPage, intent, Messages.OASIS_PAGE);
        onClick(miragePage, intent, Messages.MIRAGE_PAGE);
    }

    private void onClick(Button button, Intent intent, String page) {
        button.setOnClickListener(v -> {
            intent.putExtra(Messages.PAGE, page);
            startActivity(intent);
            finish();
        });
    }
}
