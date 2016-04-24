package com.binbin.activity.changephone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.binbin.activity.LoginActivity;
import com.binbin.activity.MainActivity;
import com.binbin.activity.R;

public class PhoneNumChangeSecondStepActivity extends Activity {
    private EditText smsCode; // 短信验证码
    private Button getSMSCodeBtn; // 获取短信验证码

    private TimeCount timeCount;  // 计时器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_num_change_second_step);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.union_title); // 设置标题样式
        ((TextView) findViewById(R.id.title)).setText("更换绑定手机");
        smsCode = (EditText) findViewById(R.id.smsCode);
        getSMSCodeBtn = (Button) findViewById(R.id.getSMSCodeBtn);
        timeCount = new TimeCount(60000, 1000);
    }

    public void nextStep(View view){
        Intent intent = new Intent();
        intent.setClass(PhoneNumChangeSecondStepActivity.this,MainActivity.class);
        startActivity(intent);
    }

    /**
     * 返回
     */
    public void back(View view) {
        Intent intent = new Intent(this.getApplicationContext(),
                PhoneNumChangeFirstStepActivity.class);
        startActivity(intent);
        PhoneNumChangeSecondStepActivity.this.finish();
    }

    /**
     * 获取短信验证码
     */
    public void getSMSCode(View view) {
        // 调用后台服务发送短信
        // ... //

        timeCount.start();
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {  // 计时完毕
            getSMSCodeBtn.setText("获取验证码");
            getSMSCodeBtn.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {  // 计时过程
            getSMSCodeBtn.setClickable(false);
            getSMSCodeBtn.setText(millisUntilFinished / 1000 + "秒");
        }
    }

    private boolean verifySMSCode(String smsCode) {
        // demo验证码固定为123456
        if(!"123456".equals(smsCode)) {
            Toast.makeText(getApplicationContext(), "验证码错误!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
