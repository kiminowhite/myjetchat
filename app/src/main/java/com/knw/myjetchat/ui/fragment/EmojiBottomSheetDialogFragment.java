package com.knw.myjetchat.ui.fragment;

import android.os.Bundle;
import android.view.View;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.knw.myjetchat.MainActivity;
import com.knw.myjetchat.R;
import com.knw.myjetchat.logic.model.Msg;

import java.util.ArrayList;
import java.util.List;

public class EmojiBottomSheetDialogFragment extends BottomSheetDialogFragment implements EmojiAdapter.OnEmojiClickListener,StickerAdpter.OnStickerClickListener{


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

      Button emoji =  view.findViewById(R.id.emojipick);
      Button stickers = view.findViewById(R.id.stickpick);

      //初始化资源列表
      List<String> emojiList =new ArrayList<>();
      List<Integer> stickerIdList = new ArrayList<>();
      emojiList=initemojiList(emojiList);//  LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
      stickerIdList=initiStickerIdList(stickerIdList);

        GridLayoutManager emojilayoutManager = new GridLayoutManager(getActivity(), 10);
        LinearLayoutManager stickerlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        emojiRecyclerView.setLayoutManager(emojilayoutManager);
        EmojiAdapter emojiadapter = new EmojiAdapter(emojiList,this);
        StickerAdpter stickerAdpter = new StickerAdpter(stickerIdList,this);


        //默认emoji列表
        emojiRecyclerView.setAdapter(emojiadapter);

        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiRecyclerView.setLayoutManager(emojilayoutManager);
               emojiRecyclerView.setAdapter(emojiadapter);
            }
        });
        stickers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               emojiRecyclerView.setLayoutManager(stickerlayoutManager);
               emojiRecyclerView.setAdapter(stickerAdpter);
            }
        });


        return view;
    }


    public List<String>  initemojiList(List<String> emojiList)
    {
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
        return emojiList;
    }
    public List<Integer> initiStickerIdList(List<Integer> stickerIdList)
    {
        stickerIdList.add(R.drawable.sticker);
        stickerIdList.add(R.drawable.akihiko);
        stickerIdList.add(R.drawable.aragaki);
        stickerIdList.add(R.drawable.yukari);
        stickerIdList.add(R.drawable.junpei);
        stickerIdList.add(R.drawable.koromaru);
        stickerIdList.add(R.drawable.ken);
        return stickerIdList;
    }
    @Override
    public void onEmojiClick(String emoji) {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addEmojiToEditText(emoji);
        }
    }
    @Override
    public void onStickerClick(Integer stickerId) {
        if (getActivity() != null && getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).addStickerMsg(stickerId);
            //关闭当前fragment
            getParentFragmentManager().beginTransaction().remove(this).commit();

        }
    }
}

