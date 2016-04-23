package com.binbin.activity.changephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.R;

public class InfoPhoneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_phone);
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(InfoPhoneActivity.this,PhoneNumChangeFirstStepActivity.class);
        startActivity(intent);
    }


}
