package com.binbin.activity.balance;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.binbin.activity.BaseActivity;
import com.binbin.activity.PayDialog;
import com.binbin.activity.R;

public class ChargeMoney extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_money);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("充值现金");
    }

    public void nextStep(View view){
        PayDialog dialog = new PayDialog(ChargeMoney.this,new PayDialog.OnCustomDialogListener() {});
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }



}
