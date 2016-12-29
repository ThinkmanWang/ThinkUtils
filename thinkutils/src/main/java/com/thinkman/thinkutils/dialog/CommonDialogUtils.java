package com.thinkman.thinkutils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.EditText;

import com.thinkman.thinkutils.commonutils.DisplayUtil;

/**
 * Created by wangx on 2016/6/4.
 */
public class CommonDialogUtils {

    public static void showInputDialog(Context context
            , String szTitle
            , String szMsg
            , String szHint
            , final OnInputDialogResult result) {
        showInputDialog(context, szTitle, szMsg, szHint, 1, 1, "", true, result);
    }

    public static void showInputDialog(Context context
            , String szTitle
            , String szMsg
            , String szHint
            , int nMinLine
            , int nMaxLine
            , String szText
            , boolean bCancelable
            , final OnInputDialogResult result) {
        final EditText etText = new EditText(context);
        etText.setMinLines(nMinLine);
        etText.setMaxLines(nMaxLine);
        etText.setText(szText);
        etText.setGravity(Gravity.LEFT | Gravity.TOP);
        etText.setPadding(DisplayUtil.dip2px(context, 16)
            , DisplayUtil.dip2px(context, 16)
            ,DisplayUtil.dip2px(context, 16)
            ,DisplayUtil.dip2px(context, 16));
        etText.setHint(szHint);

        new AlertDialog.Builder(context)
                .setTitle(szTitle)
                .setMessage(szMsg)
                .setView(etText)
                .setCancelable(bCancelable)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (null != result) {
                            result.onOk(etText.getText().toString());
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }


    public interface OnInputDialogResult {
        public void onOk(String szText);
    }
}
