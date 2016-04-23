package com.binbin.activity.changephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.R;

public class PhoneNumChangeFirstStepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_num_change_first_step);
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(PhoneNumChangeFirstStepActivity.this,PhoneNumChangeSecondStepActivity.class);
        startActivity(intent);
    }
}
