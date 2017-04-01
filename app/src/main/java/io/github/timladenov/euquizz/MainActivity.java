package io.github.timladenov.euquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String playerNamesMain;
    public int answerIndex = 0;
    public int quizScore = 0;
    public String[] correctAnswers;
    public String chosenAnswer;
    private TextView questionNum;
    private TextView questionText;
    private RadioButton answers0;
    private RadioButton answers1;
    private RadioButton answers2;
    private RadioButton answers3;
    private ImageView backGround;
    private Button nextView;
    private String[] questionArray;
    private String[] answersArray;
    private int questionNumber = 2;
    private int questionIndex = 0;
    private int checkIndex = 0;
    private int imageIndex = 1;
    private int[] drawableArray = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionArray = new String[10];
        answersArray = new String[40];
        correctAnswers = new String[10];

        questionNum = (TextView) findViewById(R.id.questionNumber);
        questionText = (TextView) findViewById(R.id.question);
        answers0 = (RadioButton) findViewById(R.id.answers0);
        answers1 = (RadioButton) findViewById(R.id.answers1);
        answers2 = (RadioButton) findViewById(R.id.answers2);
        answers3 = (RadioButton) findViewById(R.id.answers3);
        backGround = (ImageView) findViewById(R.id.quesitonBackground);
        nextView = (Button) findViewById(R.id.nextView);

        fillQuestionArray();
        fillAnswersAray();
        fillDrawableArray();

        String question = "Question ";
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
        selectedView();

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
    }

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
        super.onSaveInstanceState(savedInstanceState);
    }

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
        setScreen();
    }

    public void setScreen() {

        questionNum = (TextView) findViewById(R.id.questionNumber);
        questionText = (TextView) findViewById(R.id.question);
        answers0 = (RadioButton) findViewById(R.id.answers0);
        answers1 = (RadioButton) findViewById(R.id.answers1);
        answers2 = (RadioButton) findViewById(R.id.answers2);
        answers3 = (RadioButton) findViewById(R.id.answers3);
        backGround = (ImageView) findViewById(R.id.quesitonBackground);
        nextView = (Button) findViewById(R.id.nextView);

        if (questionNumber > 2) {
            String question = "Question ";
            question += Integer.toString(questionNumber - 1);
            questionNum.setText(question);
            questionText.setText(questionArray[questionIndex - 1]);
            answers0.setText(answersArray[answerIndex - 4]);
            answers1.setText(answersArray[answerIndex - 3]);
            answers2.setText(answersArray[answerIndex - 2]);
            answers3.setText(answersArray[answerIndex - 1]);
            backGround.setImageResource(drawableArray[imageIndex - 1]);
        }
    }

    public void nextView() {
        nextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tmpQuestionNumber = "Question ";

                if (questionNumber <= 10) {

                    if (chosenAnswer == correctAnswers[checkIndex]) {
                        quizScore += 10;
                    }

                    tmpQuestionNumber += questionNumber;
                    questionNum.setText(tmpQuestionNumber);
                    questionNumber++;

                    backGround.setImageResource(drawableArray[imageIndex]);
                    imageIndex++;

                    questionText.setText(questionArray[questionIndex]);
                    questionIndex++;
                    checkIndex++;

                    Toast msg = Toast.makeText(getApplicationContext(), (questionNumber - 2) + " out of 10 questions answered.\n", Toast.LENGTH_SHORT);
                    msg.show();

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
                }
            }
        });
    }

    public void selectedView() {
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

    public void fillQuestionArray() {
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

    public void fillAnswersAray() {
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

    public void fillDrawableArray() {
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