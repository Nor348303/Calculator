package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


//Main activity class start here
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Define layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Get the references to the widgets
        final EditText e1 = (EditText) findViewById(R.id.editTextWeight);
        final EditText e2 = (EditText) findViewById(R.id.editTextHeight);
        final TextView tv = (TextView) findViewById(R.id.tv);

        findViewById(R.id.btnConvert).setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                String str1 = e1.getText().toString();
                String str2 = e2.getText().toString();


                if(TextUtils.isEmpty(str1)){
                    e1.setError("Please enter your weight");
                    e1.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(str2)){
                    e2.setError("Please enter your height");
                   e2.requestFocus();
                    return;
                }

//Get the user values from the widget reference
                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2)/100;

//Calculate BMI value
                float bmiValue = calculateBMI (weight, height);

//Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);

                tv.setText(String.valueOf(String.format("%.1f", bmiValue) + "\n" + bmiInterpretation));

            }
        });

    }

    //Calculate BMI
    private float calculateBMI (float weight, float height) {
        return (float) (weight / (height * height));
    }

    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 18.4) {
            return "Underweight" + "\n" +
                    "Malnutrition risk";

        } else if (bmiValue <= 24.9 ) {
            return "Normal weight" + "\n" +
                    "Low risk";

        } else if (bmiValue <= 29.9 ) {
            return "Overweight" + "\n" +
                    "Enhanced risk" ;

        } else if (bmiValue <= 34.9 ) {
            return "Moderately obese" + "\n" +
                    "Medium risk";

        } else if (bmiValue < 39.9 ) {
            return "Severely obese" + "\n" +
                    "High Risk";

        } else {
            return "Very severaly obese" + "\n" +
                    "Very high risk";
        }
    }

    @Override
    public void onClick(View v) {

    }
}