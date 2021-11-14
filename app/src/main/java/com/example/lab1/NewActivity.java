package com.example.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewActivity extends Activity {

    private EditText set_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        set_text =  findViewById(R.id.set_text);
        TextView text_obj = findViewById(R.id.text);

        text_obj.setText((getIntent().getStringExtra("text")));
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("text_out", set_text.getText().toString());
        startActivity(intent);
    }
}
