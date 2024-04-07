package com.knw.myjetchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.knw.myjetchat.logic.model.User;
import com.knw.myjetchat.ui.MyListView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;


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

        Intent intent = getIntent();
        if(intent!=null)
        {
            User profile = (User)intent.getSerializableExtra("profile");
            ImageView imageView = findViewById(R.id.iconProfile);
            TextView nameProfile=findViewById(R.id.nameProfile);
            MyListView roles=findViewById(R.id.roles);
            TextView displayname=findViewById(R.id.displayname);
            TextView status =findViewById(R.id.status);
            TextView twitter =findViewById(R.id.twitter);
            TextView timezone =findViewById(R.id.timezone);

            imageView.setImageResource(profile.getIconSource());
            nameProfile.setText(profile.getName());
            displayname.setText(profile.getDisplayName());
            status.setText(profile.getStatus());
            twitter.setText(profile.getTwitter());
            roles.setAdapter( new ArrayAdapter<>(this, R.layout.role_item, profile.getRoles()));
            timezone.setText("Asia/Tokyo");


        }




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
                    //防止原有activity还存在
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    drawerLayout.closeDrawers();
                    return true;
                } else if (item.getGroupId() == R.id.profiles) {

                    if(item.getItemId()==R.id.user2)
                    {
                        //真实情况应该是从数据库拿item列表，然后item列表的id代表一个对象的id，再去查相应对象的真实信息
                        User user = new User("Aiges","アイギス", Arrays.asList("Robot","Angel"),
                                "Alive","twitter.com/aiges", TimeZone.getTimeZone("Asia/Tokyo"),R.drawable.aiges);

                        Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                        intent.putExtra("profile",user);
                        startActivity(intent);
                        finish();
                        return  false;
                    }
                    if(item.getItemId()==R.id.user1)
                    {
                        //真实情况应该是从数据库拿item列表，然后item列表的id代表一个对象的id，再去查相应对象的真实信息
                        User user = new User("Makoto","結城　理", Arrays.asList("Hero","Leader"),"Away","twitter.com/makoto", TimeZone.getTimeZone("Asia/Tokyo"),R.drawable.leader);
                        Intent intent = new Intent(ProfileActivity.this,ProfileActivity.class);
                        intent.putExtra("profile",user);
                        startActivity(intent);
                        finish();
                        return  false;
                    }
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
    //刷新信息
    private  void refreshProfile()
    {

    }

}