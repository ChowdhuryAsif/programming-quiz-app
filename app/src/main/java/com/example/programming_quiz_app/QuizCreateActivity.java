package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.programming_quiz_app.models.Question;

import java.util.ArrayList;
import java.util.List;


public class QuizCreateActivity extends AppCompatActivity {

    List<Question> questionList  = new ArrayList<>();
    private EditText title,noOfQues,ques,opTA,opTB,opTC,opTD,ans;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_create);
        title=(EditText)findViewById(R.id.titleText);
        noOfQues=(EditText)findViewById(R.id.quesCount);
        ques=(EditText)findViewById(R.id.quesText);
        opTA=(EditText)findViewById(R.id.opA);
        opTB=(EditText)findViewById(R.id.opB);
        opTC=(EditText)findViewById(R.id.opC);
        opTD=(EditText)findViewById(R.id.opD);
        ans=(EditText)findViewById(R.id.ansText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSaveButton();
            }
        });

}

    public  void setSaveButton(){

        /*loop to take certain ques*/
        String Q=ques.getText().toString().trim();
        String OpA=opTA.getText().toString().trim();
        String OpB=opTB.getText().toString().trim();
        String OpC=opTC.getText().toString().trim();
        String OpD=opTD.getText().toString().trim();
        String Answer=ans.getText().toString().trim();

        Question question=new Question(Q,OpA,OpB,OpC,OpD,Answer);
        questionList.add(question);

    }


}