package com.naver.diary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class item extends AppCompatActivity {

    TextView textViewItemDate, textViewItemContent;
    Button btnItemUpdate, btnItemDelete, btnGoBack;
    String pastDate,pastContent,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        textViewItemDate = (TextView)findViewById(R.id.textViewItemDate);
        textViewItemContent = (TextView)findViewById(R.id.textViewItemContent);
        btnGoBack = (Button)findViewById(R.id.btnGoBack);
        btnItemUpdate = (Button)findViewById(R.id.btnItemUpdate);
        btnItemDelete = (Button)findViewById(R.id.btnItemDelete);

        Intent intent = getIntent();
        num = intent.getStringExtra("num");
        pastDate = intent.getStringExtra("date");
        pastContent = intent.getStringExtra("content");

        textViewItemDate.setText(pastDate);
        textViewItemContent.setText(pastContent);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(0);
                finish();
            }
        });

        btnItemUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), update.class);
                intent.putExtra("date", textViewItemDate.getText());
                intent.putExtra("content", textViewItemContent.getText().toString());
                startActivityForResult(intent, 0);
            }
        });

        btnItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), list.class);
                outIntent.putExtra("num", num);
                setResult(2,outIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            Intent outIntent = new Intent(getApplicationContext(), list.class);
            outIntent.putExtra("date", data.getStringExtra("date"));
            outIntent.putExtra("content", data.getStringExtra("content"));
            outIntent.putExtra("num",num);
            setResult(1, outIntent);
            finish();
        }
    }

    @Override // 뒤로가기 눌렀을때 저장하시겠습니까 만들어서 하기!!
    public void onBackPressed() {
        super.onBackPressed();
        setResult(0);
        finish();
    }
}
