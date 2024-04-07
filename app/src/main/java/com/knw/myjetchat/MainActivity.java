package com.knw.myjetchat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.knw.myjetchat.logic.model.Group;
import com.knw.myjetchat.logic.model.Msg;
import com.knw.myjetchat.logic.model.User;
import com.knw.myjetchat.ui.fragment.EmojiSheetDialogFragment;
import com.knw.myjetchat.ui.message.MsgAdapter;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


//资料要根据是不是用户本人修改悬浮文本，实现了，但是没有灵活搜信息
//目前来说，没有用viewmodel，后面要慢慢用上

//需要实现聊天框其他按钮功能

public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList =new  ArrayList<Msg>();
    private String fileGroupName;
    private void initMsg()
    {
        Msg msg1= new Msg("从今以后，我也会一直守护你",Msg.TYPE_RECEIVED,R.drawable.aiges,new Date(),"アイギス");
     /*   Msg msg2= new Msg("我爱蛋白粉",Msg.TYPE_RECEIVED,R.drawable.akihiko,new Date(),"明彦");
        Msg msg3=new Msg("...",Msg.TYPE_RECEIVED,R.drawable.aragaki,new Date(),"荒垣");
        Msg msg4=new Msg("来，该吃饭了",Msg.TYPE_RECEIVED,R.drawable.fuuka,new Date(),"風花");
        Msg msg5= new Msg("原神，启动",Msg.TYPE_RECEIVED,R.drawable.junpei,new Date(),"淳平");
        Msg msg6= new Msg("谁家小孩",Msg.TYPE_RECEIVED,R.drawable.ken,new Date(),"天田");
        Msg msg7=new Msg("ワンワン",Msg.TYPE_RECEIVED,R.drawable.koromaru,new Date(),"コロマル");
        Msg msg8=new Msg("来学习吧",Msg.TYPE_RECEIVED,R.drawable.mitsuru,new Date(),"美鶴");
        Msg msg9=new Msg("还想再见你",Msg.TYPE_RECEIVED,R.drawable.yukari,new Date(),"由加莉");
        Msg msg10 =new Msg("孩子们，我回来了",Msg.TYPE_SENT,R.drawable.leader,new Date(),"結城　理");
*/

        msgList.add(msg1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);


        Button send = findViewById(R.id.sendButton);


        //头像正常显示
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setItemIconTintList(null);
        navigationView.setItemIconPadding(16);
        //设置菜单点击事件(要先判断了）
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        navigationView.setCheckedItem(R.id.group1);
        //加入自定义群组信息
        View groupToolbarView=getLayoutInflater().inflate(R.layout.group_details,null);
        // 获取群组信息的TextView
        TextView groupNameTextView = groupToolbarView.findViewById(R.id.groupName);
        TextView groupMemberCountTextView = groupToolbarView.findViewById(R.id.groupMemberCount);

        Group groupTest = new Group("S.E.E.S特别搜查队",10);
//      检查是否有群组名称数据被传递
        Intent intent = getIntent();
        if (intent != null) {
            // 从Intent中获取数据
            int value = intent.getIntExtra("groupId",0);

            // 在这里使用接收到的数据
            if(value!=0)
            {
                navigationView.setCheckedItem(value);
                Menu menu = navigationView.getMenu();
                MenuItem item = menu.findItem(value);
                groupTest=new Group(item.getTitle().toString(),10);
                groupNameTextView.setText("# "+groupTest.getGroupName());
                groupMemberCountTextView.setText(groupTest.getGroupMemberCount().toString()+" memebers");
                fileGroupName=groupTest.getGroupName();

            }

            groupNameTextView.setText("# "+groupTest.getGroupName());
            groupMemberCountTextView.setText(groupTest.getGroupMemberCount().toString()+" memebers");
            fileGroupName=groupTest.getGroupName();
        }


        //判断文件是否为空，为空直接初始化
        SharedPreferences prefs = getSharedPreferences(fileGroupName, Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        boolean b =allEntries.isEmpty();

        if (allEntries.isEmpty()) {
            // SharedPreferences文件为空，即文件不存在
           initMsg();
        } else {
            // SharedPreferences文件不为空，文件存在且包含键值对
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Msg>>() {}.getType();
         String json = prefs.getString("msgList","");
           List<Msg> msgList = gson.fromJson(json, listType);
           this.msgList=msgList;

        }




        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView messagesRecyclerView =findViewById(R.id.messages);
        messagesRecyclerView.setLayoutManager(layoutManager);
        MsgAdapter adapter = new MsgAdapter(msgList);
        messagesRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputText = findViewById(R.id.inputText);
                String content = inputText.getText().toString();
                if(!content.isEmpty())
                {
                    Msg msg = new Msg(content,Msg.TYPE_SENT,R.drawable.leader,new Date(),"結城　理");
                    // 添加新消息到msgList
                    msgList.add(msg);

                    if (adapter != null) {
                        adapter.notifyItemInserted(msgList.size() - 1);
                    }
                    RecyclerView messagesRecyclerView = findViewById(R.id.messages);
                    messagesRecyclerView.scrollToPosition(msgList.size() - 1);

                    inputText.setText("");

                }
            }
        });


        // 设置状态栏颜色为白色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(Color.WHITE);
        }



        // 获取Toolbar的LayoutParams，并设置布局参数为居中
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        groupToolbarView.setLayoutParams(layoutParams);


        toolbar.addView(groupToolbarView);


        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null)
        {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_jetchat);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getGroupId() == R.id.chats) {
                    //这里处理信息列表的切换
                    groupNameTextView.setText(item.getTitle());
                    //重启是因为要获取新的信息列表
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    intent.putExtra("groupId",item.getItemId());
                     startActivity(intent);
                     finish();
                    drawerLayout.closeDrawers();
                    return true;
                } else if (item.getGroupId() == R.id.profiles) {
                    //去之前，传输真实信息。这里只做一个
                    if(item.getItemId()==R.id.user2)
                    {
                        //真实情况应该是从数据库拿item列表，然后item列表的id代表一个对象的id，再去查相应对象的真实信息
                        User user = new User("Aiges","アイギス", Arrays.asList("Robot","Angel"),
                                "Alive","twitter.com/aiges", TimeZone.getTimeZone("Asia/Tokyo"),R.drawable.aiges);

                        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                        intent.putExtra("profile",user);
                        startActivity(intent);

                        return  false;
                    }
                    if(item.getItemId()==R.id.user1)
                    {
                        //真实情况应该是从数据库拿item列表，然后item列表的id代表一个对象的id，再去查相应对象的真实信息
                        User user = new User("Makoto","結城　理", Arrays.asList("Hero","Leader"),"Away","twitter.com/makoto", TimeZone.getTimeZone("Asia/Tokyo"),R.drawable.leader);
                        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                        intent.putExtra("profile",user);
                        startActivity(intent);

                        return  false;
                    }

                }
                return true; }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmojiSheetDialogFragment bottomSheetDialogFragment = new EmojiSheetDialogFragment();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    //关闭activity时，将聊天记录储存到本地
        //1、获取群组名称，生成相应的文件名
        System.out.println(fileGroupName);
        SharedPreferences.Editor editor = getSharedPreferences(fileGroupName, Context.MODE_PRIVATE).edit();
        //当前list信息列表转换为json
        Gson gson = new Gson();
        String json = gson.toJson(msgList);
        System.out.println(json);
      //存储信息
       editor.putString("msgList",json);
       editor.apply();

    }





        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        if(item.getItemId()==R.id.serach)
        {
            Toast.makeText(this,"你点击了搜索",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}