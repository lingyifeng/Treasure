package com.zxzq.treasure.user.login;

import android.text.TextUtils;

import com.zxzq.treasure.bean.LoginBean;
import com.zxzq.treasure.bean.RegistBean;
import com.zxzq.treasure.bean.UserBean;
import com.zxzq.treasure.commons.RegexUtils;
import com.zxzq.treasure.httpconnect.HttpManager;
import com.zxzq.treasure.httpconnect.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class LoginPresenter {
    private LoginView mLoginView;
    public void setLoginView(LoginView loginView){
        this.mLoginView=loginView;
    }
    public void login(String userName,String passWord){

        UserAPI userAPI = HttpManager.getHttpManager().getUserAPI();
        Call<LoginBean> login = userAPI.login(new UserBean(userName, passWord));
        mLoginView.showProgress();
        login.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if(response.isSuccessful()){
                    LoginBean body = response.body();
                    String errmsg = body.getErrmsg();
                    int errcode = body.getErrcode();
                    mLoginView.showMessage(errmsg);
                    mLoginView.success(errcode);
                }
                mLoginView.hideProgress();
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                mLoginView.showMessage("网络连接出错");
                mLoginView.hideProgress();
            }
        });
    }

}
