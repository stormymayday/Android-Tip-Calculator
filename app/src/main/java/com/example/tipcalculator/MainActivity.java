package com.example.tipcalculator;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {

    private String billAmountString = "";
    private float tipPercent = .15f;

    // Instance Variables
    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Getting references to the UI Controls
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        percentUpButton = findViewById(R.id.percentUpButton);
        percentDownButton = findViewById(R.id.percentDownButton);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);

        // Listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentDownButton.setOnClickListener(this);
        percentUpButton.setOnClickListener(this);
    }

    public void calculateAndDisplay() {
        // Getting the bill amount
        billAmountString = billAmountEditText.getText().toString();

        float billAmount;

        if(billAmountString.equals("")) {
            billAmount = 0;
        } else {
            billAmount = Float.parseFloat(billAmountString);
        }

        // Calculating Tip and Total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // Displaying the results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//            case R.id.percentDownButton:
//                tipPercent = tipPercent - 0.01f;
//                calculateAndDisplay();
//                break;
//            case R.id.percentUpButton:
//                tipPercent = tipPercent + 0.01f;
//                calculateAndDisplay();
//                break;
//        }

        if(v.getId() == R.id.percentDownButton) {
            tipPercent = tipPercent - 0.01f;
            calculateAndDisplay();
        }

        if(v.getId() == R.id.percentUpButton) {
            tipPercent = tipPercent + 0.01f;
            calculateAndDisplay();
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }
}