package www.hqu.edu.cn.lxb.coursesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.Fragments.FragmentCommon;
import www.hqu.edu.cn.lxb.entity.Course;


public class StudentHome extends AppCompatActivity {
    private NavigationView navigationView;
    private View headerView;
    private TextView studentName;
    private TextView studentCollege;
    TabView tabView;
    FragmentCommon fragmentCommon1;
    FragmentCommon fragmentCommon2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 初始化 两个 fragment
        fragmentCommon1 = FragmentCommon.newInstance("1");
        fragmentCommon2 = FragmentCommon.newInstance("2");


        getSupportActionBar().hide();
        setContentView(R.layout.studenthome);
        tabView=  findViewById(R.id.tabView);
        navigationView = findViewById(R.id.nav);
        headerView = navigationView.getHeaderView(0);
        //显示学生名字
        studentName = headerView.findViewById(R.id.studentname);
        studentName.setText("lixibin");
        //显示学生学院信息
        studentCollege = headerView.findViewById(R.id.studentcollege);
        studentCollege.setText("计算机科学与技术学院");
        // 监听菜单按钮单击事件
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
                        Intent intent = new Intent(StudentHome.this,IndexActivity.class);
                        startActivity(intent);
                        break;
                    }

                }


                return false;
            }
        });


        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChild01=new TabViewChild(0,0,"首页",fragmentCommon1);
        TabViewChild tabViewChild02=new TabViewChild(0,0,"设置",fragmentCommon2);

        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabView.setTabViewDefaultPosition(2);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());

        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
                    @Override
                    public void onTabChildClick(int position, ImageView currentImageIcon, TextView currentTextView) {
                        if (position==1){
                            List<Course> list = new ArrayList<>();
                            Course course1 = new Course("软件工程","张一",1,"计算机科学与技术","网络工程","12345",50,"专业选修课");
                            list.add(course1);
                            fragmentCommon2.setList(list);


                        }

                 Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    }




