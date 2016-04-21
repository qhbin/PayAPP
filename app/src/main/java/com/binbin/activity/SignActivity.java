package com.binbin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.binbin.activity.R;
import com.binbin.client.Connector;
import com.binbin.commom.util.Encode;
import com.binbin.proto.IprojHeader.Header;
import com.binbin.proto.Line;
import com.binbin.proto.Line.RegistUserReq;
import  com.binbin.proto.Line.RegistUserRsp;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class SignActivity extends Activity {
    private EditText et_username = null;
    private EditText et_password = null;
    private EditText et_mobile_phone = null;
    private Button bt_sign = null;
    private Connector m_conn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
        bt_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = et_username.getText().toString();
                final String password = et_password.getText().toString();
                String mobile = et_mobile_phone.getText().toString();
                new Thread(){
                    public void run()
                    {
                        m_conn = Connector.getInstance();
                        Header header = Header.newBuilder()
                                .setMsgFullName("line.LoginReq")
                                .build();
                        RegistUserReq req = RegistUserReq.newBuilder()
                                .setUserName(username)
                                .setEncryptedPsw(Encode.getEncode("MD5",password))
                                .build();
                        m_conn.sendMessage(header, req);

                        try {
                            //接收服务器回包
                           ByteString bs_recv = m_conn.receiveMessage();
                           RegistUserRsp rsp = RegistUserRsp.parseFrom(bs_recv);
                            System.out.println("result of recv msg: " + rsp.getResult());
                        } catch (InvalidProtocolBufferException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    private void initView(){
        et_username = (EditText)findViewById(R.id.sign_username);
        et_mobile_phone = (EditText)findViewById(R.id.sign_mobile_phone);
        et_password = (EditText)findViewById(R.id.sign_password);
        bt_sign =(Button) findViewById(R.id.sign);
    }


}