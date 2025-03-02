package com.set.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private EditText edtWeight, edtHeightFt, edtHeightIn;
    private Button btnStart, btnBMI;
    private TextView txtResult;
    private ImageView imageView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Connects XML to Java

        // ViewFlipper for switching screens
        viewFlipper = findViewById(R.id.viewFlipper);

        // First screen components
        btnStart = findViewById(R.id.btnStart);

        // Second screen components
        edtWeight = findViewById(R.id.edtWeight);
        edtHeightFt = findViewById(R.id.edtHeigthFt);
        edtHeightIn = findViewById(R.id.edtHeigthIn);
        btnBMI = findViewById(R.id.btnBMI);
        txtResult = findViewById(R.id.txtResult);

        // Move from Welcome Screen to BMI Calculator Screen
        btnStart.setOnClickListener(view -> viewFlipper.showNext());

        // Calculate BMI when the button is clicked
        btnBMI.setOnClickListener(view -> {
            String weightStr = edtWeight.getText().toString();
            String heightFtStr = edtHeightFt.getText().toString();
            String heightInStr = edtHeightIn.getText().toString();

            // Validate input
            if (weightStr.isEmpty() || heightFtStr.isEmpty() || heightInStr.isEmpty()) {
                txtResult.setText("Please enter all values!");
                return;
            }

            // Convert input to integers
            int weight = Integer.parseInt(weightStr);
            int feet = Integer.parseInt(heightFtStr);
            int inches = Integer.parseInt(heightInStr);

            // Convert height to meters
            int totalInches = (feet * 12) + inches;
            double totalMeters = totalInches * 0.0254;

            // Calculate BMI
            double bmi = weight / (totalMeters * totalMeters);

            // Display result
            if (bmi > 25) {
                txtResult.setText("You are overweight \uD83D\uDE14!!");
            } else if (bmi < 18) {
                txtResult.setText("You are  Underweight focus on your Diet\uDF72\uD83E\uDD58\uD83E\uDD57");
            } else {
                txtResult.setText("You are Healthy \uD83D\uDCAA\uD83D\uDCAA!!");
            }
        });
    }
}
