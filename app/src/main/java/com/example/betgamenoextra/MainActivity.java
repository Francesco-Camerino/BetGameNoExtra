package com.example.betgamenoextra;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Integer savedAmount, savedBet, savedResult, savedRandNumber;
    Boolean savedEvenOdd;
    TextView savedResultTextView;
    ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedResultTextView = findViewById(R.id.savedResultTextView);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new BetCallBack());
    }
    public void onClickButton(View view) {
     Intent mainToBetIntent = new Intent(this,BetActivity.class);
     mainToBetIntent.putExtra(KeyConstants.AMOUNT_FROM_MAIN_KEY,savedAmount);
     launcher.launch(mainToBetIntent);
    }

    public class BetCallBack implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d("Main","VAI CALLBACK");
             if (result.getResultCode() == 0) {
                Intent data = result.getData();
                savedAmount = data.getIntExtra(KeyConstants.AMOUNT_FROM_BET_KEY,0);
                savedBet = data.getIntExtra(KeyConstants.BET_KEY,0);
                savedEvenOdd = data.getBooleanExtra(KeyConstants.EVEN_ODD_KEY, false);
                savedResult = data.getIntExtra(KeyConstants.RESULT_KEY,0);
                savedRandNumber = data.getIntExtra(KeyConstants.RAND_NUMBER_KEY,0);
                String evenOddStr, resultStr;
                if (savedEvenOdd) {
                    evenOddStr = "odd";
                } else {
                    evenOddStr = "even";
                }
                if (savedResult == 1) {
                    resultStr = "Won: ";
                } else {
                    resultStr = "Lost: ";
                }

                savedResultTextView.setText("Result of saved bet:\n" +
                        resultStr + savedBet + "\n Bet: " + evenOddStr +"\n" +
                        "Number generated: "+ savedRandNumber +
                        "\nAmount left: " + savedAmount);
            } else {
                 savedAmount = result.getData().getIntExtra(KeyConstants.AMOUNT_FROM_BET_KEY,0);
             }
        }
    }


}