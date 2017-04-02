package com.zxzq.treasure.user.register;

import com.zxzq.treasure.bean.RegistBean;
import com.zxzq.treasure.bean.UserBean;
import com.zxzq.treasure.httpconnect.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RegisterPresenter {
    private RegisterView mRegisterView;
    public void setRegisterListener(RegisterView registerListener){
        mRegisterView=registerListener;
    }
    public void register(String userName,String password){
        mRegisterView.showProgress();
        final Call<RegistBean> regist = HttpManager.getHttpManager().getUserAPI().regist(new UserBean(userName, password));
        regist.enqueue(new Callback<RegistBean>() {
            @Override
            public void onResponse(Call<RegistBean> call, Response<RegistBean> response) {
                if(response.isSuccessful()) {
                    int errcode = response.body().getErrcode();
                    String errmsg = response.body().getErrmsg();
                    mRegisterView.showMessage(errmsg);
                    mRegisterView.success(errcode);
                }
                mRegisterView.hideProgress();
            }

            @Override
            public void onFailure(Call<RegistBean> call, Throwable t) {
                mRegisterView.showMessage("网络连接出错");
                mRegisterView.hideProgress();
            }
        });
    }

}
