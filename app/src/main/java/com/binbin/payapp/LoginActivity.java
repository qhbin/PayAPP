package com.binbin.payapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

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
    private Button bt_login = null;
    private Button bt_regist = null;
    private String m_sUserName = null;
    private String m_sPassword = null;
    private Connector m_conn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }*/
    public void initView(){
        et_username = (EditText)findViewById(R.id.username);
        et_password = (EditText)findViewById(R.id.password);
        bt_login = (Button)findViewById(R.id.login);
        bt_login.setOnClickListener((android.view.View.OnClickListener) this);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                submit();
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
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
