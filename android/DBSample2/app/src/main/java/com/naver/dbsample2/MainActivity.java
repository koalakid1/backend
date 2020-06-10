package com.naver.dbsample2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyDBHelper myDBHelper;
    Button btnInit, btnInsert, btnSelect;
    EditText editName, editNum;
    ListView listView;
    ListViewAdapter adapter;
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
        listView = (ListView)findViewById(R.id.listView);

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
                btnSelect.performClick();
            }
        });

        //조회 버튼
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //읽기모드로 DB오픈
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor = sqlDB.rawQuery("select * from groupTBL",null);

                ArrayList<ListViewItem> arrayList = new ArrayList<ListViewItem>();
                //반복처리
                adapter = new ListViewAdapter(arrayList);
                while (cursor.moveToNext()) {
                    adapter.addItem(cursor.getString(0), cursor.getInt(1));
                }
                listView.setAdapter(adapter);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), second.class);
                intent.putExtra("그룹명", item.getName());
                intent.putExtra("인원수", Integer.toString(item.getNum()));
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sqlDB = myDBHelper.getWritableDatabase();
        if (resultCode == 1){
            try{
                sqlDB.execSQL("update groupTBL set gNumber = '" + data.getStringExtra("인원수") + "'"
                        + " where gNumber = '" + data.getStringExtra("이전인원수") + "'"
                );
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "그룹명 중복", Toast.LENGTH_LONG).show();
            }
        }else if (resultCode == -1){
            sqlDB.execSQL("delete from groupTBL where gName = '"+data.getStringExtra("그룹명")+"'");
        }
        sqlDB.close();
        btnSelect.performClick();
    }
}
