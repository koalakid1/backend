package com.naver.widgetsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calView;
    TimePicker tPicker;
    TextView textEnd;
    int selectYear, selectMonth, selectDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrono = (Chronometer)findViewById(R.id.chronometer1);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        calView = (CalendarView)findViewById(R.id.calendarView1);
        tPicker = (TimePicker)findViewById(R.id.timePicker1);
        textEnd = (TextView)findViewById(R.id.textEnd);
        rdoCal = (RadioButton)findViewById(R.id.radioButton1);
        rdoTime = (RadioButton)findViewById(R.id.radioButton2);

        calView.setVisibility(View.INVISIBLE);
        tPicker.setVisibility(View.INVISIBLE);

        Date curDate = new Date(calView.getDate());

        selectYear = curDate.getYear() + 1900;
        selectMonth = curDate.getMonth() + 1;
        selectDay = curDate.getDay();

        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calView.setVisibility(View.VISIBLE);
                tPicker.setVisibility(View.INVISIBLE);

            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calView.setVisibility(View.INVISIBLE);
                tPicker.setVisibility(View.VISIBLE);

            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
                chrono.setTextColor(Color.RED);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

                textEnd.setText(Integer.toString(selectYear)+"년 "+Integer.toString(selectMonth)+"월 "+Integer.toString(selectDay)+"일 "
                                +Integer.toString(tPicker.getCurrentHour()) + "시 " +Integer.toString(tPicker.getCurrentMinute()) + "분 예약");
                textEnd.setTextColor(Color.BLUE);
            }
        });

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectYear = year;
                selectMonth = month + 1;
                selectDay = dayOfMonth;
            }
        });
    }
}
