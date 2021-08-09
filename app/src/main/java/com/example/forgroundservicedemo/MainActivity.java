package com.example.forgroundservicedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
        }
    };

    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("key");

        Intent serviceIntent = new Intent(this, BroadcastReceiver.class);
        startService(serviceIntent);
    }

    public void startService(View v) {
        Intent serviceIntent = new Intent(this, AutoStartService.class);
        serviceIntent.putExtra("inputExtra", "passing any text");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, AutoStartService.class);
        stopService(serviceIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
}