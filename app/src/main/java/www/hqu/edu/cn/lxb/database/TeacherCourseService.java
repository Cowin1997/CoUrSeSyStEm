package www.hqu.edu.cn.lxb.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.entity.Course;
import www.hqu.edu.cn.lxb.entity.Student;
import www.hqu.edu.cn.lxb.entity.Teacher;

public class TeacherCourseService {
    private SQLiteDatabase database;
    private String Find_Teach_courses = " select * from course where tid = ?";
    private String FIND_Course =  "select cname from course where tid = ?";
    private String All_Student_To_Courses="select student.sid,student.sname,student.sgrade,student.smajor,student.scollege,student.gender from student where sid in (select sid from course_select join course join teacher where course_select.cid=course.cid and course.tid = teacher.tid and teacher.tid=? and course.cname= ?) " +
            " order by student.sid asc";


    public TeacherCourseService(SQLiteDatabase database){
            this.database = database;

    }



    public List<Course> getTeachCoursesByTid(String tid){
        List<Course> courseList = new ArrayList<>();
         Cursor cursor= database.rawQuery(Find_Teach_courses,new String[]{tid});
         while (cursor.moveToNext()){
             Course course = new Course();
             course.setCourseId(cursor.getString(0));
             course.setCourseName(cursor.getString(1));
             course.setCourseType(cursor.getString(2));
             course.setGrade(cursor.getInt(3));
             course.setNumber(cursor.getInt(4));
             course.setGrade(cursor.getInt(5));
             courseList.add(course);
         }

        System.out.print("Teach Courses:"+courseList.toString());
        return  courseList;
    }


   public List<String> getTeacherCourseNames(String tid){
        List<String> courses = new ArrayList<>();
        Cursor cursor= database.rawQuery(FIND_Course,new String[]{tid});
        while (cursor.moveToNext()){
           courses.add(cursor.getString(0));
        }
        Log.i("所有的课程名",courses.toString());
        return courses;
    }


    public List<Student> getStudentsToCourse(String tid,String coursename){

        List<Student> students = new ArrayList<>();

        Cursor cursor= database.rawQuery(All_Student_To_Courses,new String[]{tid,coursename});

        while (cursor.moveToNext()){
            Student student = new Student();
            student.setSid(cursor.getString(0));
            student.setsName(cursor.getString(1));
            student.setsGrade(cursor.getString(2));
            student.setsMajor(cursor.getString(3));
            student.setsCollege(cursor.getString(4));
            if(cursor.getInt(5)==1) {
                student.setSex("男");
                student.setGender(1);
            }
            if(cursor.getInt(5)==0) {
                student.setSex("女");
                student.setGender(0);
            }
            students.add(student);
        }

        Log.i("选修"+coursename+"的学生有:",students.toString());
        return students;
    }




}
