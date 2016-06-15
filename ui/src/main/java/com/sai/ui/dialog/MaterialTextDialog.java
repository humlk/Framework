package com.sai.ui.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sai.ui.R;


/**
 * 系统名称： </br>
 * 模块名称： </br>
 * 功能说明： </br>
 *
 * @author: hj
 * @version: 1.0
 * @date:
 */
public class MaterialTextDialog extends MaterialDialog{
    private TextView mTvBody;

    private MaterialTextDialog(Context context) {
       super(context);
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_material_text;
    }

    @Override
    protected void contentView(View view) {
        mTvBody = (TextView)view.findViewById(R.id.tv_body);
    }
}
