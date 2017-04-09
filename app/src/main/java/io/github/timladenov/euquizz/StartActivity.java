/*
* @author   Tihomri Mladenov <tihomir.mladenov777@gmail.com>
*           This application has been created for final Project 3 on Udacity's Google sponsored "Android For Beginners" course;
*           02 April 2017
*
* @version  v2.3 final
* @since    v1.0a
* */

package io.github.timladenov.euquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private static final String STATE_SLEEP_TIME = "sleePTime";
    private static final String STATE_PLAYER_NAMES = "playerNames";
    private String playerNames;
    private int sleePTime = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /*@param namesField is used to get user's name and store it @param playerNames
        * The EditText view is hidden for 3,5 seconds on start. It is later added via
        * @param showNamesField thread.
        * */

        final EditText namesField = (EditText) findViewById(R.id.namesField);
        namesField.setVisibility(View.GONE);


        Thread showNamesField = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {

                        /*Checks if @param sleePTime is more than 0. If it starts for the first time, @param sleePTime will be 3500 (ms), if the
                        * screen is rotated, a value of 0 will be stored, so that the loading screen will initiate only once in app's life cycle,
                        * and name entry view will always be visible.
                        * */

                        if (sleePTime > 0) {
                            sleep(sleePTime);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    namesField.setVisibility(View.VISIBLE);
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    namesField.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        showNamesField.start();

        /*
        * OnClickListener will store the name as entered in the EditText view, in a String,
        * then it will send the string to the next Activity for further use.
        *
        * Player won't proceed without entering a name;
        * */

        namesField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerNames = namesField.getText().toString();

                if (playerNames.equals("")) {
                    Toast msg = Toast.makeText(getApplicationContext(), getResources().getString(R.string.enterNameToast), Toast.LENGTH_SHORT);
                    msg.show();
                } else if (!(playerNames.equals(""))) {
                    Intent proceedToMain = new Intent(getApplicationContext(), MainActivity.class);
                    proceedToMain.putExtra("playerNames", playerNames);
                    startActivity(proceedToMain);
                    finish();
                }


            }
        });
    }

    /* onSaveInstanceState is used to store data before screen rotation */

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_SLEEP_TIME, 0);
        savedInstanceState.putString(STATE_PLAYER_NAMES, playerNames);
        super.onSaveInstanceState(savedInstanceState);
    }

    /* onRestoreInstanceState restores the data from onSaveInstanceState to the respectful variables*/

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        sleePTime = savedInstanceState.getInt(STATE_SLEEP_TIME);
        playerNames = savedInstanceState.getString(STATE_PLAYER_NAMES);
    }
}
