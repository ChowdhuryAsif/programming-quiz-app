package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    ListView quizListView;
    List<String> quizList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    @Override
    protected void onStart() {
        super.onStart();

        quizListView = findViewById(R.id.quizListView);
        quizList = Arrays.asList(new DateFormatSymbols().getMonths());

        ArrayAdapter<String> quizAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quizList);
        quizListView.setAdapter(quizAdapter);
    }
}