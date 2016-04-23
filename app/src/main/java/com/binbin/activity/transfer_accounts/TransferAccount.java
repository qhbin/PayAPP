package com.binbin.activity.transfer_accounts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.binbin.activity.R;

public class TransferAccount extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_account);
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(TransferAccount.this,TransferAmount.class);
        startActivity(intent);
    }


}
