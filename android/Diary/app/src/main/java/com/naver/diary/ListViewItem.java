package com.naver.diary;

public class ListViewItem {
    private String date;
    private String content;
    private String num;

    public void setDate(String date){this.date = date;}
    public void setContent(String content){this.content = content;}
    public void setNum(String num){this.num = num;}

    public String getDate() {
        return this.date;
    }
    public String getContent() {
        return this.content;
    }
    public String getNum() {
        return this.num;
    }
}
