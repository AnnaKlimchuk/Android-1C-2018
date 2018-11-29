package com.example.anna.myapplication.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.anna.myapplication.R;

public class TouchActivity extends AppCompatActivity {

    private TextView textView;
    private String sDown, sMove, sUp;
    private float downX, downY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch);
        textView = findViewById(R.id.text_view_for_motion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                sDown = "Down: " + x + "," + y;
                sMove = ""; sUp = "";

                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE: // движение
                sMove = "Move: " + x + "," + y;
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + "," + y;

                float upX = event.getX();
                float upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;
                if ((Math.abs(deltaY) > Math.abs(deltaX)) && (deltaY < 0)) {
                    Intent startActivity = new Intent(TouchActivity.this, MainActivity.class);
                    startActivity(startActivity);
                }
                break;
        }
        textView.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }
}