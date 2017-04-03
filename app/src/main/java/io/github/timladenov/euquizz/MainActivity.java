/*
* @author   Tihomri Mladenov <tihomir.mladenov777@gmail.com>
*           This application has been created for final Project 3 on Udacity's Google sponsored "Android For Beginners" course;
*           02 April 2017
*
* @version  v2.1 final
* @since    v1.0a
* */

package io.github.timladenov.euquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String playerNamesMain;
    public int answerIndex;
    public int quizScore;
    public String[] correctAnswers;
    public String chosenAnswer;
    private EditText editTextBox0;
    private TextView questionNum;
    private TextView questionText;
    private RadioButton answers0;
    private RadioButton answers1;
    private RadioButton answers2;
    private RadioButton answers3;
    private CheckBox answers0check;
    private CheckBox answers1check;
    private CheckBox answers2check;
    private CheckBox answers3check;
    private TextView answers0text;
    private TextView answers1text;
    private TextView answers2text;
    private TextView answers3text;
    private String question;
    private boolean notChecked;
    private ImageView backGround;
    private Button nextView;
    private String[] questionArray;
    private String[] answersArray;
    private String toastString;
    private int questionNumber;
    private int questionIndex;
    private int checkIndex;
    private int imageIndex;
    private int[] drawableArray;

   /*
    *  Sets up the the views initialization on activity creation;
    *  Sets up views with values for question number, question, answers and background;
    *
    *  Calls method fillQuestionArray(),    which populates the question array;
    *  Calls method fillAnswersAray(),      which populates the answers array;
    *  Calls method fillDrawableArray(),    which populates the drawables ID array;
    *  Calls method nextView(),             which if used will call next view;
    *  Calls method selectedRadioView(),    which if used will pass the selected answer;
    *  Calls method selectedCheckBoxView(), which if used will pass the selected checkBox answer, if correct;
    *
    *  @param questionArray     is an array that keeps all questions. Data is received via "string" values;
    *  @param answersArray      is an array that keeps all answers. Data is received via "string" values;
    *  @param correctAnswers    is an array that keeps all correct answers and checks each selected answer;
    *  @param questionNumber    iterates until (incl.) 10 and updates question number together with @param question or via concatenation;
    *  @param drawableArray     is an array that contains the IDs to all background drawables;
    *  @param questionIndex     is used to iterate to the next question in questionArray;
    *  @param imageIndex        is used to iterate to the next background drawable is drawableArray;
    *  @param answerIndex       is used to iterate to the next 4 answers in answerArray;
    *  @param playerNamesMain   is used to store player's name, which is later passed to ResultActivity;
    *  @param toastString       is used to store Toast message template text;
    *  @param question          is used as a template to create question number;
    *
    *  @param questionNum       is used to set the text in question number TextView;
    *  @param questionText      is used to set the text in question TextView;
    *  @param answers 0 - 4     is used to set the answers for each question;
    *  @param backGround        is used to set the background for each question;
    *  @param nextView          is used to get the ID of Next button;
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = getResources().getString(R.string.questionTxt);

        quizScore = 0;
        questionNumber = 2;
        questionIndex = 0;
        checkIndex = 0;
        imageIndex = 1;

        questionArray = new String[10];
        answersArray = new String[40];
        correctAnswers = new String[10];
        drawableArray = new int[10];

        /*
        * Sets up the fields for RadioButton questions;
        * */

        questionNum = (TextView) findViewById(R.id.questionNumber);
        questionText = (TextView) findViewById(R.id.question);
        answers0 = (RadioButton) findViewById(R.id.answers0);
        answers1 = (RadioButton) findViewById(R.id.answers1);
        answers2 = (RadioButton) findViewById(R.id.answers2);
        answers3 = (RadioButton) findViewById(R.id.answers3);
        backGround = (ImageView) findViewById(R.id.quesitonBackground);
        nextView = (Button) findViewById(R.id.nextView);

        /*
        * Sets up the fields for CheckBox questions;
        * */

        answers0check = (CheckBox) findViewById(R.id.checkbox0);
        answers1check = (CheckBox) findViewById(R.id.checkbox1);
        answers2check = (CheckBox) findViewById(R.id.checkbox2);
        answers3check = (CheckBox) findViewById(R.id.checkbox3);

        /*
        * Sets up the fields for TextView for the questions and EditText view for response entry with @param editTextBox0;
        * */

        answers0text = (TextView) findViewById(R.id.textBox0);
        answers1text = (TextView) findViewById(R.id.textBox1);
        answers2text = (TextView) findViewById(R.id.textBox2);
        answers3text = (TextView) findViewById(R.id.textBox3);
        editTextBox0 = (EditText) findViewById(R.id.editTextBox0);
        notChecked = true;

        fillQuestionArray();
        fillAnswersAray();
        fillDrawableArray();

        /*
        * Populates first views with the text;
        * */

        question += Integer.toString(questionNumber - 1);
        questionNum.setText(question);
        questionText.setText(questionArray[questionIndex]);
        answers0.setText(answersArray[0]);
        answers1.setText(answersArray[1]);
        answers2.setText(answersArray[2]);
        answers3.setText(answersArray[3]);
        backGround.setImageResource(drawableArray[imageIndex - 1]);
        if (questionIndex == 0) {
            questionIndex++;
        }

        nextView();
        selectedRadioView();
        editTextCheck();

        if (answerIndex == 0) {
            answers0.setText(answersArray[answerIndex]);
            answerIndex++;
            answers1.setText(answersArray[answerIndex]);
            answerIndex++;
            answers2.setText(answersArray[answerIndex]);
            answerIndex++;
            answers3.setText(answersArray[answerIndex]);
            answerIndex++;
        }
        playerNamesMain = getIntent().getExtras().getString("playerNames");
        toastString = getResources().getString(R.string.toastMsg);

        /*
        * Hides the unused views until they are needed;
        * */

        findViewById(R.id.answersField2).setVisibility(View.GONE);
        findViewById(R.id.answersField3).setVisibility(View.GONE);
    }

    /*
    * Saves variable state on screen rotation;
    *
    * @see onCreate doc for variable documentation;
    * */

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("playerNamesMain", playerNamesMain);
        savedInstanceState.putInt("answerIndex", answerIndex);
        savedInstanceState.putInt("quizScore", quizScore);
        savedInstanceState.putString("chosenAnswer", chosenAnswer);
        savedInstanceState.putInt("questionNumber", questionNumber);
        savedInstanceState.putInt("questionIndex", questionIndex);
        savedInstanceState.putInt("checkIndex", checkIndex);
        savedInstanceState.putInt("imageIndex", imageIndex);
        savedInstanceState.putBoolean("notChecked", notChecked);
        super.onSaveInstanceState(savedInstanceState);
    }

    /*
    * Restores variable and View state on screen rotation;
    *
    * Calls method setScreen() to restore views state with the saved data;
    *
    * @see onCreate doc for variable documentation;
    * */

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playerNamesMain = savedInstanceState.getString("playerNamesMain");
        answerIndex = savedInstanceState.getInt("answerIndex");
        quizScore = savedInstanceState.getInt("quizScore");
        chosenAnswer = savedInstanceState.getString("chosenAnswer");
        questionNumber = savedInstanceState.getInt("questionNumber");
        questionIndex = savedInstanceState.getInt("questionIndex");
        checkIndex = savedInstanceState.getInt("checkIndex");
        imageIndex = savedInstanceState.getInt("imageIndex");
        notChecked = savedInstanceState.getBoolean("notChecked");

        if (questionNumber - 1 > 8 && questionNumber - 1 < 10) {
            findViewById(R.id.answersField).setVisibility(View.GONE);
            findViewById(R.id.answersField2).setVisibility(View.VISIBLE);
            findViewById(R.id.answersField3).setVisibility(View.GONE);
        } else if (questionNumber - 1 == 10) {
            findViewById(R.id.answersField).setVisibility(View.GONE);
            findViewById(R.id.answersField2).setVisibility(View.GONE);
            findViewById(R.id.answersField3).setVisibility(View.VISIBLE);
        }
        setScreen();
    }

    /*
    * Called only on screen rotation to restore the states of the views.
    *
    * Further, it is used only after question 1, if the question is 1, the default method from onCreate() is used;
    *
    * @see onCreate doc for variable documentation;
    * */

    private void setScreen() {

        questionNum = (TextView) findViewById(R.id.questionNumber);
        questionText = (TextView) findViewById(R.id.question);
        answers0 = (RadioButton) findViewById(R.id.answers0);
        answers1 = (RadioButton) findViewById(R.id.answers1);
        answers2 = (RadioButton) findViewById(R.id.answers2);
        answers3 = (RadioButton) findViewById(R.id.answers3);
        backGround = (ImageView) findViewById(R.id.quesitonBackground);
        nextView = (Button) findViewById(R.id.nextView);

        if (questionNumber > 2) {
            String question = getResources().getString(R.string.questionTxt);
            question += Integer.toString(questionNumber - 1);
            questionNum.setText(question);
            questionText.setText(questionArray[questionIndex - 1]);
            answers0.setText(answersArray[answerIndex - 4]);
            answers1.setText(answersArray[answerIndex - 3]);
            answers2.setText(answersArray[answerIndex - 2]);
            answers3.setText(answersArray[answerIndex - 1]);
            backGround.setImageResource(drawableArray[imageIndex - 1]);
        }

        if (questionNumber - 1 == 10) {
            answers0text.setText(answersArray[answerIndex - 4]);
            answers1text.setText(answersArray[answerIndex - 3]);
            answers2text.setText(answersArray[answerIndex - 2]);
            answers3text.setText(answersArray[answerIndex - 1]);
        }
    }

    /*
    * If next button is pressed and the question number is not more than 10, the method calls method changeViewContent();
    * Shows Toast message with number of answered questions after each question;
    *
    * Else if the question number is more than 10, the Results view is called and the player name and score is passed to it;
    *
    * @param quizScore saves the score of the player. It adds 10 for each correct answer;
    * @param chosenAnswer checks the selected answer to the corresponding correct answer for the respective question in question array;
    * @see onCreate doc for variable documentation;
    * */

    private void nextView() {
        nextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (questionIndex < 8) {

                    //Added in v2.1. Question 8 couldn't enter the default answer check method, had to override it here;

                    if (questionIndex == 7) {
                        answers0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (answers0.isChecked()) {
                                    String chosenReply = "";

                                    answers1.setChecked(false);
                                    answers2.setChecked(false);
                                    answers3.setChecked(false);
                                    chosenReply = answers0.getText().toString();
                                    if (checkAnswer(chosenReply, correctAnswers[checkIndex])) {
                                        quizScore += 10;
                                    }
                                }
                            }
                        });
                    }

                    //Added in v2.1. Default check method for RadioButtons;
                    boolean match = checkAnswer(chosenAnswer, correctAnswers[checkIndex]);
                    if (match) {
                        quizScore += 10;
                    }

                    changeViewContent();

                    answers0.setText(answersArray[answerIndex]);
                    answerIndex++;
                    answers1.setText(answersArray[answerIndex]);
                    answerIndex++;
                    answers2.setText(answersArray[answerIndex]);
                    answerIndex++;
                    answers3.setText(answersArray[answerIndex]);
                    answerIndex++;

                    answers0.setChecked(false);
                    answers1.setChecked(false);
                    answers2.setChecked(false);
                    answers3.setChecked(false);
                } else if (questionIndex >= 8 && questionIndex <= 9) {

                    //Added in v2.1. Default method for CheckBox answer check;

                    if (questionIndex == 8) {
                        findViewById(R.id.answersField).setVisibility(View.GONE);
                        findViewById(R.id.answersField2).setVisibility(View.VISIBLE);
                        findViewById(R.id.answersField3).setVisibility(View.GONE);

                        changeViewContent();

                        answers0check.setText(answersArray[answerIndex]);
                        answerIndex++;
                        answers1check.setText(answersArray[answerIndex]);
                        answerIndex++;
                        answers2check.setText(answersArray[answerIndex]);
                        answerIndex++;
                        answers3check.setText(answersArray[answerIndex]);
                        answerIndex++;

                        selectedCheckBoxView();
                    } else if (questionIndex == 9) {

                        //Added in v2.1. Default method for EditText answer check;

                        findViewById(R.id.answersField).setVisibility(View.GONE);
                        findViewById(R.id.answersField2).setVisibility(View.GONE);
                        findViewById(R.id.answersField3).setVisibility(View.VISIBLE);

                        changeViewContent();

                        answers0text.setText(answersArray[answerIndex]);
                        answerIndex++;
                        answers1text.setText(answersArray[answerIndex]);
                        answerIndex++;
                        answers2text.setText(answersArray[answerIndex]);
                        answerIndex++;
                        answers3text.setText(answersArray[answerIndex]);
                        answerIndex++;
                    }
                } else if (questionNumber > 9) {
                    /*
                    * Goes to Result intent after the last result;
                    * Bypasses EditText check if NULL in order not to crash the application;
                    * */

                    if (!(chosenAnswer == null)) {
                        if (chosenAnswer.equals(correctAnswers[checkIndex])) {
                            quizScore += 10;
                        }
                    }

                    Intent toResult = new Intent(getApplicationContext(), ResultActivity.class);
                    toResult.putExtra("playerNamesMain", playerNamesMain);
                    toResult.putExtra("quizScore", quizScore);
                    startActivity(toResult);
                    finish();
                }
            }
        });
    }

    /*
    * @return Returns true if the chosen answer is the same as the correct answer;
    * If true, in method nextView(), 10 points will be added once for each correct answer;
    * */

    private boolean checkAnswer(String chosen, String correct) {
        return (chosen == correct);
    }

    /*
    * Sets up View resources according to the View that is being loaded;
    * Background image, question number, question, question Text, answer text;
    * Displays a Toast message with the answered questions;
    *
    * @see onCreate doc for variable documentation;
    * */

    private void changeViewContent() {
        question = getResources().getString(R.string.questionTxt);
        question += questionNumber;
        questionNum.setText(question);
        questionNumber++;

        backGround.setImageResource(drawableArray[imageIndex]);
        imageIndex++;

        questionText.setText(questionArray[questionIndex]);
        questionIndex++;
        checkIndex++;

        Toast msg = Toast.makeText(getApplicationContext(), (questionNumber - 2) + toastString, Toast.LENGTH_SHORT);
        msg.show();
    }

    /*
    * Listens to player's open response entry for question number 10;
    * Removes any spaces with @return correctAnswers[checkIndex]. Strings are case sensitive;
    * */

    private void editTextCheck() {
        editTextBox0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenAnswer = editTextBox0.getText().toString();
                chosenAnswer = chosenAnswer.replaceAll("\\s+", "");
                correctAnswers[checkIndex] = correctAnswers[checkIndex].replaceAll("\\s+", "");
            }
        });
    }

    /*
    * Listens to player's CheckBox selection on each of the four CheckBoxes. Makes sure that he can
    * score the correct combination of answers only once with @param notChecked;
    *
    * Assigns once 10 points for correct combination of answers;
    *
    * @see onCreate doc for variable documentation;
    * */

    private void selectedCheckBoxView() {
        answers0check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
            }
        });

        answers1check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
            }
        });

        answers2check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
            }
        });

        answers3check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
            }
        });
    }

    /*
    * Listens to RadioButton clicks. Makes sure that only one RadioButton can be selected;
    * Passes the value of the selected RadioButton to @param chosenAnswer for check;
    *
    * @see nextView doc for variable chosenAnswer;
    * @see onCreate doc for variable documentation;
    * */

    private void selectedRadioView() {
        answers0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answers0.isChecked()) {
                    answers1.setChecked(false);
                    answers2.setChecked(false);
                    answers3.setChecked(false);

                    chosenAnswer = answers0.getText().toString();
                }
            }
        });

        answers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answers1.isChecked()) {
                    answers0.setChecked(false);
                    answers2.setChecked(false);
                    answers3.setChecked(false);

                    chosenAnswer = answers1.getText().toString();
                }
            }
        });

        answers2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answers2.isChecked()) {
                    answers0.setChecked(false);
                    answers1.setChecked(false);
                    answers3.setChecked(false);

                    chosenAnswer = answers2.getText().toString();
                }
            }
        });

        answers3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answers3.isChecked()) {
                    answers0.setChecked(false);
                    answers1.setChecked(false);
                    answers2.setChecked(false);

                    chosenAnswer = answers3.getText().toString();
                }
            }
        });
    }

    /*
    * Receives question values via String values and stores them in an array.
    *
    * It can work with different string translations;
    * */

    private void fillQuestionArray() {
        questionArray[0] = getString(R.string.question0);
        questionArray[1] = getString(R.string.question1);
        questionArray[2] = getString(R.string.question2);
        questionArray[3] = getString(R.string.question3);
        questionArray[4] = getString(R.string.question4);
        questionArray[5] = getString(R.string.question5);
        questionArray[6] = getString(R.string.question6);
        questionArray[7] = getString(R.string.question7);
        questionArray[8] = getString(R.string.question8);
        questionArray[9] = getString(R.string.question9);
    }

    /*
    * Receives answer values via String values and stores them in an array.
    *
    * It can work with different string translations;
    * */

    private void fillAnswersAray() {
        answersArray[0] = getString(R.string.answerString0); // correct
        answersArray[1] = getString(R.string.answerString1);
        answersArray[2] = getString(R.string.answerString2);
        answersArray[3] = getString(R.string.answerString3);

        correctAnswers[0] = answersArray[0];

        answersArray[4] = getString(R.string.answerString4);
        answersArray[5] = getString(R.string.answerString5); // correct
        answersArray[6] = getString(R.string.answerString6);
        answersArray[7] = getString(R.string.answerString7);

        correctAnswers[1] = answersArray[5];

        answersArray[8] = getString(R.string.answerString8);
        answersArray[9] = getString(R.string.answerString9);
        answersArray[10] = getString(R.string.answerString10); // correct
        answersArray[11] = getString(R.string.answerString11);

        correctAnswers[2] = answersArray[10];

        answersArray[12] = getString(R.string.answerString12);
        answersArray[13] = getString(R.string.answerString13); // correct
        answersArray[14] = getString(R.string.answerString14);
        answersArray[15] = getString(R.string.answerString15);

        correctAnswers[3] = answersArray[13];

        answersArray[16] = getString(R.string.answerString16);
        answersArray[17] = getString(R.string.answerString17);
        answersArray[18] = getString(R.string.answerString18); // correct
        answersArray[19] = getString(R.string.answerString19);

        correctAnswers[4] = answersArray[18];

        answersArray[20] = getString(R.string.answerString20);
        answersArray[21] = getString(R.string.answerString21);
        answersArray[22] = getString(R.string.answerString22); // correct
        answersArray[23] = getString(R.string.answerString23);

        correctAnswers[5] = answersArray[22];

        answersArray[24] = getString(R.string.answerString24);
        answersArray[25] = getString(R.string.answerString25); // correct
        answersArray[26] = getString(R.string.answerString26);
        answersArray[27] = getString(R.string.answerString27);

        correctAnswers[6] = answersArray[25];

        answersArray[28] = getString(R.string.answerString28); // correct
        answersArray[29] = getString(R.string.answerString29);
        answersArray[30] = getString(R.string.answerString30);
        answersArray[31] = getString(R.string.answerString31);

        correctAnswers[7] = answersArray[28];

        answersArray[32] = getString(R.string.answerString32);
        answersArray[33] = getString(R.string.answerString33); // correct
        answersArray[34] = getString(R.string.answerString34);
        answersArray[35] = getString(R.string.answerString35);

        correctAnswers[8] = answersArray[33];

        answersArray[36] = getString(R.string.answerString36);
        answersArray[37] = getString(R.string.answerString37); // correct
        answersArray[38] = getString(R.string.answerString38);
        answersArray[39] = getString(R.string.answerString39);

        correctAnswers[9] = answersArray[37];

    }

    /*
    * Receives background resources IDs via integer values and stores them in an array.
    * */

    private void fillDrawableArray() {
        drawableArray[0] = R.drawable.pic_1;
        drawableArray[1] = R.drawable.pic_2;
        drawableArray[2] = R.drawable.pic_3;
        drawableArray[3] = R.drawable.pic_4;
        drawableArray[4] = R.drawable.pic_5;
        drawableArray[5] = R.drawable.pic_6;
        drawableArray[6] = R.drawable.pic_7;
        drawableArray[7] = R.drawable.pic_8;
        drawableArray[8] = R.drawable.pic_9;
        drawableArray[9] = R.drawable.pic_10;
    }
}