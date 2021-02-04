package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.programming_quiz_app.models.Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView quizListView;

    List<Quiz> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    @Override
    protected void onStart() {
        super.onStart();

        quizListView = findViewById(R.id.quizListView);

        quizList.clear();
        findAll();

        setListView(quizList);

        quizListView.setOnItemClickListener(this);
    }

    private void setListView(List<Quiz> newQuizList) {
        ArrayAdapter<Quiz> quizAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newQuizList);
        quizListView.setAdapter(quizAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(QuizActivity.this, QuizContainerActivity.class);
        intent.putExtra("quizItem", quizList.get(position).getId());
        startActivity(intent);
    }

    private List<Quiz> setItemsOnList(List<Quiz> list, Quiz newItem){
        list.add(newItem);
        return list;
    }

    public void addQuizBtnHandler(View view) {
        Intent intent = new Intent(QuizActivity.this, QuizCreateActivity.class);

        startActivity(intent);
    }

    private void findAll() {

        List<Quiz> quizzes;
        Thread thread = new Thread(() -> {
            List<Quiz> list = SQLiteRoomDB.getInstance(getApplicationContext()).quizDAO().findAll();
            quizList.addAll(list);
        });

        thread.start();
    }
}