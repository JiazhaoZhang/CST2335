package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static SharedPreferences prefs;
    private static final String SHARE_PREF_LAB3 = "sharedpreferences";
    private Button logInBtn;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3_layout);

        prefs = getSharedPreferences(SHARE_PREF_LAB3,MODE_PRIVATE);

        String email = prefs.getString("email","");
        String passwd = prefs.getString("pass","");

        EditText editEmail = findViewById(R.id.inputEmail);
        EditText editPasswd = findViewById(R.id.inputPasswd);

        editEmail.setText(email);
        editPasswd.setText(passwd);

        logInBtn = (Button)findViewById(R.id.logInBtn);
        if(logInBtn!=null) {
            logInBtn.setOnClickListener(new LogInListener());
        }
    }
        private class LogInListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
               it = new Intent();
               it.setClass(MainActivity.this, ProfileActivity.class);
               EditText et =  findViewById(R.id.inputEmail);
               String email = et.getText().toString();
               it.putExtra("email",email);
               startActivity(it);
            }
        }

    @Override
    protected void onPause(){
        super.onPause();
        EditText et =  findViewById(R.id.inputEmail);
        String email = et.getText().toString();
        EditText pt = findViewById(R.id.inputPasswd);
        String passwd = pt.getText().toString();
        prefs = getSharedPreferences(SHARE_PREF_LAB3,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email",email);
        editor.putString("pass",passwd);
        editor.apply();
    }
}
