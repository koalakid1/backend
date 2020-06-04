package com.naver.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btn1, btn2, btn3, btn4;
    TextView tv1;
    String num1, num2;
    Integer result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edt1=(EditText)findViewById(R.id.editText);
        this.edt2=(EditText)findViewById(R.id.editText2);

        this.btn1=(Button)findViewById(R.id.Button01);
        this.btn2=(Button)findViewById(R.id.Button02);
        this.btn3=(Button)findViewById(R.id.Button03);
        this.btn4=(Button)findViewById(R.id.Button04);

        this.tv1=(TextView)findViewById(R.id.TextView);

        this.btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                tv1.setText("계산결과 : " + result);
            }
        });

        this.btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                tv1.setText("계산결과 : " + result);
            }
        });

        this.btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                tv1.setText("계산결과 : " + result);
            }
        });

        this.btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                num1 = edt1.getText().toString();
                num2 = edt2.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                tv1.setText("계산결과 : " + result);
            }
        });

    }
}
