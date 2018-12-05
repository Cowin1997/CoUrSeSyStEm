package www.hqu.edu.cn.lxb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.entity.Course;

public class StudentCourseAdapter extends RecyclerView.Adapter <StudentCourseAdapter.MyHolder> {
    //上下文
    Context context;
    //传递进来的参数
    List<Course> list;


    public StudentCourseAdapter(Context context, List<Course> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.item_layout, null);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Course course = list.get(position);
        holder.courseType.setText(course.getCourseType());
        holder.courseName.setText(course.getCourseName());
        holder.teacherName.setText(course.getTeacherName());
        holder.courseCredit.setText(course.getCourseType());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     *
     *  ViewHolder 类
     *
     */

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView courseType;
        private TextView courseName;
        private TextView teacherName;
        private TextView courseCredit;



        public MyHolder(View itemView) {
            super(itemView);
            //课程性质
            courseType = itemView.findViewById(R.id.coursetype);
            //课程名字
            courseName = itemView.findViewById(R.id.coursename);
            //任课老师
            teacherName = itemView.findViewById(R.id.teachername);
            //学分
            courseCredit = itemView.findViewById(R.id.coursecredit);


            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //可以选择直接在本位置直接写业务处理
//                    //Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
//                    //此处回传点击监听事件
//                    if(onItemClickListener!=null){
//                        onItemClickListener.OnItemClick(v, goodsEntityList.get(getLayoutPosition()));
//                    }
//                }
//            });











        }
    }





}
