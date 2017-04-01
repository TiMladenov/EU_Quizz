package io.github.timladenov.euquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public TextView resultView;
    public Button retake;
    public Button close;
    public ImageView setEmoji;

    public String[] resultText;
    public String playerName;
    public int playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        playerName = getIntent().getExtras().getString("playerNamesMain");
        playerScore = getIntent().getExtras().getInt("quizScore");

        resultText = new String[5];
        setResultText();

        resultView = (TextView) findViewById(R.id.showRes);
        setEmoji = (ImageView) findViewById(R.id.emoji);

        if(playerScore >= 80 && playerScore == 100) {
            resultView.setText(resultText[0]);
            setEmoji.setImageResource(R.drawable.emojis_smile);
        }
        else if(playerScore >= 60 && playerScore < 80){
            resultView.setText(resultText[1]);
            setEmoji.setImageResource(R.drawable.emojis_smile);
        }
        else if(playerScore >= 50 && playerScore < 60) {
            resultView.setText(resultText[2]);
            setEmoji.setImageResource(R.drawable.emojis_no_smile);
        }
        else if(playerScore >= 40 && playerScore < 50) {
            resultView.setText(resultText[3]);
            setEmoji.setImageResource(R.drawable.emojis_no_smile);
        }
        else {
            resultView.setText(resultText[4]);
            setEmoji.setImageResource(R.drawable.emojis_sad_face);
        }

        resultView = (TextView) findViewById(R.id.resultWindow2);
        String textResult = "You have " + Integer.toString(playerScore) + " out of 100 points. Thank you for participating, " + playerName + ".";
        resultView.setText(textResult);

        retake = (Button) findViewById(R.id.retake);
        close = (Button) findViewById(R.id.finish);
        finalAction();
    }

    public void finalAction() {
        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toStart = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(toStart);
                finish();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setResultText() {
        resultText[0] = getString(R.string.resultText0);
        resultText[1] = getString(R.string.resultText1);
        resultText[2] = getString(R.string.resultText2);
        resultText[3] = getString(R.string.resultText3);
        resultText[4] = getString(R.string.resultText4);
    }
}
