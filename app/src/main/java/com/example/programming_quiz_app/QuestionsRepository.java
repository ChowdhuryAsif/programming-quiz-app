package com.example.programming_quiz_app;

import android.app.Application;

import com.example.programming_quiz_app.DAOs.QuestionDao;
import com.example.programming_quiz_app.models.Questions;

import java.util.List;

public class QuestionsRepository {
    private QuestionDao mQuestionDao;
    private LiveData<List<Questions>> mAllQuestions;


    public QuestionsRepository(Application application){
        SQLiteRoomDB db = SQLiteRoomDB.getInstance(application);
        mQuestionDao = db.questionDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }


    public LiveData<List<Questions>> getmAllQuestions(){
        return mAllQuestions;
    }




}
