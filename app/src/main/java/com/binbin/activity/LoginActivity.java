package com.binbin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.binbin.activity.resetpassword.RsetPassword;
import com.binbin.client.Connector;
import com.binbin.commom.util.Encode;
import com.binbin.proto.IprojHeader;
import com.binbin.proto.Line.LoginReq;
import com.binbin.proto.Line.LoginRsp;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class LoginActivity extends Activity implements OnClickListener  {

    private EditText et_username = null;
    private EditText et_password = null;
    private TextView et_forget_password=null;
    private Button bt_login = null;
    private TextView et_register = null;
    private String m_sUserName = null;
    private String m_sPassword = null;
    private Connector m_conn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    public void initView(){
        et_username = (EditText)findViewById(R.id.username);
        et_password = (EditText)findViewById(R.id.password);
        et_register = (TextView)findViewById(R.id.user_sign);
        bt_login = (Button)findViewById(R.id.login);
        et_forget_password = (TextView)findViewById(R.id.forget_password);
        et_forget_password.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        et_register.setOnClickListener(this);


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                submit();
                break;
            case R.id.user_sign:
                goRegister();
                break;
            case R.id.forget_password:
                goResetPassword();
                break;
            default:
                break;
        }
    }

    public void submit(){
           m_sUserName=et_username.getText().toString();
           m_sPassword = et_password.getText().toString();
        new Thread(){
                public void run()
                {
                m_conn = Connector.getInstance();
                IprojHeader.Header header = IprojHeader.Header.newBuilder()
                        .setMsgFullName("line.LoginReq")
                        .build();
                LoginReq req = LoginReq.newBuilder()
                        .setUserName(m_sUserName)
                        .setEncryptedPsw(Encode.getEncode("MD5", m_sPassword))
                        .build();
                m_conn.sendMessage(header, req);

              try {
                    //接收服务器回包
                    ByteString bs_receive = m_conn.receiveMessage();
                    LoginRsp rsp = LoginRsp.parseFrom(bs_receive);
                    System.out.println("result of receive msg: " + rsp.getResult());

                  Intent intent = new Intent();
                  intent.setClass(LoginActivity.this,MainActivity.class);
                  startActivity(intent);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    protected void goResetPassword(){
        Intent intent = new Intent();
        intent.setClass(this,RsetPassword.class);
        startActivity(intent);
    }

    protected void  goRegister(){
        Intent intent = new Intent();
        intent.setClass(this,SignActivity.class);
        startActivity(intent);
    }

}
