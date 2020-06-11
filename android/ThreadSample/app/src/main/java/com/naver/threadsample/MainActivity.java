package com.naver.threadsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;
    Button btn, btn2;
    SeekBar sb1, sb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);
        sb1 = (SeekBar) findViewById(R.id.seekBar);
        sb2 = (SeekBar) findViewById(R.id.seekBar2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        for(int i = sb1.getProgress(); i<100; i+=2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //뷰를 변경하는 코드는 이곳에.
                                    sb1.setProgress(sb1.getProgress()+2);
                                    tv1.setText("1번 진행률 : "+sb1.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(100); // 0.1초 지연
                        }
                    }
                }.start();

                new Thread(){
                    @Override
                    public void run() {
                        for(int i = sb2.getProgress(); i<100; i++){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //view를 변경하는 코드는 여기에
                                    sb2.setProgress(sb2.getProgress()+1);
                                    tv2.setText("2번 진행률 : "+sb2.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(100); // 0.1초 지연
                        }
                    }
                }.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new myAsyncTask().execute();
            }
        });
    }

    class myAsyncTask extends AsyncTask<String, String, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            //주작업은 여기서 코딩
            Log.d("========", "doInBackground()실행됨:");

            for (int i = 0 ; i < 100; i++){
                //onProgressUpdate 호출
                publishProgress(Integer.toString(2*i), Integer.toString(i));
                SystemClock.sleep(100); // 0.1초 지연
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            sb1.setProgress(Integer.parseInt(progress[0]));
            tv1.setText("1번진행률" + sb1.getProgress() + "%");
            sb2.setProgress(Integer.parseInt(progress[1]));
            tv2.setText("2번진행률" + sb2.getProgress() + "%");
        }
    }
}
