package www.hqu.edu.cn.lxb.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.coursesystem.R;
import www.hqu.edu.cn.lxb.database.StudentCourseService;
import www.hqu.edu.cn.lxb.entity.Course;

public class StudentCourseSelectAdapter extends RecyclerView.Adapter <StudentCourseSelectAdapter.MyHolder> {
    //上下文
    Context context;
    //传递进来的参数
    List<Course> list;
    //

    private List<String> selectedcourse = new ArrayList<>();

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

    public StudentCourseSelectAdapter(Context context, List<Course> list) {
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

        View itemView = inflater.inflate(R.layout.item_select,parent, false);
        return new MyHolder(itemView);
    }


    // 数据绑定
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Course course = list.get(position);

        if(course.getNumber()!=0) {
            holder.courseType.setText(course.getCourseType());
            holder.courseName.setText(course.getCourseName());
            holder.teacherName.setText(course.getTeacherName());
            holder.courseCredit.setText(course.getCredit() + "");
            holder.number.setText(course.getNumber() + "");
            holder.checkBox.setChecked(false);  //每次选择提交完的时候,复选框都会清默认为空
        }
        else {
            holder.setVisibility(false);
        }


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
        private TextView number;
        private CheckBox checkBox;



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
            // 选择框啊
            checkBox = itemView.findViewById(R.id.toselect);
            //可选人数
            number  = itemView.findViewById(R.id.number);
            checkBox.setChecked(false);


//            点击事件放在adapter中使用，也可以写个接口在activity中调用
//            方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {

                Boolean selected = false;

                @Override
                public void onClick(View v) {

                    //可以选择直接在本位置直接写业务处理
              //      Toast.makeText(context,"点击了xxx",Toast.LENGTH_SHORT).show();
                    if(!selected) {
                        checkBox.setChecked(true);
                        selected = true;
                    }
                    else {
                        checkBox.setChecked(false);
                        selected = false;
                    }

            }});


            //复选框监听



            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectedcourse.add(courseName.getText().toString());
                        Log.i("Select",courseName.getText().toString());
                        Log.i("selectedcoursezzzz",selectedcourse.toString());

                    }
                    if(!isChecked){
                        if(selectedcourse.size()!=0)
                        selectedcourse.remove(selectedcourse.size()-1);
                        Log.i("UnSelect",courseName.getText().toString());
                        Log.i("selectedcoursezzzzz",selectedcourse.toString());
                    }
                }
            });

        }



        public void setVisibility(boolean isVisible){
            RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
            if (isVisible){
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                param.width = LinearLayout.LayoutParams.MATCH_PARENT;
                itemView.setVisibility(View.VISIBLE);
            }else{
                itemView.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
            }
            itemView.setLayoutParams(param);
        }




        }

    public List<String> getSelectedcourse() {
        return selectedcourse;
    }

    public void clearSelectCourse(){
        this.selectedcourse.clear();
    }
}





