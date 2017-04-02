package com.zxzq.treasure.user.register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zxzq.treasure.R;
import com.zxzq.treasure.commons.ActivityUtils;
import com.zxzq.treasure.commons.RegexUtils;
import com.zxzq.treasure.dalog.AlertDialogFragment;
import com.zxzq.treasure.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView,TextWatcher{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.et_Confirm)
    EditText mEtConfirm;
    @BindView(R.id.btn_Register)
    Button mBtnRegister;
    private ProgressDialog mDialog;
    private ActivityUtils mActivityUtils;
    private String userName;
    private String passWord;
    private String mConFirm;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRegisterPresenter = new RegisterPresenter();
        mRegisterPresenter.setRegisterListener(this);
        mActivityUtils = new ActivityUtils(this);
        mEtConfirm.addTextChangedListener(this);
        mEtPassword.addTextChangedListener(this);
        mEtUsername.addTextChangedListener(this);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("注册");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showProgress() {
        mDialog = ProgressDialog.show(this, "注册", "正在登录中，请稍后~");
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
        if (code == 1) {
            mActivityUtils.startActivity(HomeActivity.class);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.toolbar, R.id.btn_Register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.btn_Register:
                System.out.println("userName"+userName+"  passWord:"+passWord);
                if(!passWord.equals(mConFirm)){
                    mActivityUtils.showToast("校验密码错误");
                    break;
                }else {
                    // 账号和密码是否符合规范
                    if (RegexUtils.verifyUsername(userName)!=RegexUtils.VERIFY_SUCCESS){

                        // 显示一个对话框提示
                        AlertDialogFragment.getInstance(getString(R.string.username_error),getString(R.string.username_rules))
                                .show(getSupportFragmentManager(),"usernameError");
                        break;
                    }

                    if (RegexUtils.verifyPassword(passWord)!=RegexUtils.VERIFY_SUCCESS){
                        AlertDialogFragment.getInstance(getString(R.string.password_error),getString(R.string.password_rules))
                                .show(getSupportFragmentManager(),"passwordError");
                        break;
                    }
                    mRegisterPresenter.register(userName,passWord);
                }

                break;
        }
    }

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
        mConFirm = mEtConfirm.getText().toString();
        if(!(TextUtils.isEmpty(userName)||TextUtils.isEmpty(passWord)||TextUtils.isEmpty(mConFirm))){
            mBtnRegister.setEnabled(true);
        }

    }
}
