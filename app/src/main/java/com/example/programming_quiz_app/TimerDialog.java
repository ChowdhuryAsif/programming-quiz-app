package com.example.programming_quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimerDialog {

    private Context mContext;
    private Dialog timerDialog;


    TimerDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void timerDialog(){


        timerDialog = new Dialog(mContext);

        timerDialog.setContentView(R.layout.activity_timer_dialog);
        final Button bttimerDialog = (Button) timerDialog.findViewById(R.id.bt_timerDialog);


        bttimerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerDialog.dismiss();
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);

            }
        });

        timerDialog.show();
        timerDialog.setCancelable(false);
        timerDialog.setCanceledOnTouchOutside(false);

    }


}