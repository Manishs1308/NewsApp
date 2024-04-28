package com.example.newsapp.ui.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.example.newsapp.R;
import com.example.newsapp.databinding.ItemLoaderBinding;

public class MyLoader {

    private Dialog dialog;
    private final Context mContext;

    public MyLoader(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
       ItemLoaderBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_loader, null, false);
        mContext = context;
        try {
            dialog = new Dialog(mContext);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(binding.getRoot());
            dialog.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
