package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText input_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_text = findViewById(R.id.input);

    }

    public void sendData(View view){
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("text", input_text.getText().toString());

        startActivity(intent);
    }
}