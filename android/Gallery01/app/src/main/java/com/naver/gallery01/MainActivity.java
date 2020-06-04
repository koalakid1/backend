package com.naver.gallery01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1;
    TextView textView1;
    RadioGroup radioGroup1;
    RadioButton radioButton1, radioButton2, radioButton3;
    Button button1;
    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox01);

        textView1 = (TextView)findViewById(R.id.textView02);

        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup01);

        radioButton1 = (RadioButton)findViewById(R.id.radioButton01);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton02);
        radioButton3 = (RadioButton)findViewById(R.id.radioButton03);

        button1 = (Button)findViewById(R.id.button01);

        imageView1 = (ImageView)findViewById(R.id.imageView01);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox1.isChecked()){
                    textView1.setVisibility(View.VISIBLE);
                    radioGroup1.setVisibility(View.VISIBLE);
                    button1.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
                }else {
                    textView1.setVisibility(View.INVISIBLE);
                    radioGroup1.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.INVISIBLE);
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.radioButton01:
                        imageView1.setImageResource(R.drawable.dog);
                        break;
                    case R.id.radioButton02:
                        imageView1.setImageResource(R.drawable.rabbit);
                        break;
                    case R.id.radioButton03:
                        imageView1.setImageResource(R.drawable.cat);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"동물을 선택하세요", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
