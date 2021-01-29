package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.programming_quiz_app.models.Quiz;

public class QuizContainerActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_container);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int quizId = getIntent().getIntExtra("quizItem", 1);

        textView = findViewById(R.id.containerHeaderText);
        textView.setText(String.valueOf(quizId));
    }
}