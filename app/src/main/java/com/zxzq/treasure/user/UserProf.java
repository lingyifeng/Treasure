package com.zxzq.treasure.user;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class UserProf {
    private static UserProf mUserProf = null;
    private Context mContext;
    private static final String PREFS_NAME = "user_info";
    private static final String KEY_TOKENID = "key_tokenid";
    private static final String KEY_PHOTO = "key_photo";

    private final SharedPreferences mPreferences;

    public int getMtokenid() {
        return mPreferences.getInt(KEY_TOKENID,-1);
    }

    public void setMtokenid(int mtokenid) {
        mPreferences.edit().putInt(KEY_TOKENID,mtokenid).commit();
    }

    public String getHeadpic() {
        return mPreferences.getString(KEY_PHOTO,null);
    }

    public void setHeadpic(String headpic) {
        mPreferences.edit().putString(KEY_PHOTO,headpic);
    }

    private UserProf(Context context) {
        mPreferences = context.getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);

    }

    public static void init(Context context) {
        mUserProf = new UserProf(context);
    }

    public static UserProf getInstances() {
        return mUserProf;
    }


}
