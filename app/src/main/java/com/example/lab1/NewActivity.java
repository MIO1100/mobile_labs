package com.example.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        TextView text_obj = findViewById(R.id.text);

        text_obj.setText((getIntent().getStringExtra("text")));
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
