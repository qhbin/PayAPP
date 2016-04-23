package com.binbin.activity.bindwechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.R;

public class WeChatInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_info);
    }

    public void changeWeChat(View view){
        Intent intent = new Intent();
        intent.setClass(WeChatInfoActivity.this,BindWechatPayFirstStepActivity.class);
        startActivity(intent);
    }
}
