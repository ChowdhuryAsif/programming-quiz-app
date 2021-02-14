package com.example.programming_quiz_app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.programming_quiz_app.models.Questions;

import java.util.List;

public class QuestionsRepository {
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionsRepository(Application application){
        mAllQuestions = SQLiteRoomDB.getInstance(application)
                .questionDao()
                .getAllQuestions();
    }

    public LiveData<List<Questions>> getmAllQuestions(){
        return mAllQuestions;
    }
}
