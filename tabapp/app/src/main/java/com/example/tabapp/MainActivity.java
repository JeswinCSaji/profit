package com.example.tabapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextWeight;
    private EditText editTextHeight;
    private RadioGroup radioGroupGender;
    private Spinner spinnerActivityLevel;
    private Button buttonCalculate;
    private TextView textViewResult;
    private EditText nameEditText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        spinnerActivityLevel = findViewById(R.id.spinnerActivityLevel);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        nameEditText = findViewById(R.id.edit_text_name);
        Button buttonView = findViewById(R.id.btnView);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double protein = calculateProtein();
                String name = ((EditText) findViewById(R.id.edit_text_name)).getText().toString();
                int selectedId = ((RadioGroup) findViewById(R.id.radioGroupGender)).getCheckedRadioButtonId();
                String gender = ((RadioButton) findViewById(selectedId)).getText().toString();


                Intent in = new Intent(MainActivity.this,display.class);
                in.putExtra("name", name);
                in.putExtra("gender", gender);
                in.putExtra("protein", protein);

                startActivity(in);

            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, com.example.tabapp.View.class);
                startActivity(in);
            }
        });
    }

    private double calculateProtein() {
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());
        int genderId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton radioButtonGender = findViewById(genderId);
        String gender = radioButtonGender.getText().toString();
        name = nameEditText.getText().toString();
        String activityLevel = spinnerActivityLevel.getSelectedItem().toString();

        double protein = calculateProteinIntake(weight, height, gender, activityLevel);
//        DatabaseHelper databaseHelper = new DatabaseHelper(this);
//        databaseHelper.addData(name, gender,weight,height,activityLevel,protein);
        textViewResult.setText(name + ", your daily protein intake should be " + protein + " grams.");
        return protein;

    }

    private double calculateProteinIntake(double weight, double height, String gender, String activityLevel) {
        double bmr = 0;
        double activityFactor = 0;

        if (gender.equals("Male")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * 25);
        } else if (gender.equals("Female")) {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * 25);
        }

        switch (activityLevel) {
            case "Sedentary":
                activityFactor = 1.2;
                break;
            case "Lightly active":
                activityFactor = 1.375;
                break;
            case "Moderately active":
                activityFactor = 1.55;
                break;
            case "Very active":
                activityFactor = 1.725;
                break;
            case "Super active":
                activityFactor = 1.9;
                break;
        }

        double proteinIntake = (bmr * activityFactor) / 4;
        return proteinIntake/10;
    }
}