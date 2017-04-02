package com.zxzq.treasure.httpconnect;

import com.zxzq.treasure.bean.LoginBean;
import com.zxzq.treasure.bean.RegistBean;
import com.zxzq.treasure.bean.UserBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public interface UserAPI {
    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginBean> login(@Body UserBean userBean);

    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegistBean> regist(@Body UserBean userBean);
}
