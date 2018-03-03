package com.shop.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.shop.NavigationDrawerMenu;
import com.shop.R;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
 //   private String TAG = "ReachMySchool";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

       // Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,NavigationDrawerMenu.class));
                SplashActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
