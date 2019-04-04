package dgsw.hs.kr.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends AppCompatActivity implements Button.OnClickListener{

    HistoryDBHelper dbHelper = new HistoryDBHelper(this, "userdb", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    @Override
    public void onClick(View v) {
        dbHelper.deleteAll();
    }
}
