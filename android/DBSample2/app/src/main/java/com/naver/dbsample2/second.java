package com.naver.dbsample2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class second extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    EditText getName, getNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        getName = (EditText)findViewById(R.id.getName);
        getNum = (EditText)findViewById(R.id.getNum);

        Intent intent = getIntent();

        final String groupName = intent.getStringExtra("그룹명");
        final String groupNum = intent.getStringExtra("인원수");
        getName.setText(groupName);
        getNum.setText(groupNum);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("이전인원수", groupNum);
                outIntent.putExtra("인원수", getNum.getText().toString());
                setResult(1, outIntent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("그룹명", getName.getText().toString());
                setResult(-1, outIntent);
                finish();
            }
        });

    }
}
