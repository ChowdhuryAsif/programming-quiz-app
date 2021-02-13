package com.example.programming_quiz_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.programming_quiz_app.models.Questions;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionViewModel(Application application){
        super(application);
        mAllQuestions = new QuestionsRepository(application).getmAllQuestions();
    }

    LiveData<List<Questions>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}