package com.naver.dbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    Button btnInit, btnInsert, btnSelect;
    EditText editName, editNum, editNameResult, editNumResult;
    SQLiteDatabase sqlDB;

    public class MyDBHelper extends SQLiteOpenHelper{


        public MyDBHelper(Context context){
            // groupDB라는 db를 생성함.
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //테이블 생성
            db.execSQL("create table groupTBL(gName char(10) primary key, gNumber integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //데이터베이스를 삭제하고 테이블을 다시 생성 (초기화)
            db.execSQL("drop table if exists groupTBL"); // 보통 사용하지 않는 코드
            onCreate(db);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInit = (Button)findViewById(R.id.btnInit);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnSelect = (Button)findViewById(R.id.btnSelect);

        editName = (EditText)findViewById(R.id.editName);
        editNum = (EditText)findViewById(R.id.editNum);
        editNameResult = (EditText)findViewById(R.id.editNameResult);
        editNumResult = (EditText)findViewById(R.id.editNumResult);

        //MyDBHelper 생성 => DB와 table이 생성됨
        myDBHelper = new MyDBHelper(this);

        //입력 버튼
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //쓰기모드로 DB오픈
                sqlDB = myDBHelper.getWritableDatabase();
                try{
                    sqlDB.execSQL("insert into groupTBL values('"+editName.getText().toString()+"', '"
                            +editNum.getText().toString()+"')");
                    sqlDB.close();
                    Toast.makeText(getApplicationContext(), "입력완료", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "이름 중복", Toast.LENGTH_LONG).show();
                }

            }
        });

        //조회 버튼
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //읽기모드로 DB오픈
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("select * from groupTBL",null);
                //컬럼제목
                String strNames = "그룹이름" + "\r\n" + "---------------------------------------" + "\r\n";
                String strNums = "인원수" + "\r\n" + "---------------------------------------" + "\r\n";
                //반복처리
                while (cursor.moveToNext()){
                    strNames += cursor.getString(0)+"\r\n";
                    strNums += cursor.getString(1)+"\r\n";
                }
                editNameResult.setText(strNames);
                editNumResult.setText(strNums);

                cursor.close();
                sqlDB.close();
            }
        });

        //초기화 버튼
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //쓰기모드로 DB오픈
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });



    }
}
