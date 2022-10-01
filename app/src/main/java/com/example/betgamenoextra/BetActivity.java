package com.example.betgamenoextra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button saveResultButton;
    Intent dataToSend;
    BetLogic betLogic;
    int[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);
        amountTextView = findViewById(R.id.amountTextView);
        resultTextView = findViewById(R.id.resultTextView);
        betTextNumber = findViewById(R.id.betTextNumber);
        evenOddToggle = findViewById(R.id.evenOddToggle);
        saveResultButton = findViewById(R.id.saveResultButton);
        Intent fromMainIntent = getIntent();
        betLogic = new BetLogic(fromMainIntent.getIntExtra(KeyConstants.AMOUNT_FROM_MAIN_KEY, 100));
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
                result =betLogic.bet();
                amountTextView.setText("Amount: " + betLogic.getAmount().toString());
                if (result[0] == 1) {
                    resultTextView.setText("You won! The number was " +result[1]);
                } else {
                    resultTextView.setText("You lost The number was " +result[1]);
                }
                saveResultButton.setVisibility(View.VISIBLE);

            }
        }

    }
    public void onClickSaveResultButton(View view) {
        dataToSend = new Intent();
        dataToSend.putExtra(KeyConstants.AMOUNT_FROM_BET_KEY,betLogic.getAmount());
        dataToSend.putExtra(KeyConstants.BET_KEY,betLogic.getBet());
        dataToSend.putExtra(KeyConstants.EVEN_ODD_KEY,betLogic.getEvenOdd());
        dataToSend.putExtra(KeyConstants.RESULT_KEY,result[0]);
        dataToSend.putExtra(KeyConstants.RAND_NUMBER_KEY,result[1]);
        setResult(0,dataToSend);
        finish();
    }

    public void goBackIntent(View view) {
        dataToSend = new Intent();
        dataToSend.putExtra(KeyConstants.AMOUNT_FROM_BET_KEY,betLogic.getAmount());
        setResult(1,dataToSend);
        finish();
    }
}