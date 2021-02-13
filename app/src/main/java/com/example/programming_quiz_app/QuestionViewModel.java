package com.example.programming_quiz_app;

import android.app.Application;

import com.example.programming_quiz_app.models.Questions;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionsRepository mRepository;

    private LiveData<List<Questions>> mAllQuestions;

    public QuestionViewModel(Application application){
        super(application);
        mRepository = new QuestionsRepository(application);
        mAllQuestions = mRepository.getmAllQuestions();

    }

    LiveData<List<Questions>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}