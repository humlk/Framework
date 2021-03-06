package com.sai.ui.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

import com.sai.ui.R;


public class ViewUtil {
    public static void showView(View... views) {
        viewVisibility(View.VISIBLE, views);
    }

    public static void hideView(View... views) {
        viewVisibility(View.GONE, views);
    }

    public static void viewVisibility(int visibility, View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view != null && view.getVisibility() != visibility) {
                view.setVisibility(visibility);
            }
        }
    }


    public static void hideSysKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);
        context = null;
    }

    public static Dialog createDialog(Context context) {
        Dialog mDialog = new Dialog(context, R.style.theme_dialog);
        mDialog.getWindow().setWindowAnimations(R.style.dialog_animation);
        mDialog.setCanceledOnTouchOutside(false);
        context = null;
        return mDialog;
    }

    public static void webviewLoadContent(WebView mWebview, String content) {
        mWebview.loadData(content, "text/html; charset=UTF-8", null);
    }
}
