package com.binbin.activity.changephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.binbin.activity.R;

public class PhoneNumChangeFirstStepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_num_change_first_step);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("更换绑定手机");
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(PhoneNumChangeFirstStepActivity.this,PhoneNumChangeSecondStepActivity.class);
        startActivity(intent);
    }
}
