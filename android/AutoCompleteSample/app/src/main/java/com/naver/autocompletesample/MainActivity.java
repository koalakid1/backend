package com.naver.autocompletesample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auto = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        //1 배열 or ArrayList 생성
        String[] items = {"CSI-뉴욕","CSI-라스베거스","Friends","Fringe","Lost"};

        //2 arrayAdapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);

        //3 목록을 출력하는 뷰에 어댑터 적용
        auto.setAdapter(adapter);

        // 1,2,3 외우기. 안드로이드에서 항상 사용하는 방법 배열보다 arraylist를 더 많이 사용함.
    }
}
