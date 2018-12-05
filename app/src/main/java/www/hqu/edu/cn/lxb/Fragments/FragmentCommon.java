package www.hqu.edu.cn.lxb.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.Adapters.StudentCourseAdapter;
import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.entity.Course;


public class FragmentCommon extends Fragment {
    RecyclerView mRecyclerView;
    StudentCourseAdapter studentCourseAdapter;
    List<Course> list;

    public static FragmentCommon newInstance(String text){
        FragmentCommon fragmentCommon=new FragmentCommon();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
        View view=inflater.inflate(R.layout.fragment_common,container,false);
        // 获取Fragment的recyclerView组件
        mRecyclerView = view.findViewById(R.id.recyclerView);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       // mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //设置item的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //初始化适配器
        studentCourseAdapter = new StudentCourseAdapter(getActivity(), list);
        //设置适配器
        mRecyclerView.setAdapter(studentCourseAdapter);

        return view;
    }


    //测试 手动设置
    public void initData(){
        list = new ArrayList<>();
// String courseName, String teacherName, Integer credit, String college, String major, String courseId, Integer number, String courseType
        Course course1 = new Course("软件工程","张一",1,"计算机科学与技术","网络工程","12345",50,"专业选修课");
        Course course2 = new Course("软件工程","张二",2,"计算机科学与技术","网络工程","12345",50,"专业选修课");
        Course course3 = new Course("软件工程","张三",3,"计算机科学与技术","网络工程","12345",50,"专业选修课");
        Course course4 = new Course("软件工程","张四",4,"计算机科学与技术","网络工程","12345",50,"专业选修课");
        list.add(course1);
        list.add(course2);
        list.add(course3);

    }
}
