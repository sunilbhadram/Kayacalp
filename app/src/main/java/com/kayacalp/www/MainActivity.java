package com.kayacalp.www;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.kayacalp.www.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(com.kayacalp.www.R.layout.activity_main);
        getSupportActionBar().hide();

        LogLauncher logoLauncher = new LogLauncher();
        logoLauncher.start();

    }

    private class LogLauncher extends Thread{
        public void run(){
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(homeIntent);
        MainActivity.this.finish();

        }

    }
}

