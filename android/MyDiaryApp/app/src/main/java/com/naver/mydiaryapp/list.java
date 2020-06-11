package com.naver.mydiaryapp;

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
import android.widget.Button;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;

public class list extends AppCompatActivity {

    ListView diaryList;
    Button btnUpdate;
    SQLiteDatabase sqlDB;
    MyDiaryDB myDiaryDB;
    public class MyDiaryDB extends SQLiteOpenHelper{

        public MyDiaryDB(Context context){
            super(context, "dairyDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("dd", "dd");
            db.execSQL("create table diary(ddate char(10), dcontent char(1000))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //데이터베이스를 삭제하고 테이블을 다시 생성 (초기화)
            db.execSQL("drop table if exists diary"); // 보통 사용하지 않는 코드
            onCreate(db);
        }
    }

    public void addColumn(SQLiteDatabase sqlDB, String date, String content){
        sqlDB = myDiaryDB.getWritableDatabase();
        sqlDB.execSQL("insert into diary values('"+date+"', '"+content+"')");
        sqlDB.close();
    }

    public void updateColumn(SQLiteDatabase sqlDB, String date, String content){
        sqlDB = myDiaryDB.getWritableDatabase();
        sqlDB.execSQL("insert into diary values('"+date+"', '"+content+"')");
        sqlDB.close();
    }

    public void deleteColumn(SQLiteDatabase sqlDB, String date, String content){
        sqlDB = myDiaryDB.getWritableDatabase();
        sqlDB.execSQL("insert into diary values('"+date+"', '"+content+"')");
        sqlDB.close();
    }

    public void selectColumn(SQLiteDatabase sqlDB, ListView listView){
        ArrayList<ListViewItem> arrayList = new ArrayList<ListViewItem>();
        ListViewAdapter adapter = new ListViewAdapter(arrayList);
        Cursor cursor = sqlDB.rawQuery("select * from diary",null);

        while (cursor.moveToNext()) {
            adapter.addItem(cursor.getString(0), cursor.getString(1));
        }

        listView.setAdapter(adapter);

        cursor.close();
        sqlDB.close();
    }
    public class MyDBHelper extends SQLiteOpenHelper{


        public MyDBHelper(Context context){
            // groupDB라는 db를 생성함.
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //테이블 생성
            Log.d("todtjd","todtjd");
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
        setContentView(R.layout.activity_list);

//        myDiaryDB = new MyDiaryDB(list.this);
        MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());



        diaryList = (ListView)findViewById(R.id.diaryList);
//        sqlDB = myDiaryDB.getReadableDatabase();
//        ArrayList<ListViewItem> arrayList = new ArrayList<ListViewItem>();
//        ListViewAdapter adapter = new ListViewAdapter(arrayList);
//        Cursor cursor = sqlDB.rawQuery("select * from diary",null);
//
//        while (cursor.moveToNext()) {
//            adapter.addItem(cursor.getString(0), cursor.getString(1));
//        }
//
//        diaryList.setAdapter(adapter);
//
//        cursor.close();
//        sqlDB.close();
//        selectColumn(sqlDB, diaryList);
        diaryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), item.class);
                intent.putExtra("date", item.getDate());
                intent.putExtra("context", item.getContent());
                startActivityForResult(intent, 1);
            }
        });

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), update.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            addColumn(sqlDB, data.getStringExtra("data"), data.getStringExtra("content"));
        }
        else{
            if (resultCode == 1){

            } else if (resultCode == 2){

            }
        }
    }
}
