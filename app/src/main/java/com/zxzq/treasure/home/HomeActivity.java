package com.zxzq.treasure.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxzq.treasure.R;
import com.zxzq.treasure.user.UserProf;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.picasso.Picasso;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        // DrawerLayout的侧滑监听
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout
                , mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
        // 侧滑菜单item的选择监听
        mNavigation.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageView imageView = (ImageView) mNavigation.getHeaderView(0).findViewById(R.id.iv_usericon);
        String headpic = UserProf.getInstances().getHeadpic();
        if(headpic!=null) {
            Picasso.with(this).load(headpic).into(imageView);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_hide:// 埋藏宝藏的时候
                Toast.makeText(this, "埋藏宝藏", Toast.LENGTH_SHORT).show();
                // TODO: 2017/4/1 0001
                break;
            case R.id.menu_logout:// 退出的时候
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                // TODO: 2017/4/1 0001
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
