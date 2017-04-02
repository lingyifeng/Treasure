package com.zxzq.treasure;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.zxzq.treasure.commons.ActivityUtils;
import com.zxzq.treasure.user.login.LoginActivity;
import com.zxzq.treasure.user.register.RegisterActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_Register)
    Button mBtnRegister;
    @BindView(R.id.btn_Login)
    Button mBtnLogin;
    private ActivityUtils mActivityUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivityUtils = new ActivityUtils(this);


    }

    @OnClick({R.id.btn_Register, R.id.btn_Login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }



}
