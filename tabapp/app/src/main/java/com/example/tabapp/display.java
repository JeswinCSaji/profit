package com.example.tabapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        DatabaseHelper db = new DatabaseHelper(this);
        Intent in = getIntent();
        String name = in.getStringExtra("name");
        String gender = in.getStringExtra("gender");
        double protein = in.getDoubleExtra("protein",0);

        TextView textName = (TextView) findViewById(R.id.textName);
        textName.setText(name);
        TextView textGender = (TextView) findViewById(R.id.textGender);
        textGender.setText(gender);
        TextView textCourse = (TextView) findViewById(R.id.textprotein);
        textCourse.setText("" + protein);

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addData(name,gender,protein);
            }
        });

    }
}