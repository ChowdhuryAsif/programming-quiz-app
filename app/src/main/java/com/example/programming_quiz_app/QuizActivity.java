package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.programming_quiz_app.models.Quiz;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView quizListView;

    ArrayList<Quiz> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    @Override
    protected void onStart() {
        super.onStart();

        quizListView = findViewById(R.id.quizListView);

        quizList.add(new Quiz(quizList.size()+1, "C++", new ArrayList<>()));
        quizList.add(new Quiz(quizList.size()+1, "JAVA", new ArrayList<>()));
        quizList.add(new Quiz(quizList.size()+1, "Python", new ArrayList<>()));

        setListView(quizList);

        quizListView.setOnItemClickListener(this);
    }

    private void setListView(ArrayList<Quiz> newQuizList) {
        ArrayAdapter<Quiz> quizAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newQuizList);
        quizListView.setAdapter(quizAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(QuizActivity.this, QuizContainerActivity.class);
        intent.putExtra("quizItem", quizList.get(position).getId());
        startActivity(intent);
    }

    private ArrayList<Quiz> setItemsOnArrayList(ArrayList<Quiz> list, Quiz newItem){
        list.add(newItem);
        return list;
    }

    public void addQuizBtnHandler(View view) {
        ArrayList<Quiz> newQuizList = setItemsOnArrayList(quizList, new Quiz(quizList.size()+1, "JavaScript", new ArrayList<>()));

        setListView(newQuizList);
    }
}