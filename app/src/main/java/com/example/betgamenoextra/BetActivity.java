package com.example.betgamenoextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.betgamenoextra.bet.BetLogic;

public class BetActivity extends AppCompatActivity {
    TextView amountTextView;
    TextView resultTextView;
    EditText betTextNumber;
    ToggleButton evenOddToggle;
    BetLogic betLogic = new BetLogic(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        amountTextView = findViewById(R.id.amountTextView);
        resultTextView = findViewById(R.id.resultTextView);
        betTextNumber = findViewById(R.id.betTextNumber);
        evenOddToggle = findViewById(R.id.evenOddToggle);
        amountTextView.setText("Amount: "+ betLogic.getAmount().toString());

    }
    public void betStart(View view) {
        String bstr = betTextNumber.getText().toString();
        if (bstr.isEmpty() ) {
            Toast.makeText(getBaseContext(),"Insert a value", Toast.LENGTH_LONG).show();
        } else {
            Integer betValue = Integer.parseInt(betTextNumber.getText().toString());
            if (betValue <= 0) {
                Toast.makeText(getBaseContext(),"The value of the bet must be over 0", Toast.LENGTH_LONG).show();
            } else if (betValue > betLogic.getAmount()) {
                Toast.makeText(this,"The value of the bet must be below the total amount", Toast.LENGTH_LONG).show();
            } else if (betValue <= betLogic.getAmount()) {
                betLogic.setBet(betValue);
                betLogic.setEvenOdd(evenOddToggle.isChecked());
                int[] result =betLogic.bet();
                amountTextView.setText("Amount: " + betLogic.getAmount().toString());
                if (result[0] == 1) {
                    resultTextView.setText("You won! The number was " +result[1]);
                } else {
                    resultTextView.setText("You lost The number was " +result[1]);
                }
            }
        }


    }

    public void goBackIntent(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}