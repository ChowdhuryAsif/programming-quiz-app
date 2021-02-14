package com.example.programming_quiz_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.programming_quiz_app.models.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion;
    TextView textViewScore,textViewQuestionCount,textViewCountDownTimer;


    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rbGroup;
    Button buttonNext;

    boolean answerd = false;


    List<Questions> quesList;
    Questions currentQ;


    private int questionCounter=0,questionTotalCount;

    private QuestionViewModel questionViewModel;

    private ColorStateList textColorofButtons;

    private Handler handler = new Handler();

    private int correctAns = 0,wrongAns = 0, score = 0;

    private TimerDialog timerDialog;
    private WrongDialog wrongDialog;
    private CorrectDialog correctDialog;

    private int totalSizeofQuiz;


    // set timer for quiz=======
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        insertNewData();

        setupUI();

        textColorofButtons = rb1.getTextColors();

        timerDialog =  new TimerDialog(this);
        wrongDialog =  new WrongDialog(this);
        correctDialog = new CorrectDialog(this);


        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getmAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable List<Questions> questions) {
                Toast.makeText(QuizActivity.this, "Get IT :)", Toast.LENGTH_SHORT).show();

                fetchContent(questions);

            }
        });
        Log.i("QuizActivity","onCreate() in QuizActivity");
    }

    private void insertNewData() {
        List<Questions> questionsList = new ArrayList<>();

        questionsList.add(new Questions("In C++, the token is used for:","integer division","concatenation","comments","exponentiation",3));
        questionsList.add(new Questions("In C++, the token is used for:","integer division","concatenation","comments","exponentiation",3));
        questionsList.add(new Questions("Finding and fixing problems in code is known as...","Coding","Automating","Debugging","Programming",3));
        questionsList.add(new Questions("Computers need instructions that are _______.","Wordy","Tricky","Specific","Interesting",3));
        questionsList.add(new Questions("What must every line of code end with in Python?","comma,","semi-colon ;","parantheses ()","period .",2));
        questionsList.add(new Questions("In C++, cout is found in which library file?","ctype.h","stdlib.h","math.h","iostream.h",4));
        questionsList.add(new Questions("The notation of ternary operator is","&","?","|","~",2));
        questionsList.add(new Questions("Compiler generates ___ file.","Executable code","Object code","Assembly code","None of the above.",2));
        questionsList.add(new Questions("The notation of logical NOT operator in a C++ program is",":",";","!","None of the Above",4));
        questionsList.add(new Questions("Which of the following denotes the C++ looping statement?", "Do-while","For","None","Both",4));
        questionsList.add(new Questions("How many values can be returned by a C++ function?", "1","0","Infinity","None",1));


//        for(int i=0; i<questionsList.size(); i++){
//            final Questions q = questionsList.get(i);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    SQLiteRoomDB.getInstance(QuizActivity.this.getApplicationContext())
//                            .questionDao()
//                            .insert(q);
//                }
//            }).start();
//        }
    }


    void setupUI(){


        textViewCountDownTimer = findViewById(R.id.txtTimer);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        txtQuestion = findViewById(R.id.txtQuetsionContainer);


        rbGroup = findViewById(R.id.raido_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);

        buttonNext = findViewById(R.id.button_Next);

    }


    private void fetchContent(List<Questions> questions) {

        quesList = questions;

        startQuiz();

    }


    public void setQuestionView(){

        rbGroup.clearCheck();

        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);

        questionTotalCount = quesList.size();
        // shuffling the ques list for random ques

        Collections.shuffle(quesList);
        if (questionCounter < questionTotalCount -1){

            currentQ = quesList.get(questionCounter);

            txtQuestion.setText(currentQ.getQuestion());
            rb1.setText(currentQ.getOptA());
            rb2.setText(currentQ.getOptB());
            rb3.setText(currentQ.getOptC());
            rb4.setText(currentQ.getOptD());
            questionCounter++;

            answerd = false;

            buttonNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter +"/" +(questionTotalCount-1));

            timeLeftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }else {
            Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonNext.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resultData();
                }
            },2000);
        }
    }

    private void startQuiz() {
        setQuestionView();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.radio_button1:

                        rb1.setTextColor(Color.MAGENTA);
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        break;

                    case R.id.radio_button2:

                        rb1.setTextColor(Color.BLACK);
                        rb2.setTextColor(Color.MAGENTA);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        break;

                    case R.id.radio_button3:

                        rb1.setTextColor(Color.BLACK);
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.MAGENTA);
                        rb4.setTextColor(Color.BLACK);

                        break;

                    case R.id.radio_button4:

                        rb1.setTextColor(Color.BLACK);
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.MAGENTA);

                        break;
                }
            }
        });



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answerd){

                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        quizOpeartion();
                    }else {
                        Toast.makeText(QuizActivity.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void quizOpeartion() {

        answerd = true;

        countDownTimer.cancel();

        RadioButton rbselected =  findViewById(rbGroup.getCheckedRadioButtonId());

        int answerNr = rbGroup.indexOfChild(rbselected) +1;

        checkSolution(answerNr,rbselected);
    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQ.getAnswer()) {

            case 1:

                if (currentQ.getAnswer() == answerNr) {

                    rb1.setTextColor(Color.WHITE);

                    correctAns++;


                    score += 5;
                    textViewScore.setText("Score: " + score);

                    correctDialog.correctDialog(score,this);
                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb1.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                }
                break;

            case 2:

                if (currentQ.getAnswer() == answerNr) {
                    rb2.setTextColor(Color.WHITE);
                    correctAns++;

                    score += 5;
                    textViewScore.setText("Score: " + score);

                    correctDialog.correctDialog(score,this);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb2.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                }
                break;

            case 3:

                if (currentQ.getAnswer() == answerNr) {
                    rb3.setTextColor(Color.WHITE);
                    correctAns++;
                    score += 5;
                    textViewScore.setText("Score: " + score);
                    correctDialog.correctDialog(score,this);
                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb3.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                }
                break;

            case 4:

                if (currentQ.getAnswer() == answerNr) {
                    rb4.setTextColor(Color.WHITE);
                    correctAns++;
                    score += 5;
                    textViewScore.setText("Score: " + score);
                    correctDialog.correctDialog(score,this);
                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb4.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                }
                break;
        }

        if (questionCounter == questionTotalCount){
            buttonNext.setText("Confirm and Finish");
        }

    }

    private void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setTextColor(Color.WHITE);
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftinMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftinMillis/1000) /60;
        int seconds = (int) (timeLeftinMillis/1000) %60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        textViewCountDownTimer.setText(timeFormatted);

        if (timeLeftinMillis <10000){
            textViewCountDownTimer.setTextColor(Color.RED);
        }else {
            textViewCountDownTimer.setTextColor(Color.GREEN);
        }

        if (timeLeftinMillis == 0){

            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timerDialog.timerDialog();
                }
            },2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }

        Log.i("QuizActivity","onDestroy in QuizActivity");

    }

    private void resultData(){

        Intent resultofQuiz = new Intent(QuizActivity.this,ResultActivity.class);
        resultofQuiz.putExtra("UserScore", score);
        resultofQuiz.putExtra("TotalQuizQuestions",(questionTotalCount -1));
        resultofQuiz.putExtra("CorrectQuestions",correctAns);
        resultofQuiz.putExtra("WrongQuestions",wrongAns);
        startActivity(resultofQuiz);
    }


    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(QuizActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("QuizActivity","onStop() in QuizActivity");
        finish();
    }
}