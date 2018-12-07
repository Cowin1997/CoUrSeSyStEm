package www.hqu.edu.cn.lxb.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chanven.lib.cptr.PtrClassicFrameLayout;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.Adapters.StudentCourseShowAdapter;
import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.entity.Course;


public class FragmentShowCouse extends Fragment {
    RecyclerView mRecyclerView;
    StudentCourseShowAdapter studentCourseShowAdapter;
    List<Course> list  = null;
    PtrClassicFrameLayout ptrClassicFrameLayout;  //列表下拉刷新
    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    Boolean isshow=true;


    public static FragmentShowCouse newInstance(String text, List<Course>list){
        FragmentShowCouse fragmentShowCouse =new FragmentShowCouse();
        Bundle bundle=new Bundle();
        bundle.putString("text",text);
        //初始化的时候传入list
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) list);
        fragmentShowCouse.setArguments(bundle);
        return fragmentShowCouse;
    }

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  initData();
        // 初始化数据
         List<Course>list = getArguments().getParcelableArrayList("list");
         Log.i("bounder",  getArguments().getParcelableArrayList("list").toString());
         //

         View view=inflater.inflate(R.layout.fragment_show,container,false);
         if(isshow == false)
        ((Button) view.findViewById(R.id.select)).setVisibility(View.INVISIBLE);
        // 获取Fragment的recyclerView组件
        mRecyclerView = view.findViewById(R.id.recyclerView);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       // mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //设置item的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //初始化适配器
        studentCourseShowAdapter = new StudentCourseShowAdapter(getActivity(), list);
        //设置适配器
        mRecyclerView.setAdapter(studentCourseShowAdapter);
        this.updateData(list); // 一打开的时候是会初始化的,如需要及时显示;update
        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.test_recycler_view_frame);
        Log.i("ptrClassicFrameLayout",ptrClassicFrameLayout.toString());
        Log.i("Fragment","------------onCreateView---------------");

        ptrClassicFrameLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);


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
        list.add(course4);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){   // 切换的时候会隐藏

        } else {
          this.updateData(this.getList());
        }
    }
    @Override
    public void onResume() {
        Log.i("Fragement","resume");
        super.onResume();
    }

    /**
     *  数据更新
     */
    public void updateData(List<Course>list){
        // 对适配器数据进行更新呀
        studentCourseShowAdapter.setList(list);
        studentCourseShowAdapter.notifyDataSetChanged(); // 当有数据更新的时候需要告诉适配器的鸭
    }


}
