package com.mengb.mymvpapplication;

import android.app.Activity;
import android.content.Intent;

import com.mengb.mymvpapplication.fragment.MainFragment;

public class SplashActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(SplashActivity.this, MainFragment.class);
        startActivity(intent);
        finish();
    }
}