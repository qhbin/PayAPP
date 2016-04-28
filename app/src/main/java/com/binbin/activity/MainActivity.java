package com.binbin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binbin.activity.R;
import com.binbin.activity.balance.AccountBalance;
import com.binbin.activity.transfer_accounts.TransferAccount;

import java.util.Iterator;

public class MainActivity extends BaseActivity {
    private LinearLayout user_info;
    private Button goBalance;
    private Button goTransfer;
    private TextView currentUserName;
    private TextView currentUserMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init(){
        user_info=(LinearLayout)findViewById(R.id.user_info);
        goBalance = (Button)findViewById(R.id.go_balance);
        goTransfer = (Button)findViewById(R.id.go_transfer);
        currentUserName = (TextView)findViewById(R.id.main_user_account);
        currentUserMobile = (TextView)findViewById(R.id.main_mobile_phone);

        user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,InfoAccountActivity.class);
                startActivity(intent);
            }
        });

        goBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AccountBalance.class);
                startActivity(intent);
            }
        });

        goTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TransferAccount.class);
                startActivity(intent);
            }
        });
    }

}
