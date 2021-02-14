package com.example.programming_quiz_app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.programming_quiz_app.DAOs.QuestionDao;
import com.example.programming_quiz_app.models.Questions;

@Database(entities = {Questions.class}, version = 1)
public abstract class SQLiteRoomDB extends RoomDatabase {

    public abstract QuestionDao questionDao();

    private static volatile SQLiteRoomDB INSTANCE;

    public static synchronized SQLiteRoomDB getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    SQLiteRoomDB.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback(){


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private QuestionDao questionDao;


        private PopulateDbAsyncTask(SQLiteRoomDB db){

            questionDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(Void... voids){


            questionDao.insert(new Questions("What is Android?","OS","Browser","Software","Hard Drive",1));
            questionDao.insert(new Questions("RAM Stands for what ?","Operating System","Browser","Random Access Memory","CD Project",3));
            questionDao.insert(new Questions("Chrome is what ?","System Software","Browser","Middle Ware","Windows",2));
            questionDao.insert(new Questions("HTML is what ?","Scipting Language","Programming Language","Software","Hyper Text Markup Language",4));
            questionDao.insert(new Questions("Unity is used for ?","Game Developement","Web Development","Graphics Design","3-D Modling",2));
            questionDao.insert(new Questions("What is OS","Hardware","System Software","PC Software","Hard Drive",2));
            questionDao.insert(new Questions("IP stand for what? ","Language","Intenet Protocol","Graphics","Random",2));
            return null;
        }
    }
}