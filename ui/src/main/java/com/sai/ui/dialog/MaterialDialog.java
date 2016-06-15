package com.sai.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sai.ui.R;
import com.sai.ui.util.ViewUtil;


/**
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 *
 * @author: hj
 * @version: 1.0
 * @date:
 */
public class MaterialDialog {
    protected TextView mTvTitle;
    protected Button mBtnCancel;
    protected Button mBtnConfirm;
    protected ViewStub mContentView;
    protected Dialog mDialog;
    protected Context mContext;

    public MaterialDialog(Context context) {
        mContext = context;
        create();
    }


    private void create() {
        mDialog = ViewUtil.createDialog(mContext);
        View view = LinearLayout.inflate(mContext, R.layout.dialog_material, null);
        mTvTitle = (TextView)view.findViewById(R.id.tv_title);
        mContentView = (ViewStub)view.findViewById(R.id.vs_content);
        int contentId = getContentView();
        if(contentId != 0){
            mContentView.setLayoutResource(contentId);
            contentView(mContentView.inflate());
        }
        mBtnCancel = (Button)view.findViewById(R.id.btn_cancel);
        mBtnConfirm = (Button)view.findViewById(R.id.btn_confirm);
        mDialog.setContentView(view);
    }

    public Dialog getDialog(){
        return mDialog;
    }

    public MaterialDialog setTitle(String title) {
        if(TextUtils.isEmpty(title)){
            ViewUtil.hideView(mTvTitle);
        }else{
            ViewUtil.showView(mTvTitle);
        }
        mTvTitle.setText(title);
        return this;
    }

    protected int getContentView(){
       return 0;
    }

    protected void contentView(View view){

    }


    public MaterialDialog setCancelText(String cancel) {
        ViewUtil.showView(mBtnCancel);

        if (!TextUtils.isEmpty(cancel)) {
            mBtnCancel.setText(cancel);
        }
        return this;
    }

    public MaterialDialog setCancelListener(final View.OnClickListener listener) {
        ViewUtil.showView(mBtnCancel);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });

        return this;
    }
    public MaterialDialog setConfirmText(String confirm) {
        ViewUtil.showView(mBtnConfirm);

        if (!TextUtils.isEmpty(confirm)) {
            mBtnConfirm.setText(confirm);
        }

        return this;
    }
    public MaterialDialog setConfirmListener(final View.OnClickListener listener) {
        ViewUtil.showView(mBtnConfirm);

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide();
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });

        return this;
    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    protected void hide() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
