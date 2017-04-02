package com.zxzq.treasure.user.login;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void showMessage(String msg);
    void success(int code);
}
