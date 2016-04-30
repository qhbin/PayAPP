package com.binbin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binbin.client.Connector;
import com.binbin.commom.util.Encode;
import com.binbin.proto.IprojHeader;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.binbin.proto.IprojHeader.Header;
import com.binbin.proto.Line;
import com.binbin.proto.Line.RegistUserReq;
import  com.binbin.proto.Line.RegistUserRsp;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;


public class VerifyCode extends BaseActivity implements View.OnClickListener {
 private Button requestCodeBtn;
 private Button nextStep;
 private EditText ed_code;
 private  int i = 60;
 private  String phoneNums=null;
 private  String username = null;
 private  String password = null;
 private Connector m_conn = null;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        init();
    }

    private void init(){
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("验证码");
        Intent intent =getIntent();
        requestCodeBtn=(Button)findViewById(R.id.request_code);
        nextStep = (Button)findViewById(R.id.verify_code);
        requestCodeBtn.setOnClickListener(this);
        nextStep.setOnClickListener(this);
        ed_code = (EditText)findViewById(R.id.verify_code_content);
        phoneNums = intent.getStringExtra("phoneNums");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        SMSSDK.initSDK(this, "11d8b4fdb73f3", "be78f3a01d89935617ff449a1bcaa401");
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        requestCodeBtn.performClick();
        // 2. 通过sdk发送短信验证
      //  SMSSDK.getVerificationCode("86", phoneNums);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.request_code:
                SMSSDK.getVerificationCode("86", phoneNums);

                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                requestCodeBtn.setClickable(false);
                requestCodeBtn.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;

            case R.id.verify_code:
                String code = ed_code.getText().toString();
                SMSSDK.submitVerificationCode("86", phoneNums,code);
                //createProgressBar();
                break;
        }
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                requestCodeBtn.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                requestCodeBtn.setText("获取验证码");
                requestCodeBtn.setClickable(true);
                i = 60;
            }else if(msg.what == -7){
                Intent intent = new Intent(VerifyCode.this,
                        MainActivity.class);
                startActivity(intent);
            }
            else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();

                        new Thread(){
                            public void run()
                            {
                                m_conn = Connector.getInstance();
                                Header header = Header.newBuilder()
                                        .setMsgFullName("line.LoginReq")
                                        .build();
                                RegistUserReq req = RegistUserReq.newBuilder()
                                        .setUserName(username)
                                        .setEncryptedPsw(Encode.getEncode("MD5", password))
                                        .build();
                                m_conn.sendMessage(header, req);

                                try {
                                    //接收服务器回包
                                    ByteString bs_recv = m_conn.receiveMessage();
                                    RegistUserRsp rsp = RegistUserRsp.parseFrom(bs_recv);
                                    System.out.println("result of recv msg: " + rsp.getResult());

                                 //   if( rsp.getResult()==1){
                                        handler.sendEmptyMessage(-7);
                                   // }
                                } catch (InvalidProtocolBufferException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };
}
