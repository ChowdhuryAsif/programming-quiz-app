package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

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
        listViewUpdate();

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

    private List<Quiz> setItemOnList(List<Quiz> list, Quiz newItem){
        list.add(newItem);
        return list;
    }

    public void addQuizBtnHandler(View view) {
//        Intent intent = new Intent(QuizActivity.this, QuizCreateActivity.class);
//
//        startActivity(intent);

        Thread thread = new Thread(() -> {
           SQLiteRoomDB.getInstance(getApplicationContext())
                   .quizDAO()
                   .insertQuiz(new Quiz("GoLang", 50.00));
        });
        thread.start();
    }

    private void listViewUpdate() {
        LiveData<List<Quiz>> liveData = SQLiteRoomDB.getInstance(getApplicationContext()).quizDAO().findAll();

        liveData.observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                setListView(quizzes);
            }
        });
    }
}