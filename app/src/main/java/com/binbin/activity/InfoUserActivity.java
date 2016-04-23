package com.binbin.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class InfoUserActivity extends Activity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("用户信息");
        actionBar=getActionBar();
        actionBar.show();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public void submitName(View view){
        Intent intent = new Intent();
        intent.setClass(InfoUserActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
