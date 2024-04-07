package com.knw.myjetchat.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.knw.myjetchat.R;

public class EmojiSheetDialogFragment extends BottomSheetDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        // 创建并返回对话框的视图
        View view = inflater.inflate(R.layout.emoji_bottom_sheet_dialog, container, false);
        // 在这里可以初始化视图组件、设置监听器等
        return view;
    }
}
