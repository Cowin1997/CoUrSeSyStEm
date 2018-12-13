package www.hqu.edu.cn.lxb.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.entity.Course;
import www.hqu.edu.cn.lxb.entity.Student;

public class StudentCourseService {
    private static SQLiteDatabase db;
    String FIND_COURSE_SELECT = "select course_select.sid,course.ctype,course.cname,teacher.tname,course.credit,course.number from course_select join course join student\n" +
            "    join teacher where  course.tid = teacher.tid and   course_select.cid=course.cid\n" +
            "     and course_select.sid=student.sid and student.sid= ?";
    String COURSE_CAN_SELECT = " select * from course join teacher where  course.tid=teacher.tid  and grade = ? and major = ? and cid  not in (select cid from\n" +
            "course_select where sid = ?);";
    String FIND_STUDENT_BY_ID ="select * from Student where sid = ?";

    String Select_CourseId_By_cname = "select cid from course where cname=?";

    String Insert_SelectCourse = " insert into course_select values(?,?)";

    String Update_Number_Course = "update course set number=number-1 where cid = ?";
    String Update_Number_Course2 = "update course set number=number+1 where cid = ?";
    String Delete_Courses_Selected = "delete from course_select where sid= ? and cid = (select cid from course where cname= ?)";
    public StudentCourseService(String path) {
        db = SQLiteDatabase.openOrCreateDatabase(path,null);
    }
    // 通过学生的学号来获取 学生的选课的信息

    public List<Course> getCourseListById(String sid){
        List<Course> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(FIND_COURSE_SELECT,new String[]{sid});
        if(cursor.getCount()<=0)
            return null;
        else{
            while (cursor.moveToNext()){

                Course course = new Course();
                course.setTeacherName(cursor.getString(cursor.getColumnIndex("tname")));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("cname")));
                course.setCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex("credit"))));
                course.setCourseType(cursor.getString(cursor.getColumnIndex("ctype")));
                course.setNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndex("number"))));
                list.add(course);

                }
            Log.i("courses",list.toString());
        }

        return list;

    }
    // 通过学生的学号来获取 学生的可以选课的信息
    public List<Course> getCourseCanSelectListById(String sid){

        Student student  = this.getStudentById(sid);
        Log.i("学生啊",student.toString());
        List<Course> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(COURSE_CAN_SELECT,new String[]{student.getsGrade(),student.getsMajor(),sid});
        if(cursor.getCount()<=0)
            return null;
        else{
            while (cursor.moveToNext()){
                Course course = new Course();
                course.setNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndex("number"))));
                course.setTeacherName(cursor.getString(cursor.getColumnIndex("tname")));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("cname")));
                course.setCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex("credit"))));
                course.setCourseType(cursor.getString(cursor.getColumnIndex("ctype")));
                list.add(course);
            }
            Log.i("coursescanselect",list.toString());
        }

        return list;

    }



    public Student getStudentById(String sid){
        Student student = new Student();
        Cursor cursor = db.rawQuery(FIND_STUDENT_BY_ID,new String[]{sid});
        if(cursor.getCount()==1){
            cursor.moveToNext();
            student.setsName(cursor.getString(cursor.getColumnIndex("sname")));
            student.setSid(cursor.getString(cursor.getColumnIndex("sid")));
            student.setsMajor(cursor.getString(cursor.getColumnIndex("smajor")));
            student.setsGrade(cursor.getString(cursor.getColumnIndex("sgrade")));
            student.setsCollege(cursor.getString(cursor.getColumnIndex("scollege")));
            Log.i("查询到的学生信息结果",student.toString());

            return student;
        }
        else{
            Log.e("error","查询结果不唯一");
            return null;
        }

    }

    // 根据 学号 和选中的课 来 选课

    public void doSelectCourses(String sid,List<String> courses){

        for(int i=0;i<courses.size();i++){
                 Cursor cursor = db.rawQuery(Select_CourseId_By_cname,new String[]{courses.get(i)});
                 cursor.moveToNext();
                Log.i("选中的课",cursor.getCount()+"");
                String cid = cursor.getString(cursor.getColumnIndex("cid"));
                db.execSQL(Insert_SelectCourse,new String[]{sid,cid});
                this.UpdateCourseNumberSelect(cid);
                Log.i("插入选课",i+"");

        }


    }


    public void doDeleteCoursesSelected (String sid,List<String> courses){


        for(int i=0;i<courses.size();i++){
            Cursor cursor = db.rawQuery(Select_CourseId_By_cname,new String[]{courses.get(i)});
            cursor.moveToNext();

            String cid = cursor.getString(cursor.getColumnIndex("cid"));
            db.execSQL(Delete_Courses_Selected,new String[]{sid,courses.get(i)});
            this.UpdateCourseNumberDelete(cid);
            Log.i("删除选课",i+":"+courses.get(i));
        }

    }





    // 选课-1
    public void UpdateCourseNumberSelect(String cid){
        db.execSQL(Update_Number_Course,new String[]{cid});
    }
    //
    public void UpdateCourseNumberDelete(String cid){
        db.execSQL(Update_Number_Course2,new String[]{cid});
    }

}
