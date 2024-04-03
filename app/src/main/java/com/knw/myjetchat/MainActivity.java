package com.knw.myjetchat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.knw.myjetchat.logic.model.Group;
import com.knw.myjetchat.logic.model.Msg;
import com.knw.myjetchat.ui.message.MsgAdapter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//对于每次chat切换，需要使用持久化技术完成聊天页面的读取,每个聊天都有一个list，需要获取真实数据
//对于每次chat切换，群组真实人数没有做逻辑判断（为了方便统一设，真实情况应该是从id拿title，再从数据库拿真实人数赋值给group对象，这里不再实现）
//资料要根据是不是用户本人修改悬浮文本
//需要根据聊天头像进入不同的身份信息界面，同时左边的recent也要实现这个功能，要把所有人都加上
//需要实现聊天框其他按钮功能！
//目前来说，没有用viewmodel，后面要慢慢用上
public class MainActivity extends AppCompatActivity {
    private List<Msg> msgList =new  ArrayList<Msg>();
    private void initMsg()
    {
        Msg msg1= new Msg("从今以后，我也会一直守护你",Msg.TYPE_RECEIVED,R.drawable.aiges,new Date(),"アイギス");
        Msg msg2= new Msg("我爱蛋白粉",Msg.TYPE_RECEIVED,R.drawable.akihiko,new Date(),"明彦");
        Msg msg3=new Msg("...",Msg.TYPE_RECEIVED,R.drawable.aragaki,new Date(),"荒垣");
        Msg msg4=new Msg("来，该吃饭了",Msg.TYPE_RECEIVED,R.drawable.fuuka,new Date(),"風花");
        Msg msg5= new Msg("原神，启动",Msg.TYPE_RECEIVED,R.drawable.junpei,new Date(),"淳平");
        Msg msg6= new Msg("谁家小孩",Msg.TYPE_RECEIVED,R.drawable.ken,new Date(),"天田");
        Msg msg7=new Msg("ワンワン",Msg.TYPE_RECEIVED,R.drawable.koromaru,new Date(),"コロマル");
        Msg msg8=new Msg("来学习吧",Msg.TYPE_RECEIVED,R.drawable.mitsuru,new Date(),"美鶴");
        Msg msg9=new Msg("还想再见你",Msg.TYPE_RECEIVED,R.drawable.yukari,new Date(),"由加莉");
        Msg msg10 =new Msg("孩子们，我回来了",Msg.TYPE_SENT,R.drawable.leader,new Date(),"結城　理");

        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
        msgList.add(msg4);
        msgList.add(msg5);
        msgList.add(msg6);
        msgList.add(msg7);
        msgList.add(msg8);
        msgList.add(msg9);
        msgList.add(msg10);
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

        initMsg();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView messagesRecyclerView =findViewById(R.id.messages);
        messagesRecyclerView.setLayoutManager(layoutManager);
        MsgAdapter adapter = new MsgAdapter(msgList);
        messagesRecyclerView.setAdapter(adapter);
        Button send = findViewById(R.id.sendButton);
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
        // 设置群组名称和成员数量(要写到观察里）
        Group groupTest = new Group("S.E.E.S",10);
        groupNameTextView.setText("# "+groupTest.getGroupName());
        groupMemberCountTextView.setText(groupTest.getGroupMemberCount().toString()+" memebers");


// 检查是否有数据被传递
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

            }
        }





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

                    drawerLayout.closeDrawers();
                    return true;
                } else if (item.getGroupId() == R.id.profiles) {

                    startActivity(new Intent(MainActivity.this,ProfileActivity.class));
                    return false;
                }
                return true; }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}