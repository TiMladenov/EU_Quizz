package io.github.timladenov.euquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Make the app to save data state on screen rotation
    public String playerNamesMain;
    public int answerIndex = 0;
    public int quizScore = 0;
    public String[] correctAnswers;
    public String chosenAnswer;
    private TextView questionNum;
    private TextView questionText;

    //TO DO: Change to 0 and remove all precoded answer attributes from strings.xml to load from 0 element on start.
    //TO DO: Create a method only on first start that will populate first question and answers, it will pick up with
    //@param fillQuestionArray(); and @param fillAnswersAray(); from there to load next elements
    private RadioButton answers0;
    private RadioButton answers1;
    private RadioButton answers2;
    private RadioButton answers3;
    private ImageView backGround;
    private String[] questionArray;
    private String[] answersArray;
    private int questionNumber = 2;
    private int questionIndex = 1;
    private int genericIndex = 0;
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

        fillQuestionArray();
        fillAnswersAray();
        fillDrawableArray();

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

    public void selectAnswer(View view) {

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

    public void changeViewNext(View view) {

        String tmpQuestionNumber = "Question ";

        if (questionNumber <= 10) {

            if (chosenAnswer == correctAnswers[genericIndex]) {
                quizScore += 10;
            }

            tmpQuestionNumber += questionNumber;
            questionNum.setText(tmpQuestionNumber);
            questionNumber++;

            backGround.setImageResource(drawableArray[genericIndex]);
            genericIndex++;

            questionText.setText(questionArray[questionIndex]);
            questionIndex++;

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

    public void fillQuestionArray() {
        questionArray[0] = "When was the European Union created?";
        questionArray[1] = "How many members did the Union count when the Euro began circulation in January 2000?";
        questionArray[2] = "How many countries were members of the Eurozone when Euro coins and banknotes were first circulated on 1st January 2002?";
        questionArray[3] = "What was the former name of the currency for Europe, now known as the Euro?";
        questionArray[4] = "How many stars are there on the European Flag?";
        questionArray[5] = "Who was not a president of the European Commission?";
        questionArray[6] = "Which of these is not part of the European Union?";
        questionArray[7] = "The Presidency of the European Union is rotated every _ months?";
        questionArray[8] = "Who is regarded as the 'chief architect' of European Unity?";
        questionArray[9] = "When you cross the border from Germany to the Netherlands without showing your passport you do so due to:";
    }

    public void fillAnswersAray() {
        answersArray[0] = "In 1993"; // correct
        answersArray[1] = "In 2014";
        answersArray[2] = "In 2003";
        answersArray[3] = "In 1956";

        correctAnswers[0] = answersArray[0];

        answersArray[4] = "18";
        answersArray[5] = "15"; // correct
        answersArray[6] = "12";
        answersArray[7] = "10";

        correctAnswers[1] = answersArray[5];

        answersArray[8] = "9";
        answersArray[9] = "18";
        answersArray[10] = "12"; // correct
        answersArray[11] = "10";

        correctAnswers[2] = answersArray[10];

        answersArray[12] = "E-Mark";
        answersArray[13] = "ECU"; // correct
        answersArray[14] = "Sterling";
        answersArray[15] = "Pound";

        correctAnswers[3] = answersArray[13];

        answersArray[16] = "9";
        answersArray[17] = "15";
        answersArray[18] = "12"; // correct
        answersArray[19] = "10";

        correctAnswers[4] = answersArray[18];

        answersArray[20] = "Santer";
        answersArray[21] = "Prodi";
        answersArray[22] = "Dehaene"; // correct
        answersArray[23] = "Junker";

        correctAnswers[5] = answersArray[22];

        answersArray[24] = "The European Council";
        answersArray[25] = "The Council of Europe"; // correct
        answersArray[26] = "The Council";
        answersArray[27] = "The European Commission";

        correctAnswers[6] = answersArray[25];

        answersArray[28] = "2y, 6m"; // correct
        answersArray[29] = "0y, 12m";
        answersArray[30] = "4y, 0m";
        answersArray[31] = "5y, 0m";

        correctAnswers[7] = answersArray[28];

        answersArray[32] = "Robert Schuman";
        answersArray[33] = "Jean Monnet"; // correct
        answersArray[34] = "Winston Churchill";
        answersArray[35] = "Schuman Robert";

        correctAnswers[8] = answersArray[33];

        answersArray[36] = "The Maastricht Treaty";
        answersArray[37] = "The Schengen Agreement"; // correct
        answersArray[38] = "The Brussels Agreement";
        answersArray[39] = "The Treaty of Rome";

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
