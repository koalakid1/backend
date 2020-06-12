package com.naver.batterysample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView)findViewById(R.id.imageView);
        editText=(EditText)findViewById(R.id.editText);



    }

    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                editText.setText("현재 충전량 : " + remain + "%\n");

                if (remain >= 90) imageView.setImageResource(R.drawable.battery_100);
                else if (remain >= 70) imageView.setImageResource(R.drawable.battery_80);
                else if (remain >= 50) imageView.setImageResource(R.drawable.battery_60);
                else if (remain >= 10) imageView.setImageResource(R.drawable.battery_20);
                else imageView.setImageResource(R.drawable.battery_0);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug){
                    case 0:
                        editText.append("전원 연결 : 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        editText.append("전원 연결 : 어댑터 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        editText.append("전원 연결 : USB 연결됨");
                        break;
                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, filter);
    }
}
