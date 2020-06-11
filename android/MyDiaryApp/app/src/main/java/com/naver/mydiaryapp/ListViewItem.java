package com.naver.mydiaryapp;

import java.sql.Date;

public class ListViewItem {
    private String date;
    private String content;

    public void setDate(String date){this.date = date;}
    public void setContent(String content){this.content = content;}

    public String getDate() {
        return this.date;
    }

    public String getContent() {
        return this.content;
    }
}
