package com.naver.bussample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    private String downLoadUrl(String myurl) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(myurl); // 서버주소로 URL 객체 생성
            conn = (HttpURLConnection) url.openConnection();
            conn.connect(); // 서버연결

            // 파일을 다운로드. 읽어옴.
            BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
            String line = null;
            String page = "";
            while((line = bufreader.readLine()) != null) {
                page += line;
            }

            return page;
        } finally {
            conn.disconnect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);

        String serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey = "qRJ5CTEZAk2DYXTwql9Oqqc%2FDuY9%2F3v2pIzkjKLFz%2BIkvs5gfpYWqIJy7u%2BGwn5ebw3HxNw0tmzobu1m96UDKg%3D%3D";
        String strSrch = "3";
        String strUrl = serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch;

        new DownLoadWebPageTask().execute(strUrl);
    }

    private class DownLoadWebPageTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downLoadUrl((String)urls[0]);
            }catch (IOException e) {
                return "다운로드 실패";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            tv.setText(result);
        }
    }
}
