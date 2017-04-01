package io.github.timladenov.euquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public TextView resultView;

    public String[] resultText;
    public String playerName;
    public int playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playerName = getIntent().getExtras().getString("playerNamesMain");
        playerScore = getIntent().getExtras().getInt("quizScore");

        Log.v("playerName", playerName);
        Log.v("playerScore", Integer.toString(playerScore));

        resultText = new String[5];
        setResultText();
        resultView = (TextView) findViewById(R.id.showRes);

        if(playerScore >= 80 && playerScore == 100) {
            resultView.setText(resultText[0]);
        }
        else if(playerScore >= 60 && playerScore < 80){
            resultView.setText(resultText[1]);
        }
        else if(playerScore >= 50 && playerScore < 60) {
            resultView.setText(resultText[2]);
        }
        else if(playerScore >= 40 && playerScore < 50) {
            resultView.setText(resultText[3]);
        }
        else {
            resultView.setText(resultText[4]);
        }
    }

    public void setResultText() {
        resultText[0] = getString(R.string.resultText0);
        resultText[1] = getString(R.string.resultText1);
        resultText[2] = getString(R.string.resultText2);
        resultText[3] = getString(R.string.resultText3);
        resultText[4] = getString(R.string.resultText4);
    }
}
