package www.hqu.edu.cn.lxb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.entity.Course;

public class StudentCourseShowAdapter extends RecyclerView.Adapter <StudentCourseShowAdapter.MyHolder> {
    //上下文
    Context context;
    //传递进来的参数
    List<Course> list;

    public List<Course> getList() {
        return list;
    }

    public void setList(List<Course> list) {
        this.list = list;
    }

    /**
     *
     *  初始化
     *
     * @param context
     * @param list
     */

    public StudentCourseShowAdapter(Context context, List<Course> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建自定义布局
        LayoutInflater inflater = LayoutInflater.from(context);
        // 下面这个 有毒 , 不会使用root view
        //  View itemView = View.inflate(context, R.layout.item_show, null);
    //    View itemView = inflater.inflate(R.layout.item_show,parent, false);

        View itemView = inflater.inflate(R.layout.item_show,parent, false);
        return new MyHolder(itemView);
    }


    // 数据绑定
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Course course = list.get(position);
        holder.courseType.setText(course.getCourseType());
        holder.courseName.setText(course.getCourseName());
        holder.teacherName.setText(course.getTeacherName());
        holder.courseCredit.setText(course.getCredit()+"");



    }

    /**
     *   这个方法返回整数,用于数据绑定的行数;
     *
      * @return
     */
    @Override
    public int getItemCount() {

        if(this.list!=null)
        return list.size();
        else
            return 0;
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



        public MyHolder(final View itemView) {
            super(itemView);
            //课程性质
            courseType = itemView.findViewById(R.id.coursetype);
            //课程名字
            courseName = itemView.findViewById(R.id.coursename);
            //任课老师
            teacherName = itemView.findViewById(R.id.teachername);
            //学分
            courseCredit = itemView.findViewById(R.id.coursecredit);


        }
        }
    }





