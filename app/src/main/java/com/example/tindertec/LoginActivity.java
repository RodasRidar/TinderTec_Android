package com.example.tindertec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buttonLogin = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(buttonLogin);
            }
        });
    }
    //
    public void goCreateAccount(View view){
        Intent intent = new Intent(this , RegistroActivity.class);
        startActivity(intent);
    }
}