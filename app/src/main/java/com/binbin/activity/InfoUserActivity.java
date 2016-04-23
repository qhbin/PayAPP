package com.binbin.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InfoUserActivity extends Activity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
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
