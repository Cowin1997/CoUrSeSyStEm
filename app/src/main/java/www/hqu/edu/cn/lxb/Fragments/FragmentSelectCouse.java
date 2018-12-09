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

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.Adapters.StudentCourseSelectAdapter;
import www.hqu.edu.cn.lxb.Adapters.StudentCourseShowAdapter;
import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.database.StudentCourseService;
import www.hqu.edu.cn.lxb.entity.Course;


public class FragmentSelectCouse extends Fragment {
    RecyclerView mRecyclerView;
    StudentCourseSelectAdapter studentCourseSelectAdapter;
    List<Course> list  = null;
    Button bSelect;
    String sid; //学生 id

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    private StudentCourseService studentCourseService;
    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    Boolean isshow=true;


    public static FragmentSelectCouse newInstance(String sid, List<Course>list){
        FragmentSelectCouse fragmentShowCouse =new FragmentSelectCouse();
        Bundle bundle=new Bundle();
        bundle.putString("sid",sid);
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

        studentCourseService = new StudentCourseService(getContext().getFilesDir()+"/test.db3");

         // 初始化数据
         final List<Course>list = getArguments().getParcelableArrayList("list");
         sid = getArguments().getString("sid");
       //  Log.i("bounder",  getArguments().getParcelableArrayList("list").toString());
         //
         View view=inflater.inflate(R.layout.fragment_select,container,false);
      //   if(isshow == false)
        bSelect =view.findViewById(R.id.select);



        // 获取Fragment的recyclerView组件
        mRecyclerView = view.findViewById(R.id.recyclerView);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       // mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //设置item的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //初始化适配器
        studentCourseSelectAdapter = new StudentCourseSelectAdapter(getActivity(), list);
        //设置适配器
        mRecyclerView.setAdapter(studentCourseSelectAdapter);
        this.updateData(list); // 一打开的时候是会初始化的,如需要及时显示;update
        ((Button) view.findViewById(R.id.select)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bSelect",list.toString());
                Log.i("bSelect","我只是个选择按钮,别点我");
               // 选中的课
                List<String>selectCourse = studentCourseSelectAdapter.getSelectedcourse();

                //do 选择
                Log.i("选中的课 ",selectCourse.toString());
                studentCourseService.doSelectCourses(sid,selectCourse);
                studentCourseSelectAdapter.clearSelectCourse(); //清空刚刚插入的
            //    Log.i("可以选择的课 ",studentCourseService.getCourseCanSelectListById(getSid()).toString());
                updateData(studentCourseService.getCourseCanSelectListById(getSid()));

            }
        });

        Log.i("Fragment","------------onCreateView---------------");
        return view;
    }




    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){   // 切换的时候会隐藏
            // todo
        } else {
          this.updateData(this.getList());
        }
    }

    /**
     *  数据更新
     */
    public void updateData(List<Course>list){
        // 对适配器数据进行更新呀
        studentCourseSelectAdapter.setList(list);
        studentCourseSelectAdapter.notifyDataSetChanged(); // 当有数据更新的时候需要告诉适配器的鸭
    }

    public Button getSelectButton(){
        return  this.bSelect;
    }
}
