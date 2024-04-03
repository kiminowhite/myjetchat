package com.knw.myjetchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {
    private List<String> data = Arrays.asList("Hero", "Leader");
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_profile, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //头像正常显示
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setItemIconTintList(null);
        navigationView.setItemIconPadding(16);
        //不设置菜单点击事件
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);


        Toolbar profilesToolBar = findViewById(R.id.profilestoolbar);
        setSupportActionBar(profilesToolBar);
        // 去掉标题
        getSupportActionBar().setTitle("");

        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null)
        {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_jetchat);
        }

     //给list赋值
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.role_item, data);
       ListView roles = findViewById(R.id.roles);
       roles.setAdapter(adapter);
        //加入自定义群组信息
        View groupToolbarView=getLayoutInflater().inflate(R.layout.group_details,null);

        // 获取群组信息的TextView
        TextView groupNameTextView = groupToolbarView.findViewById(R.id.groupName);
        TextView groupMemberCountTextView = groupToolbarView.findViewById(R.id.groupMemberCount);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getGroupId() == R.id.chats) {
                    //这里处理信息列表的切换
                    groupNameTextView.setText(item.getTitle());
                    Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                    intent.putExtra("groupId",item.getItemId());
                    //因为是去信息界面，所以需要切换到主页了
                    startActivity(intent);
                    drawerLayout.closeDrawers();
                    return true;
                } else if (item.getGroupId() == R.id.profiles) {

                    startActivity(new Intent(ProfileActivity.this,ProfileActivity.class));
                    return false;
                }
                return true; }
        });
       //这边处理数据
        TextView nameProfile = findViewById(R.id.nameProfile);

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