package com.binbin.activity.resetpassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.binbin.activity.R;

public class RsetPassword extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rset_password);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("重置密码");
    }
    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(RsetPassword.this,CheckRsetPassword.class);
        startActivity(intent);
    }
    public void back(View view){
        RsetPassword.this.finish();
    }
}
