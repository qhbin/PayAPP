package com.binbin.activity.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binbin.activity.R;

public class AccountBalance extends Activity {
    private LinearLayout goChargeMoney;
    private  LinearLayout goWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_balance);
        init();
    }

    protected void init(){
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("账户余额");
        goChargeMoney = (LinearLayout)findViewById(R.id.go_charge_money);
        goWithdraw = (LinearLayout)findViewById(R.id.go_withdraw);

        goChargeMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(AccountBalance.this,ChargeMoney.class);
                startActivity(intent);
            }
        });

        goWithdraw.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View view){
                Intent intent = new Intent();
                intent.setClass(AccountBalance.this,Withdraw.class);
                startActivity(intent);
            }
        });
    }

    public void back(View view){
        AccountBalance.this.finish();
    }

}
