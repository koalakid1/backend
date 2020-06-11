package com.naver.downloadsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    // button to show progress dialog
    Button btnShowProgress;
    ImageView my_image;

    // Progress Dialog
    private ProgressDialog pDialog;

    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;

    // File url to download
    private static String file_url = "https://api.androidhive.info/progressdialog/hive.jpg";

    //dialog show
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SD CARD쓰기 권한을 요청하는 코드가 필요. 마시멜로우(6.0)부터 변경됨.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);


        // show progress bar button
        btnShowProgress = (Button) findViewById(R.id.btnProgressBar);
        // Image view to show image after downloading
        my_image = (ImageView) findViewById(R.id.my_image);

        btnShowProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFileFromURL().execute(file_url);
            }
        });


    }

    // AsyncTask<1String, 2String, 3String>
        //1은 doInBackground
        //2는 onProgressUpdate
        //3은 onPostexecute
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //프로그레스 다이얼로그를 띄운다.
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            //서버에 요청해서 다운로드
            int count;
            try {
                URL url = new URL(f_url[0]); // 서버주소로 URL 객체 생성
                URLConnection conection = url.openConnection();
                conection.connect(); // 서버연결
                // 파일의 크기 구하기
                int lenghtOfFile = conection.getContentLength();

                // 파일을 다운로드. 읽어옴.
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // 파일을 SD카드에 쓰기 권한필요. 사용자에게 요청.
                OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");

                byte data[] = new byte[1024];

                long total = 0; // 현재까지 다운로드한 양.

                while ((count = input.read(data)) != -1) { // 다운로드한 파일이 있으면
                    total += count; // 현재까지 다운로드한 양을 증가
                    // 백분율을 구해서 프로그레스바에 표시
                    // 온프로그레스업데이트를 호출함.
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        //프로그레스바 업데이트
        protected void onProgressUpdate(String... progress) {
            // 프로그레스바 진행률을 변경한다.
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {


            // 프로그레스 다이얼로그를 종료
            dismissDialog(progress_bar_type);

            // ImageView에 다운로드한 파일을 띄운다.
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/downloadedfile.jpg";
            my_image.setImageDrawable(Drawable.createFromPath(imagePath));
        }
    }


}
