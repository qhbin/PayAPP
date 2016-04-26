package com.binbin.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.widget.EditText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by dell on 2016/1/4.
 */
    public class PayDialog extends Dialog {

    //定义回调事件，用于dialog的点击事件
    public interface OnCustomDialogListener{
       // public void back(String name);
    }

     private OnCustomDialogListener customDialogListener;
     private EditText password;
     private TextView pay_title;

    public PayDialog(Context context,OnCustomDialogListener customDialogListener) {
        super(context);
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        password = (EditText)findViewById(R.id.pay_password);
        pay_title= (TextView) findViewById(R.id.pay_title);
        Button payBtn = (Button) findViewById(R.id.payButton);
        payBtn.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if(password.getText().toString().length()==6){
          // customDialogListener.back(String.valueOf(password.getText()));
            pay_title.setText("支付成功！");
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        PayDialog.this.dismiss();
                    }
                }, 500);
               }
        }
    };
}
