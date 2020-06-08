package com.naver.androidexercise2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
//    RadioButton rRed, rGreen, rBlue;
    RadioGroup radioGroup;
    View dialogView;
    LinearLayout baseLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        try {
            FileInputStream inFs = openFileInput("config.txt");
            byte[] txt = new byte[inFs.available()];
            inFs.read(txt);
            String str = new String(txt);

            switch (str){
                case "RED":
                    baseLayout.setBackgroundColor(Color.RED);
                    break;
                case "GREEN":
                    baseLayout.setBackgroundColor(Color.GREEN);
                    break;
                case "BLUE":
                    baseLayout.setBackgroundColor(Color.BLUE);
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "색이 지정되지 않음", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "config.txt가 없음", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("앱 배경색 설정");
        dlg.setView(dialogView);
        dlg.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {
                    FileOutputStream outFs = openFileOutput("config.txt", Context.MODE_PRIVATE);
                    radioGroup = (RadioGroup)dialogView.findViewById(R.id.radioGroup);
                    String str = null;
                    switch (radioGroup.getCheckedRadioButtonId()){
                        case R.id.radioRed:
                            baseLayout.setBackgroundColor(Color.RED);
                            str = "RED";
                            break;
                        case R.id.radioGreen:
                            baseLayout.setBackgroundColor(Color.GREEN);
                            str = "GREEN";
                            break;
                        case R.id.radioBlue:
                            baseLayout.setBackgroundColor(Color.BLUE);
                            str = "BLUE";
                            break;
                    }
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), "config.txt가 생성됨", Toast.LENGTH_LONG).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        dlg.show();
        return true;
    }
}
