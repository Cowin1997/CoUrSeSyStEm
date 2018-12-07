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

import www.hqu.edu.cn.lxb.Adapters.StudentCourseShowAdapter;
import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.entity.Course;


public class FragmentEditCouse extends Fragment {
    RecyclerView mRecyclerView;
    StudentCourseShowAdapter studentCourseShowAdapter;
    List<Course> list  = null;

    public void setIsshow(Boolean isshow) {
        this.isshow = isshow;
    }

    Boolean isshow=true;


    public static FragmentEditCouse newInstance(String text, List<Course>list){
        FragmentEditCouse fragmentShowCouse =new FragmentEditCouse();
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

         // 初始化数据
         List<Course>list = getArguments().getParcelableArrayList("list");
         Log.i("bounder",  getArguments().getParcelableArrayList("list").toString());
         //
         View view=inflater.inflate(R.layout.fragment_select,container,false);
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
        studentCourseShowAdapter.setList(list);
        studentCourseShowAdapter.notifyDataSetChanged(); // 当有数据更新的时候需要告诉适配器的鸭
    }


}
