package com.binbin.activity.bindwechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.binbin.activity.LoginActivity;
import com.binbin.activity.R;

public class BindWechatPaySecondStepActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_wechat_pay_second_step);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("绑定微信支付");
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(BindWechatPaySecondStepActivity.this,BindWechatPayThirdStepActivity.class);
        startActivity(intent);
    }

    /**
     * 返回
     */
    public void back(View view) {
        Intent intent = new Intent(this.getApplicationContext(),
                BindWechatPayFirstStepActivity.class);
        startActivity(intent);
        BindWechatPaySecondStepActivity.this.finish();
    }
}
