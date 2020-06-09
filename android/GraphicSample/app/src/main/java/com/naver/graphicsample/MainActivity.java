package com.naver.graphicsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            //붓역할 객체
            Paint paint = new Paint();
            paint.setAntiAlias(true); // 외곽선을 부드럽게
            paint.setColor(Color.RED);
            canvas.drawLine(10, 100, 300, 100, paint);

            RectF rectF = new RectF(250, 150, 250 + 100, 150 + 100);
            canvas.drawRect(rectF, paint);

            paint.setColor(Color.BLUE);
            canvas.drawCircle(130, 320, 100, paint);

            paint.setTextSize(50);
            canvas.drawText("안드로이드", 10, 200, paint);

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.rabbit);
            canvas.drawBitmap(picture, 310, 350, null);
            picture.recycle();
        }
    }
}
