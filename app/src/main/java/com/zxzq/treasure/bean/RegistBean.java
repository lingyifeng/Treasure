package com.zxzq.treasure.bean;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RegistBean {

    /**
     * errcode : 1
     * errmsg : 登录成功！
     * headpic : add.jpg
     * tokenid : 171
     */

    private int errcode;
    private String errmsg;
    private String headpic;
    private int tokenid;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public int getTokenid() {
        return tokenid;
    }

    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }
}
