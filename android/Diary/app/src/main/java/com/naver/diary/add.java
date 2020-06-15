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

public class add extends AppCompatActivity {
    Button btnAddDate,  btnAddItem;
    EditText editAddContent;
    TextView textViewAddDate;
    CalendarView calendargetDate;
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAddDate = (Button)findViewById(R.id.btnAddDate);
        btnAddItem = (Button)findViewById(R.id.btnAddItem);
        editAddContent = (EditText)findViewById(R.id.editAddContent);
        textViewAddDate = (TextView)findViewById(R.id.textViewAddDate);
        calendargetDate = (CalendarView)findViewById(R.id.calendargetDate);

        long now = System.currentTimeMillis();
        Date curDate = new Date(now);

        selectYear = curDate.getYear() + 1900;
        selectMonth = curDate.getMonth() + 1;
        selectDay = curDate.getDay();

        textViewAddDate.setText(Integer.toString(selectYear)+"-"+Integer.toString(selectMonth)
                +"-"+Integer.toString(selectDay));

        btnAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();

            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o_intent = new Intent(getApplicationContext(), list.class);
                o_intent.putExtra("date",textViewAddDate.getText());
                o_intent.putExtra("content",editAddContent.getText().toString());
                setResult(0,o_intent);
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
                textViewAddDate.setText(Integer.toString(selectYear)+"-"+Integer.toString(selectMonth)
                        +"-"+Integer.toString(selectDay));
            }
        }, selectYear, selectMonth, selectDay);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }
}
