package com.binbin.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.binbin.activity.resetpassword.RsetPassword;
import com.binbin.client.Connector;
import com.binbin.commom.util.Encode;
import com.binbin.commom.util.ScreenUtil;
import com.binbin.commom.util.SystemBarTintManager;
import com.binbin.proto.IprojHeader;
import com.binbin.proto.Line.LoginReq;
import com.binbin.proto.Line.LoginRsp;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

public class LoginActivity extends Activity implements OnClickListener {

    private EditText et_username = null;
    private EditText et_password = null;
    private TextView et_forget_password = null;
    private Button bt_login = null;
    private TextView et_register = null;
    private String m_sUserName = null;
    private String m_sPassword = null;
    private Connector m_conn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWindow();
        initView();
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.title_without_back);
    }


    public void initView() {
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        et_register = (TextView) findViewById(R.id.user_sign);
        bt_login = (Button) findViewById(R.id.login);
        et_forget_password = (TextView) findViewById(R.id.forget_password);
        et_forget_password.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        et_register.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
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

    public void submit() {
        m_sUserName = et_username.getText().toString();
        m_sPassword = et_password.getText().toString();
        new Thread() {
            public void run() {
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
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    protected void goResetPassword() {
        Intent intent = new Intent();
        intent.setClass(this, RsetPassword.class);
        startActivity(intent);
    }

    protected void goRegister() {
        Intent intent = new Intent();
        intent.setClass(this, SignActivity.class);
        startActivity(intent);
    }

    private SystemBarTintManager tintManager;

    @TargetApi(19)
    private void initWindow() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {


           // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
          /* tintManager = new SystemBarTintManager(this);

            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setTintColor(Color.parseColor("#047BF0"));
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = ScreenUtil.getStatusBarHeight(this.getBaseContext());
            view.setMinimumHeight(statusBarHeight + view.getHeight());
            view.setPadding(0, statusBarHeight, 0, 0);*/
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.statusbar_bg);
        }
    }

    /*public static int getStatusHeight(Context context) {


        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }*/
}
