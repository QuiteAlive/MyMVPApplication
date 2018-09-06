package com.mengb.mymvpapplication;

import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(SplashActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }
}