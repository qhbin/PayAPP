package com.binbin.activity.changephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.MainActivity;
import com.binbin.activity.R;

public class PhoneNumChangeSecondStepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_num_change_second_step);
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(PhoneNumChangeSecondStepActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
