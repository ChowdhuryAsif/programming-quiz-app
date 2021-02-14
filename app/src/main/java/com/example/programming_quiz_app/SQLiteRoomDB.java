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

            questionDao.insert(new Questions("In C++, the token // is used for:","integer division","concatenation","comments","exponentiation",3));
            questionDao.insert(new Questions("Finding and fixing problems in code is known as...","Coding","Automating","Debugging","Programming",3));
            questionDao.insert(new Questions("Computers need instructions that are _______.","Wordy","Tricky","Specific","Interesting",3));
            questionDao.insert(new Questions("What must every line of code end with in Python?","comma,","semi-colon ;","parantheses ()","period .",2));
            questionDao.insert(new Questions("In C++, cout is found in which library file?","ctype.h","stdlib.h","math.h","iostream.h",4));
            questionDao.insert(new Questions("The notation of ternary operator is","&","?","|","~",2));
            questionDao.insert(new Questions("Compiler generates ___ file.","Executable code","Object code","Assembly code","None of the above.",2));
            questionDao.insert(new Questions("The notation of logical NOT operator in a C++ program is",":",";","!","None of the Above",4));
            questionDao.insert(new Questions("Which of the following denotes the C++ looping statement?", "Do-while","For","None","Both",4));
            questionDao.insert(new Questions("How many values can be returned by a C++ function?", "1","0","Infinity","None",1));
            return null;
        }
    }
}