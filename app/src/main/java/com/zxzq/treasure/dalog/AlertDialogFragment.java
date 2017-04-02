package com.zxzq.treasure.dalog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.zxzq.treasure.R;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class AlertDialogFragment extends DialogFragment{

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_MESSAGE = "key_message";
public static AlertDialogFragment getInstance(String title,String message){
    AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
    Bundle bundle = new Bundle();
    bundle.putString(KEY_MESSAGE,message);
    bundle.putString(KEY_TITLE,title);
    alertDialogFragment.setArguments(bundle);
    return alertDialogFragment;
}
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String titile = bundle.getString(KEY_TITLE);
        String message = bundle.getString(KEY_MESSAGE);

        return new AlertDialog.Builder(getContext())
                .setTitle(titile)
                .setMessage(message)
                .setNegativeButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                }).create();
    }
}
