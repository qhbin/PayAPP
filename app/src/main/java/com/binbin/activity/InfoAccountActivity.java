package com.binbin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binbin.activity.bindwechat.WeChatInfoActivity;
import com.binbin.activity.changephone.InfoPhoneActivity;

public class InfoAccountActivity extends Activity {
    private LinearLayout goUserName;
    private LinearLayout goWeChatInfo;
    private LinearLayout goMobileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);
        init();
    }

    /**
     * 返回
     */
    public void back(View view) {
        Intent intent = new Intent(this.getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        InfoAccountActivity.this.finish();
    }

    protected void init(){
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("账户信息");
        goUserName = (LinearLayout)findViewById(R.id.go_username);
        goMobileInfo = (LinearLayout)findViewById(R.id.go_mobile_info);
        goWeChatInfo = (LinearLayout)findViewById(R.id.go_wechat_info);
        goUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(InfoAccountActivity.this,InfoUserActivity.class);
                startActivity(intent);
            }
        });

        goMobileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(InfoAccountActivity.this,InfoPhoneActivity.class);
                startActivity(intent);
            }
        });

        goWeChatInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(InfoAccountActivity.this,WeChatInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
