package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText input_text;
    private EditText text_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_text = findViewById(R.id.input);
        text_out = findViewById(R.id.editText1);

        if (getIntent().getStringExtra("text_out") != null){
            text_out.setText((getIntent().getStringExtra("text_out")));
        }
    }

    public void sendData(View view){
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("text", input_text.getText().toString());

        startActivity(intent);
    }
}