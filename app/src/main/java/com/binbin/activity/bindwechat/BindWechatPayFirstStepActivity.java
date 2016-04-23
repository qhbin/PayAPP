package com.binbin.activity.bindwechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.R;

public class BindWechatPayFirstStepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_wechat_pay_first_step);
    }


    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(BindWechatPayFirstStepActivity.this,BindWechatPaySecondStepActivity.class);
        startActivity(intent);
    }
}
