package com.example.programming_quiz_app.DAOs;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.programming_quiz_app.models.Questions;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * from questions_table")
    LiveData<List<Questions>> getAllQuestions();

    @Insert
    void insert(Questions questions);

}