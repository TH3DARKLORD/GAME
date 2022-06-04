package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent get = getIntent();
        String s=get.getStringExtra("Scoreinnewpage");
        setContentView(R.layout.activity_end);
        TextView scores = (TextView) findViewById(R.id.thankyou2);
        scores.setText("Score:"+s);
    }
    public void maingame(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}