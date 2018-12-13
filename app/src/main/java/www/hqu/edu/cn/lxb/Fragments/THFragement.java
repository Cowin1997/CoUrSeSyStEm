package www.hqu.edu.cn.lxb.Fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.Adapters.StudentCourseShowAdapter;
import www.hqu.edu.cn.lxb.Adapters.StudentsShowAdapter;
import www.hqu.edu.cn.lxb.coursesystem.R;

import www.hqu.edu.cn.lxb.database.TeacherCourseService;
import www.hqu.edu.cn.lxb.entity.Course;
import www.hqu.edu.cn.lxb.entity.Student;

public class THFragement extends Fragment {
    // 循环列表
    private RecyclerView mRecyclerView;
    // 下拉框
    private Spinner spinner;
    // 数据
    private ArrayList<String> listdata = null;
    // 适配器
    private ArrayAdapter arr_adapter;
    // 数据服务
    private TeacherCourseService courseService;
    // 教师id
    private String tid;
    //循环列表的适配器
    private StudentsShowAdapter studentsShowAdapter;
    //初始化的学生
    private List<Student> studentList;
    // 下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;
 //
    private Handler mHandler = new Handler() {
     public void handleMessage(android.os.Message msg) {
         switch (msg.what) {
             case 0x01:
                 swipeRefreshLayout.setRefreshing(false);
                 Toast.makeText(getContext(),"更新完成！",Toast.LENGTH_SHORT).show();
                 break;
         }
     }
 };
     //
    private String coursename;


    private FloatingActionButton floatingActionButton;

    // 实例化
    public static THFragement newInstance(String tid, List<String> list){
        THFragement fragmentShow =new THFragement();
        Bundle bundle=new Bundle();
        bundle.putString("tid",tid);
        //初始化的时候传入list
        bundle.putStringArrayList("list",(ArrayList<String>) list);
        fragmentShow.setArguments(bundle);

        return fragmentShow;
    }

    //CreateView


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 获取教师id
        this.tid = getArguments().getString("tid");

        View view=inflater.inflate(R.layout.tshow,container,false);
        spinner = view.findViewById(R.id.mycourses); // 下拉框
        floatingActionButton = view.findViewById(R.id.fab);
       // 循环列表
        this.mRecyclerView = view.findViewById(R.id.recyclerView);
         //下拉刷新
        swipeRefreshLayout = view.findViewById(R.id.main_srl);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);




        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        // mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //设置item的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //初始化适配器
        studentsShowAdapter = new StudentsShowAdapter(getActivity(), null);


        courseService = new TeacherCourseService(SQLiteDatabase.openOrCreateDatabase(getContext().getFilesDir()+"/test.db3",null));
        // 设置适配器
        arr_adapter= new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, courseService.getTeacherCourseNames(this.tid));
        //设置下拉样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               TextView textView  = view.findViewById(android.R.id.text1);
               Log.i("下拉框",position+"||"+textView.getText());
                coursename = textView.getText()+"";
               studentList = courseService.getStudentsToCourse(tid,coursename);
                updateData(studentList);
            }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 刷新
                studentList = courseService.getStudentsToCourse(tid,coursename);
                updateData(studentList);
                mHandler.sendEmptyMessageDelayed(0x01, 2000);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("floatingButton","我被点击了");

            }
        });







        //加载下拉框适配器
        spinner.setAdapter(arr_adapter);
        //设置循环列表适配器
        mRecyclerView.setAdapter(studentsShowAdapter);

        System.out.print("***********OncreateView************");
        return view;




    }





    ArrayList<String> simulData(){
        this.listdata = new ArrayList<>();
        listdata.add("a");
        listdata.add("b");
        listdata.add("c");
        return listdata;
    }

    /**
     *  数据更新
     */
    public void updateData(List<Student>list){
        // 对适配器数据进行更新呀
        studentsShowAdapter.setList(list);
        studentsShowAdapter.notifyDataSetChanged(); // 当有数据更新的时候需要告诉适配器的鸭
    }






}
