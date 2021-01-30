package com.example.programming_quiz_app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.programming_quiz_app.db.QuizDAO;
import com.example.programming_quiz_app.models.Quiz;

@Database(entities = {Quiz.class}, version = 1)
public abstract class SQLiteRoomDB extends RoomDatabase {

    public abstract QuizDAO quizDAO();

    private static volatile SQLiteRoomDB INSTANCE;

    static SQLiteRoomDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SQLiteRoomDB.class, "Quiz_Database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
