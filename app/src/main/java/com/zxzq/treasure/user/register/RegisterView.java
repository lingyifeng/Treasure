package com.zxzq.treasure.user.register;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void showMessage(String msg);
    void success(int code);

}
