package com.example.programming_quiz_app.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.programming_quiz_app.models.Quiz;

import java.util.List;

@Dao
public interface QuizDAO {

    @Insert
    void insertQuiz(Quiz quiz);

    @Query("SELECT * FROM quiz_table")
    List<Quiz> findAll();

    @Query("SELECT * FROM quiz_table WHERE quiz_id LIKE :id")
    Quiz findById(int id);

    @Update
    void updateTodo(Quiz quiz);
}
