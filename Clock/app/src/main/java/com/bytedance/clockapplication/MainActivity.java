package com.bytedance.clockapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bytedance.clockapplication.widget.Clock;

public class MainActivity extends AppCompatActivity {

    private View mRootView;
    private Clock mClockView;
    private final int UpdateClock =1;

    @SuppressLint("HandlerLeak")
    private Handler clockHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UpdateClock) {
                mClockView.invalidate();
                clockHandler.sendEmptyMessageDelayed(UpdateClock, 1000);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.root);
        mClockView = findViewById(R.id.clock);

        clockHandler.sendEmptyMessage(UpdateClock);

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClockView.setShowAnalog(!mClockView.isShowAnalog());
            }
        });
    }
}
