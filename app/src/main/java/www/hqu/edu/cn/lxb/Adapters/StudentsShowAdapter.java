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
import www.hqu.edu.cn.lxb.entity.Student;

public class StudentsShowAdapter extends RecyclerView.Adapter <StudentsShowAdapter.MyHolder> {
    //上下文
    Context context;
    //传递进来的参数
    List<Student> list;

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    /**
     *
     *  初始化
     *
     * @param context
     * @param list
     */

    public StudentsShowAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建自定义布局
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.student_item_show,parent, false);
        return new MyHolder(itemView);
    }


    // 数据绑定
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Student student = list.get(position);
        holder.SId.setText(student.getSid());
        holder.SName.setText(student.getsName());
        holder.SGender.setText(student.getSex());
        holder.Sgrade.setText(student.getsGrade());
        holder.SCollege.setText(student.getsCollege());


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
        private TextView SId;
        private TextView SName;
        private TextView SGender;
        private TextView Sgrade;
        private TextView SCollege;


        public MyHolder(final View itemView) {
            super(itemView);
            //学生id
            SId = itemView.findViewById(R.id.infosid);
            //学生姓名
            SName = itemView.findViewById(R.id.infosname);
            //学生性别
            SGender = itemView.findViewById(R.id.infosgender);
            //学生年级
            Sgrade = itemView.findViewById(R.id.infosgrade);
            //学生院系
            SCollege = itemView.findViewById(R.id.infoscollege);

        }
        }
    }





