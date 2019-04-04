package dgsw.hs.kr.data;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[] layoutIds = {R.id.layoutForm, R.id.layoutHome};
    private LinearLayout[] layouts;
    private EditText editTextUserName;
    private TextView textViewUserName;
    private SharedPreferences preferences;

    private HistoryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new HistoryDBHelper(this, "userdb", null, 1);

        layouts = new LinearLayout[layoutIds.length];
        for(int i=0;i<layouts.length;i++){
            layouts[i] = findViewById(layoutIds[i]);
        }

        findViewById(R.id.historyBtn).setOnClickListener(v-> {
            Intent i = new Intent(this, HistoryActivity.class);
            startActivity(i);
        });

        editTextUserName = findViewById(R.id.editTextUserName);
        textViewUserName = findViewById(R.id.textViewUserName);
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        String userName = preferences.getString("userName", null);
        if(userName == null)
            showLayout(R.id.layoutForm);
        else {
            textViewUserName.setText(userName);
            showLayout(R.id.layoutHome);
        }
    }

    private void showLayout(int id){
        for(LinearLayout layout: layouts){
            if(layout.getId() == id)
                layout.setVisibility(View.VISIBLE);
            else
                layout.setVisibility(View.GONE);
        }
    }

    public void onLogin(View v){
        if(preferences == null) return;
        String userName  = editTextUserName.getText().toString().trim();
        if(userName.length() == 0) return;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userName", userName);
        editor.apply();

        UserBean user = new UserBean();
        user.setName(userName);
        dbHelper.insert(user);
        showUsers();

        textViewUserName.setText(userName);
        showLayout(R.id.layoutHome);
    }

    public void showUsers(){
        ArrayList<UserBean> users = dbHelper.getAll();
        for(UserBean u : users){
            Log.i("main", "[" + u.getSequenceNumber() + "]" + u.getName());
        }
    }

    public void onLogout(View v){
        if(preferences == null) return;
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("userName");
        editor.apply();

        showLayout(R.id.layoutForm);
    }
}
