package com.naver.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class update extends AppCompatActivity {
    Button btnUpdateDate,  btnUpdateItem;
    EditText editUpdateContent;
    TextView textViewUpdateDate;
    CalendarView calendargetDate;
    int selectYear, selectMonth, selectDay;
    Intent intent;
    String pastDate, pastContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnUpdateDate = (Button)findViewById(R.id.btnUpdateDate);
        btnUpdateItem = (Button)findViewById(R.id.btnUpdateItem);
        editUpdateContent = (EditText)findViewById(R.id.editUpdateContent);
        textViewUpdateDate = (TextView)findViewById(R.id.textViewUpdateDate);
        calendargetDate = (CalendarView)findViewById(R.id.calendargetDate);

        intent = getIntent();


        long now = System.currentTimeMillis();
        Date curDate = new Date(now);
        pastDate = intent.getStringExtra("date");
        pastContent = intent.getStringExtra("content");

        textViewUpdateDate.setText(pastDate);
        editUpdateContent.setText(pastContent);

        btnUpdateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        btnUpdateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewUpdateDate.getText() != pastDate || editUpdateContent.getText().toString() != pastContent){
                    Intent o_intent = new Intent(getApplicationContext(), list.class);
                    o_intent.putExtra("date",textViewUpdateDate.getText());
                    o_intent.putExtra("content",editUpdateContent.getText().toString());
                    setResult(1,o_intent);
                    finish();
                }
                setResult(0);
                finish();
            }
        });
    }

    void showDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month+1;
                selectDay = dayOfMonth;
                textViewUpdateDate.setText(Integer.toString(selectYear)+"-"+Integer.toString(selectMonth)
                        +"-"+Integer.toString(selectDay));
            }
        }, selectYear, selectMonth, selectDay);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }

    @Override // 뒤로가기 눌렀을때 저장하시겠습니까 만들어서 하기!!
    public void onBackPressed() {
        super.onBackPressed();
        Intent outIntent = new Intent(getApplicationContext(), list.class);

        if (editUpdateContent.getText().toString() != intent.getStringExtra("content") ||
                textViewUpdateDate.getText() != intent.getStringExtra("date")){
            outIntent.putExtra("date", textViewUpdateDate.getText());
            outIntent.putExtra("content", editUpdateContent.getText());
            outIntent.putExtra("p_date", intent);
            outIntent.putExtra("p_content", pastContent);
            setResult(1,outIntent);
            finish();
        }else{
            setResult(0,outIntent);
            finish();
        }
    }
}
