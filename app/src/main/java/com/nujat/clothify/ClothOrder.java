package com.nujat.clothify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ClothOrder extends AppCompatActivity {

    private RadioGroup radioGroupClothing;
    private CheckBox checkboxRed, checkboxBlue, checkboxGreen;
    private RatingBar ratingBar;
    private SeekBar seekBarWarmth;
    private Switch switchSustainable;
    private TextView quantityTextView;
    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Initialize UI elements
        radioGroupClothing = findViewById(R.id.radioGroupClothing);
        checkboxRed = findViewById(R.id.checkboxRed);
        checkboxBlue = findViewById(R.id.checkboxBlue);
        checkboxGreen = findViewById(R.id.checkboxGreen);
        ratingBar = findViewById(R.id.ratingBar);
        seekBarWarmth = findViewById(R.id.seekBarWarmth);
        switchSustainable = findViewById(R.id.switchSustainable);
        quantityTextView = findViewById(R.id.quantityTextView);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        Button buttonIncrease = findViewById(R.id.buttonIncrease);
        Button buttonDecrease = findViewById(R.id.buttonDecrease);

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++; // Increase quantity
                quantityTextView.setText("Quantity: " + quantity); // Update the display
            }
        });


        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 0) {
                    quantity--;
                    quantityTextView.setText("Quantity: " + quantity);
                }
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedClothingId = radioGroupClothing.getCheckedRadioButtonId();
                RadioButton selectedClothing = findViewById(selectedClothingId);
                String clothingType = selectedClothing != null ? selectedClothing.getText().toString() : "None Selected";


                StringBuilder colorPreferences = new StringBuilder("Selected Colors: ");
                if (checkboxRed.isChecked()) colorPreferences.append("Red ");
                if (checkboxBlue.isChecked()) colorPreferences.append("Blue ");
                if (checkboxGreen.isChecked()) colorPreferences.append("Green ");


                float rating = ratingBar.getRating();
                int warmthLevel = seekBarWarmth.getProgress();
                boolean isSustainable = switchSustainable.isChecked();


                String message = "Clothing Type: " + clothingType +
                        "\n" + colorPreferences +
                        "\nRating: " + rating +
                        "\nWarmth Level: " + warmthLevel +
                        "\nQuantity: " + quantity + // Include quantity in the message
                        "\nSustainable: " + isSustainable +
                        "\nThank you for your order!";


                new AlertDialog.Builder(ClothOrder.this)
                        .setTitle("Order Confirmation")
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("Okay", (dialog, which) -> resetOrder())
                        .show();
            }
        });
    }

    private void resetOrder() {

        radioGroupClothing.clearCheck();
        checkboxRed.setChecked(false);
        checkboxBlue.setChecked(false);
        checkboxGreen.setChecked(false);
        ratingBar.setRating(0);
        seekBarWarmth.setProgress(0);
        switchSustainable.setChecked(false);
        quantity = 0; // Reset quantity to 0
        quantityTextView.setText("Quantity: 0");

        Toast.makeText(getApplicationContext(), "Order cleared!", Toast.LENGTH_SHORT).show();
    }
}
