package com.binbin.activity.transfer_accounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.binbin.activity.R;

public class TransferAccount extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_account);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("转账");
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(TransferAccount.this,TransferAmount.class);
        startActivity(intent);
    }
    public void back(View view) {
        TransferAccount.this.finish();
    }

}
