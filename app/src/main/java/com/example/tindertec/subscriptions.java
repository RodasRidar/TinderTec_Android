package com.example.tindertec;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class subscriptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscripciones);
    }

    public void goPagan(View view){
        Intent intent = new Intent(this , paganActivity.class);
        startActivity(intent);

    }
}