package www.hqu.edu.cn.lxb.coursesystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.Fragments.FragmentEditCouse;
import www.hqu.edu.cn.lxb.Fragments.FragmentSelectCouse;

import www.hqu.edu.cn.lxb.Fragments.THFragement;
import www.hqu.edu.cn.lxb.database.StudentCourseService;
import www.hqu.edu.cn.lxb.database.TeacherCourseService;
import www.hqu.edu.cn.lxb.entity.Course;


public class TeacherHome extends AppCompatActivity {
    private NavigationView navigationView;
    private View headerView;
    private TextView studentName;
    private TextView studentCollege;
    TabView tabView;
    FragmentSelectCouse fragmentShowCouse0;
    THFragement fragmentShow;
    FragmentEditCouse fragmentShowCouse2;
    private Toolbar mNormalToolbar;
    private DrawerLayout drawerLayout; //抽屉的layout
    private StudentCourseService studentCourseService;
    private static SQLiteDatabase db;
    private TeacherCourseService teacherCourseService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // 获取数据库服务
        String databasepath = this.getFilesDir()+"/test.db3";
        this.db = SQLiteDatabase.openOrCreateDatabase(databasepath,null);
        teacherCourseService = new TeacherCourseService(this.db);
        // 隐藏 Bar
        getSupportActionBar().hide();
        // 设置布局
        setContentView(R.layout.student);
        // 获得 toolbar
        mNormalToolbar = findViewById(R.id.toolbar);

        tabView=  findViewById(R.id.tabView);

        drawerLayout = findViewById(R.id.a);
        navigationView = findViewById(R.id.nav);
     //   navigationView = drawerLayout.findViewById(R.id.nav);
        headerView = navigationView.getHeaderView(0);
        //显示教师名字
        studentName = headerView.findViewById(R.id.studentname);
        studentName.setText(getIntent().getStringExtra("tname"));
        //显示教师学院信息
        studentCollege = headerView.findViewById(R.id.studentcollege);
        studentCollege.setText(getIntent().getStringExtra("tcollege"));
        //为抽屉设置监听




        // 初始化 两个 fragment
        fragmentShowCouse0 = FragmentSelectCouse.newInstance("1",null);
        fragmentShow = THFragement.newInstance(getIntent().getStringExtra("tid"),null);
        fragmentShowCouse2 = FragmentEditCouse.newInstance("3",null);
       // ptrClassicFrameLayout = (PtrClassicFrameLayout) fragmentShowCouse1.getLayoutInflater()
        //这里设置 TabView

        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild0=new TabViewChild(0,0,"选择课程", fragmentShowCouse0);
        TabViewChild tabViewChild1=new TabViewChild(0,0,"学生列表", fragmentShow);
        TabViewChild tabViewChild2=new TabViewChild(0,0,"修改课程", fragmentShowCouse2);
        tabViewChildList.add(tabViewChild0);
        tabViewChildList.add(tabViewChild1);
        tabViewChildList.add(tabViewChild2);
        tabView.setTabViewDefaultPosition(1);

        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());

        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
                    @Override
                    public void onTabChildClick(int position, ImageView currentImageIcon, TextView currentTextView) {

            }
        });

        // 图标 被点击了
        mNormalToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ToolBar","啊,要死了,我被点击了");
                if(!navigationView.isShown())
                drawerLayout.openDrawer(navigationView);
                else
                    drawerLayout.closeDrawer(navigationView);
            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_menu_info: {
                        Log.i("menu", "info");
                        studentName.setText("李溪滨");
                        studentCollege.setText("计算机科学与技术学院");
                        break;
                    }
                    case R.id.nav_menu_logout:{
                        Intent intent = new Intent(TeacherHome.this,IndexActivity.class);
                        startActivity(intent);
                        break;
                    }

                }
                return false;
            }
        });



    }






}




