package com.zxzq.treasure.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class UserBean {

    public UserBean(String userName, String password) {
        Password = password;
        UserName = userName;
    }

    /**
     * UserName : qjd
     * Password : 654321
     */
    @SerializedName("UserName")
    private String UserName;
    @SerializedName("Password")
    private String Password;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
