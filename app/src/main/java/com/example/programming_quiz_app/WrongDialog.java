package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WrongDialog {

    private Context mContext;
    private Dialog wrongAnswerDialog;

    private QuizActivity mquizActivity;


    WrongDialog(Context mContext) {
        this.mContext = mContext;
    }

    void WrongDialog(String correctAnswer,QuizActivity quizActivity){

        mquizActivity = quizActivity;
        wrongAnswerDialog = new Dialog(mContext);
        wrongAnswerDialog.setContentView(R.layout.activity_wrong_dialog);
        final Button btwrongAnswerDialog = (Button) wrongAnswerDialog.findViewById(R.id.bt_wrongDialog);
        TextView textView = (TextView) wrongAnswerDialog.findViewById(R.id.textView_Correct_Answer);

        textView.setText("Correct Ans: " + correctAnswer);

        btwrongAnswerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wrongAnswerDialog.dismiss();
                mquizActivity.setQuestionView();
            }
        });

        wrongAnswerDialog.show();
        wrongAnswerDialog.setCancelable(false);
        wrongAnswerDialog.setCanceledOnTouchOutside(false);


    }

}