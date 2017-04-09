/*
* @author   Tihomri Mladenov <tihomir.mladenov777@gmail.com>
*           This application has been created for final Project 3 on Udacity's Google sponsored "Android For Beginners" course;
*           02 April 2017
*
* @version  v2.2 final
* @since    v1.0a
* */

package io.github.timladenov.euquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import static io.github.timladenov.euquizz.R.id.editTextBox0;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private final int QD_MAX_COUNT = 10;
    private final int ANSWER_MAX_COUNT = 40;
    public String playerNamesMain;
    public int answerIndex;
    public int quizScore;
    public String[] correctAnswers;
    public String chosenAnswer;
    private EditText editTextAnswer;
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
    private ScrollView mainScrollView;
    private String question;
    private boolean notChecked;
    private boolean CheckedAnswerQ8;
    private boolean notCheckedQ8;
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
    *  Calls method fillAnswersArray(),      which populates the answers array;
    *  Calls method fillDrawableArray(),    which populates the drawables ID array;
    *  Calls method nextView(),             which if used will call next view;
    *  Calls method selectedRadioView(),    which if used will pass the selected answer;
    *  Calls method selectedCheckBoxView(), which if used will pass the selected checkBox answer, if correct;
    *
    *  @param QD_MAX_COUNT      is the maximum array size for question text and drawables;
    *  @param ANSWER_MAX_COUNT  is the maximum array size for answer text;
    *  @param mainScrollView    is used to reset ScrollView to top position on each Next button click;
    *  @param questionArray     is an array that keeps all questions. Data is received via "string" values;
    *  @param answersArray      is an array that keeps all answers. Data is received via "string" values;
    *  @param correctAnswers    is an array that keeps all correct answers and checks each selected answer;
    *  @param questionNumber    iterates until (incl.) 10 and updates question number together with @param question or via concatenation;
    *  @param drawableArray     is an array that contains the IDs to all background drawables;
    *  @param questionIndex     is used to iterate to the next question in questionArray;
    *  @param imageIndex        is used to iterate to the next background drawable is drawableArray;
    *  @param answerIndex       is used to iterate to the next 4 answers in answerArray;
    *  @param checkIndex        is used to iterate through the correct answers array;
    *  @param playerNamesMain   is used to store player's name, which is later passed to ResultActivity;
    *  @param toastString       is used to store Toast message template text;
    *  @param question          is used as a template to create question number;
    *  @param notChecked        makes sure that score from correct CheckBox combination will be
    *                           updated once in activitiy's lifecycle;
    *  @param notCheckedQ8      makes sure that score from correct Question 8 answer will be
    *                           updated once in activitiy's lifecycle;
    *  @param CheckedAnswerQ8   is used to detect whether the corrected answer has been selected
    *                           in Question 8 prior to submission.
    *
    *  @param questionNum           is used to set the text in question number TextView;
    *  @param questionText          is used to set the text in question TextView;
    *  @param answers 0 - 4         is used to set the answers for each question;
    *  @param backGround            is used to set the background for each question;
    *  @param nextView              is used to get the ID of Next button;
    *  @param answers0check 0 - 4   is used to set the answers for CheckBox Question 9;
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

        questionArray = new String[QD_MAX_COUNT];
        answersArray = new String[ANSWER_MAX_COUNT];
        correctAnswers = new String[QD_MAX_COUNT];
        drawableArray = new int[QD_MAX_COUNT];

        /*
        * Sets up the fields for RadioButton questions;
        * */

        mainScrollView = (ScrollView) findViewById(R.id.scrollView);
        questionNum = (TextView) findViewById(R.id.questionNumber);
        questionText = (TextView) findViewById(R.id.question);
        answers0 = (RadioButton) findViewById(R.id.answers0);
        answers0.setOnClickListener(this);
        answers1 = (RadioButton) findViewById(R.id.answers1);
        answers1.setOnClickListener(this);
        answers2 = (RadioButton) findViewById(R.id.answers2);
        answers2.setOnClickListener(this);
        answers3 = (RadioButton) findViewById(R.id.answers3);
        answers3.setOnClickListener(this);
        backGround = (ImageView) findViewById(R.id.quesitonBackground);
        nextView = (Button) findViewById(R.id.nextView);
        nextView.setOnClickListener(this);

        /*
        * Sets up the fields for CheckBox questions;
        * */

        answers0check = (CheckBox) findViewById(R.id.checkbox0);
        answers0check.setOnClickListener(this);
        answers1check = (CheckBox) findViewById(R.id.checkbox1);
        answers1check.setOnClickListener(this);
        answers2check = (CheckBox) findViewById(R.id.checkbox2);
        answers2check.setOnClickListener(this);
        answers3check = (CheckBox) findViewById(R.id.checkbox3);
        answers3check.setOnClickListener(this);

        /*
        * Sets up the fields for TextView for the questions and EditText view for response entry with @param editTextAnswer;
        * */

        answers0text = (TextView) findViewById(R.id.textBox0);
        answers1text = (TextView) findViewById(R.id.textBox1);
        answers2text = (TextView) findViewById(R.id.textBox2);
        answers3text = (TextView) findViewById(R.id.textBox3);
        editTextAnswer = (EditText) findViewById(editTextBox0);
        editTextAnswer.setOnClickListener(this);
        notChecked = true;
        CheckedAnswerQ8 = false;
        notCheckedQ8 = true;

        fillQuestionArray();
        fillAnswersArray();
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
        savedInstanceState.putString("correctAns", correctAnswers[checkIndex]);
        savedInstanceState.putBoolean("notChecked", notChecked);
        savedInstanceState.putBoolean("notCheckedQ8", notCheckedQ8);
        savedInstanceState.putBoolean("CheckedAnswerQ8", CheckedAnswerQ8);
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
        correctAnswers[checkIndex] = savedInstanceState.getString("correctAns");
        notChecked = savedInstanceState.getBoolean("notChecked");
        notCheckedQ8 = savedInstanceState.getBoolean("notCheckedQ8");
        CheckedAnswerQ8 = savedInstanceState.getBoolean("CheckedAnswerQ8");

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

        if (questionNumber - 1 == QD_MAX_COUNT) {
            answers0text.setText(answersArray[answerIndex - 4]);
            answers1text.setText(answersArray[answerIndex - 3]);
            answers2text.setText(answersArray[answerIndex - 2]);
            answers3text.setText(answersArray[answerIndex - 1]);
        }
    }

    /*
    * Listens to RadioButton clicks. Makes sure that only one RadioButton can be selected.
    * Passes the value of the selected RadioButton to @param chosenAnswer for check;
    *
    * Listens to player's CheckBox selection on each of the four CheckBoxes. Makes sure that he can
    * score the correct combination of answers only once with @param notChecked;
    *
    * Listens to player's open response entry for question number 10;
    * Removes any spaces with @return correctAnswers[checkIndex]. Strings are case sensitive;
    *
    * If next button is pressed and the question count is not more than 10, the method calls method changeViewContent();
    * ======================================================================================================================
    * Assigns once 10 points for correct combination of answers;
    * Shows Toast message with number of answered questions after each question;
    * ======================================================================================================================
    * Else if the question number is more than 10, the Results view is called and the player name and score is passed to it;
    *
    * @param editTextAnswer     stores the answer for Question 10;
    * @param quizScore          saves the score of the player. It adds 10 for each correct answer;
    * @param chosenAnswer       checks the selected answer to the corresponding correct answer for the respective question in question array;
    * @see onCreate doc for variable documentation;
    * */

    @Override
    public void onClick(View v) {
        int viewID = v.getId();
        switch (viewID) {
            case R.id.answers0:
                if (answers0.isChecked()) {
                    answers1.setChecked(false);
                    answers2.setChecked(false);
                    answers3.setChecked(false);
                    chosenAnswer = answers0.getText().toString();
                    if (questionIndex == 8) {
                        if (notCheckedQ8) {
                            if (chosenAnswer == correctAnswers[checkIndex]) {
                                CheckedAnswerQ8 = true;
                                updateScoreQ8(CheckedAnswerQ8);
                            }
                            notCheckedQ8 = false;
                        }
                    }
                }
                break;
            case R.id.answers1:
                if (answers1.isChecked()) {
                    answers0.setChecked(false);
                    answers2.setChecked(false);
                    answers3.setChecked(false);
                    chosenAnswer = answers1.getText().toString();
                    if (questionIndex == 8) {
                        CheckedAnswerQ8 = false;
                        if (!(CheckedAnswerQ8 == false) || notCheckedQ8 == false) {
                            updateScoreQ8(CheckedAnswerQ8);
                        }
                        notCheckedQ8 = true;
                    }
                }
                break;
            case R.id.answers2:
                if (answers2.isChecked()) {
                    answers0.setChecked(false);
                    answers1.setChecked(false);
                    answers3.setChecked(false);
                    chosenAnswer = answers2.getText().toString();
                    if (questionIndex == 8) {
                        if (!(CheckedAnswerQ8 == false) || notCheckedQ8 == false) {
                            updateScoreQ8(CheckedAnswerQ8);
                        }
                        notCheckedQ8 = true;
                    }
                }
                break;
            case R.id.answers3:
                if (answers3.isChecked()) {
                    answers0.setChecked(false);
                    answers1.setChecked(false);
                    answers2.setChecked(false);
                    chosenAnswer = answers3.getText().toString();
                    if (questionIndex == 8) {
                        if (!(CheckedAnswerQ8 == false) || notCheckedQ8 == false) {
                            updateScoreQ8(CheckedAnswerQ8);
                        }
                        notCheckedQ8 = true;
                    }
                }
            case R.id.checkbox0:
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
                break;
            case R.id.checkbox1:
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
                break;
            case R.id.checkbox2:
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
                break;
            case R.id.checkbox3:
                if (((answers0check.isChecked() && answers2check.isChecked() && answers3check.isChecked()) && !(answers1check.isChecked())) && notChecked) {
                    quizScore += 10;
                    notChecked = false;
                }
                break;
            case R.id.editTextBox0:
                chosenAnswer = editTextAnswer.getText().toString();
                chosenAnswer = chosenAnswer.replaceAll("\\s+", "");
                correctAnswers[checkIndex] = correctAnswers[checkIndex].replaceAll("\\s+", "");
                break;
            case R.id.nextView:
                if (questionIndex < 8) {

                    //Added in v2.1. Default check method for RadioButtons;
                    boolean match = checkAnswer(chosenAnswer, correctAnswers[checkIndex]);
                    if (match || CheckedAnswerQ8) {
                        if (match) {
                            quizScore += 10;
                        }
                        if (CheckedAnswerQ8) {
                            quizScore += 10;
                        }
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
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);
                break;
        }
    }

    /*
    * Called by onClick() method;
    *
    * Called when method override for Question 8 is used. If the correct answer is selected prior to
    * answer submission, 10 points will be added. If other answer is selected prior to submission,
    * the previous score count will be restored.
    * */

    public void updateScoreQ8(boolean canAdd) {
        if (canAdd == true) {
            quizScore += 10;
        } else if (canAdd == false) {
            quizScore -= 10;
        }
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
    * Receives question values via String values and stores them in an array.
    *
    * @param questionStringLocation stores the path to each resource, updates on each iteration.
    * It can work with different string translations;
    * */

    private void fillQuestionArray() {
        for (int i = 0; i < QD_MAX_COUNT; i++) {
            String questionStringLocation = "string/" + "question" + i;
            int questionID = getResources().getIdentifier(questionStringLocation, "string", getPackageName());
            questionArray[i] = getString(questionID);
        }
    }

    /*
    * Receives answer values via String values and stores them in an array.
    *
    * @param answerStringLocation stores the path to each resource, updates on each iteration.
    * It can work with different string translations;
    * */

    private void fillAnswersArray() {
        for (int i = 0; i < ANSWER_MAX_COUNT; i++) {
            String answerStringLocation = "string/" + "answerString" + i;
            int answerID = getResources().getIdentifier(answerStringLocation, "string", getPackageName());
            answersArray[i] = getString(answerID);
        }
        fillCorrectAnswersArray();
    }

    /*
    * Fills an array with the correct answers;
    * */

    private void fillCorrectAnswersArray() {
        correctAnswers[0] = answersArray[0];
        correctAnswers[1] = answersArray[5];
        correctAnswers[2] = answersArray[10];
        correctAnswers[3] = answersArray[13];
        correctAnswers[4] = answersArray[18];
        correctAnswers[5] = answersArray[22];
        correctAnswers[6] = answersArray[25];
        correctAnswers[7] = answersArray[28];
        correctAnswers[8] = answersArray[33];
        correctAnswers[9] = answersArray[37];
    }

    /*
    * Receives background resources IDs via integer values and stores them in an array.
    * */

    private void fillDrawableArray() {
        for (int i = 0; i < QD_MAX_COUNT; i++) {
            String drawableLocation = "drawable/" + "pic_" + i;
            drawableArray[i] = getResources().getIdentifier(drawableLocation, "drawable", getPackageName());
        }
    }
}