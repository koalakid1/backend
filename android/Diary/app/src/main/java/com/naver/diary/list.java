package com.naver.diary;

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

import java.util.ArrayList;

public class list extends AppCompatActivity {
    ListView diaryList;
    Button btnAdd;
    SQLiteDatabase sqlDB;
    MyDiaryDB myDiaryDB;
    public class MyDiaryDB extends SQLiteOpenHelper {

        public MyDiaryDB(Context context){
            super(context, "dairyDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("dd", "dd");
            db.execSQL("create table diary(d_date char(10), d_content char(1000))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //데이터베이스를 삭제하고 테이블을 다시 생성 (초기화)
            db.execSQL("drop table if exists diary"); // 보통 사용하지 않는 코드
            onCreate(db);
        }
    }
    public void addColumn(SQLiteDatabase sqlDB, String date, String content){
        sqlDB.execSQL("insert into diary values('"+date+"', '"+content+"')");
        sqlDB.close();
    }

    public void updateColumn(SQLiteDatabase sqlDB, String date, String content){
        sqlDB.execSQL("insert into diary values('"+date+"', '"+content+"')");
        sqlDB.close();
    }

    public void deleteColumn(SQLiteDatabase sqlDB, String date, String content){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myDiaryDB = new MyDiaryDB(this);
        diaryList = (ListView)findViewById(R.id.diaryList);
        selectColumn(myDiaryDB.getReadableDatabase(), diaryList);
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

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), add.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0){
            Log.d("데이타", data.getStringExtra("date"));
            addColumn(myDiaryDB.getWritableDatabase(), data.getStringExtra("date"), data.getStringExtra("content"));

        }
        else{
            if (resultCode == 1){

            } else if (resultCode == 2){

            }
        }
        selectColumn(myDiaryDB.getReadableDatabase(),diaryList);
    }
}
