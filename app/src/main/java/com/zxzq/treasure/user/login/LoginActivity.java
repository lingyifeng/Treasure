package com.zxzq.treasure.user.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zxzq.treasure.R;
import com.zxzq.treasure.commons.ActivityUtils;
import com.zxzq.treasure.commons.RegexUtils;
import com.zxzq.treasure.dalog.AlertDialogFragment;
import com.zxzq.treasure.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.tv_forgetPassword)
    TextView mTvForgetPassword;
    @BindView(R.id.btn_Login)
    Button mBtnLogin;
    String userName;
    String passWord;
    TextWatcher mTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            userName=mEtUsername.getText().toString();
            passWord=mEtPassword.getText().toString();
            if(!(TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord))){
                mBtnLogin.setEnabled(true);
            }
        }
    };
    private LoginPresenter mLoginPresenter;
    private ProgressDialog mDialog;
    private ActivityUtils mActivityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mActivityUtils = new ActivityUtils(this);
        ButterKnife.bind(this);
        mEtPassword.addTextChangedListener(mTextWatcher);
        mEtUsername.addTextChangedListener(mTextWatcher);
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.setLoginView(this);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("登录");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.tv_forgetPassword, R.id.btn_Login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forgetPassword:

                break;
            case R.id.btn_Login:
                if(RegexUtils.verifyUsername(userName)!=RegexUtils.VERIFY_SUCCESS){
                    AlertDialogFragment.getInstance(getString(R.string.username_error),getString(R.string.username_rules))
                            .show(getSupportFragmentManager(),"login");
                    break;
                }

                if(RegexUtils.verifyUsername(passWord)!=RegexUtils.VERIFY_SUCCESS){
                    AlertDialogFragment.getInstance(getString(R.string.password_error),getString(R.string.password_rules))
                            .show(getSupportFragmentManager(),"login");
                    break;
                }
                mLoginPresenter.login(userName,passWord);
                break;
        }
    }

    @Override
    public void showProgress() {
        mDialog = ProgressDialog.show(this, "登录", "正在登录中，请稍后~");
    }

    @Override
    public void hideProgress() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        mActivityUtils.showToast(msg);
    }

    @Override
    public void success(int code) {
        if(code==1){
            mActivityUtils.startActivity(HomeActivity.class);
        }
    }
}
