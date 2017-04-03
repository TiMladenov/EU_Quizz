/*
* @author   Tihomri Mladenov <tihomir.mladenov777@gmail.com>
*           This application has been created for final Project 3 on Udacity's Google sponsored "Android For Beginners" course;
*           02 April 2017
*
* @version  v2.1 final
* @since    v1.4
* */

package io.github.timladenov.euquizz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private static final String EUROPARL_WEBSITE = "http://www.europarl.europa.eu/portal";
    private static final String EUROCOM_WEBSITE = "https://ec.europa.eu/";
    public String playerName;
    public int playerScore;
    private TextView resultView;
    private Button retake;
    private Button close;
    private ImageButton visitEP;
    private ImageButton visitECom;
    private ImageView setEmoji;
    private String[] resultText;

    /*
    * Gets payers' name and score. According to the score, different emoji and congratulation text
    * will be set to be displayed on player's device.
    *
    * Listens to player's choice to retake the quiz or to finish it.
    *
    * @param playerName     receives and stores player's name;
    * @param playerScore    receives and stores plaer's score;
    * @param resultView     sets different congratulations text to player according to his score,
    *                       sets player's score for display;
    * @param setEmoji       sets different emoji background icon according to player's score;
    * @param resultText     stores the different congratulation texts via String values;
    *
    * */

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

        if (playerScore >= 80 && playerScore <= 100) {
            resultView.setText(resultText[0]);
            setEmoji.setImageResource(R.drawable.emojis_smile);
        } else if (playerScore >= 60 && playerScore < 80) {
            resultView.setText(resultText[1]);
            setEmoji.setImageResource(R.drawable.emojis_smile);
        } else if (playerScore >= 50 && playerScore < 60) {
            resultView.setText(resultText[2]);
            setEmoji.setImageResource(R.drawable.emojis_no_smile);
        } else if (playerScore >= 40 && playerScore < 50) {
            resultView.setText(resultText[3]);
            setEmoji.setImageResource(R.drawable.emojis_no_smile);
        } else {
            resultView.setText(resultText[4]);
            setEmoji.setImageResource(R.drawable.emojis_sad_face);
        }

        resultView = (TextView) findViewById(R.id.resultWindow2);
        String textResult = getResources().getString(R.string.scoreMessage1) + Integer.toString(playerScore) + getResources().getString(R.string.scoreMessage2) + playerName + ".";
        resultView.setText(textResult);

        retake = (Button) findViewById(R.id.retake);
        close = (Button) findViewById(R.id.finish);
        visitEP = (ImageButton) findViewById(R.id.visitEP);
        visitECom = (ImageButton) findViewById(R.id.visitECom);

        finalAction();
    }

    /*
    * Listens to player's button choice:
    *
    * @param retake     If chosen, it will direct the player to the first Activity to retake the quiz;
    * @param close      If chosen, it will close the current activity and will exit the application;
    * @param visitEP    If chosen, it will direct the player to the site of European parliament;
    * @param visitEC    If chosen, it will direct the player to the site of the European Commission;
    *
    * @param EUROPARL_WEBSITE   stores URL to EP's website;
    * @param EUROCOM_WEBSITE    stores website to EC's website;
    *
    * */

    private void finalAction() {
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

        visitEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEPSite = new Intent(Intent.ACTION_VIEW);
                goToEPSite.setData(Uri.parse(EUROPARL_WEBSITE));
                startActivity(goToEPSite);
            }
        });

        visitECom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToEcomSite = new Intent(Intent.ACTION_VIEW);
                goToEcomSite.setData(Uri.parse(EUROCOM_WEBSITE));
                startActivity(goToEcomSite);
            }
        });
    }

    /*
    * Stores the different text strings for congratulation text to player. Values loaded via
    * strings.xml
    *
    * */

    private void setResultText() {
        resultText[0] = getString(R.string.resultText0);
        resultText[1] = getString(R.string.resultText1);
        resultText[2] = getString(R.string.resultText2);
        resultText[3] = getString(R.string.resultText3);
        resultText[4] = getString(R.string.resultText4);
    }
}
