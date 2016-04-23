package com.binbin.activity.balance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.binbin.activity.R;

public class AccountBalance extends Activity {
    private LinearLayout goChargeMoney;
    private  LinearLayout goWithdraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_balance);
        init();
    }

    protected void init(){
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



}
