package com.binbin.activity.bindwechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.MainActivity;
import com.binbin.activity.R;

public class BindWechatPayThirdStepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_wechat_pay_third_step);
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(BindWechatPayThirdStepActivity.this,MainActivity.class);
        startActivity(intent);
    }

}
