package com.knw.myjetchat.ui.fragment;

import android.os.Bundle;
import android.view.View;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.knw.myjetchat.MainActivity;
import com.knw.myjetchat.R;

import java.util.ArrayList;
import java.util.List;

public class EmojiBottomSheetDialogFragment extends BottomSheetDialogFragment implements EmojiAdapter.OnEmojiClickListener{


 /*   @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme); // 应用自定义的 Dialog 样式
    }

  */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 创建并返回对话框的视图
        View view = inflater.inflate(R.layout.emoji_bottom_sheet_dialog, container, false);
          RecyclerView emojiRecyclerView = view.findViewById(R.id.emojis);
      //  LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 10);
        emojiRecyclerView.setLayoutManager(layoutManager);
        List<String> emojiList = new ArrayList<>();
        emojiList.add("\uD83D\uDE00"); // 😄
        emojiList.add("\uD83D\uDE01"); // 😃
        emojiList.add("\uD83D\uDE02"); // 😂
        emojiList.add("\uD83D\uDE03"); // 😁
        emojiList.add("\uD83D\uDE04"); // 😄
        emojiList.add("\uD83D\uDE05"); // 😅
        emojiList.add("\uD83D\uDE06"); // 😆
        emojiList.add("\uD83D\uDE07"); // 😇
        emojiList.add("\uD83D\uDE08"); // 😈
        emojiList.add("\uD83D\uDE09"); // 😉
        emojiList.add("\uD83D\uDE0A"); // 😊
        emojiList.add("\uD83D\uDE0B"); // 😋
        emojiList.add("\uD83D\uDE0C"); // 😌
        emojiList.add("\uD83D\uDE0D"); // 😍
        emojiList.add("\uD83D\uDE0E"); // 😎
        emojiList.add("\uD83D\uDE0F"); // 😏
        emojiList.add("\uD83D\uDE10"); // 😐
        emojiList.add("\uD83D\uDE11"); // 😑
        emojiList.add("\uD83D\uDE12"); // 😒
        emojiList.add("\uD83D\uDE13"); // 😓
        emojiList.add("\uD83D\uDE14"); // 😔
        emojiList.add("\uD83D\uDE15"); // 😕
        emojiList.add("\uD83D\uDE16"); // 😖
        emojiList.add("\uD83D\uDE17"); // 😗
        emojiList.add("\uD83D\uDE18"); // 😘
        emojiList.add("\uD83D\uDE19"); // 😙
        emojiList.add("\uD83D\uDE1A"); // 😚
        emojiList.add("\uD83D\uDE1B"); // 😛
        emojiList.add("\uD83D\uDE1C"); // 😜
        emojiList.add("\uD83D\uDE1D"); // 😝
        EmojiAdapter adapter = new EmojiAdapter(emojiList,this);
        emojiRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onEmojiClick(String emoji) {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addEmojiToEditText(emoji);
        }
    }
}

