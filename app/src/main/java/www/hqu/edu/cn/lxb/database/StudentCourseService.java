package www.hqu.edu.cn.lxb.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import www.hqu.edu.cn.lxb.entity.Course;

public class StudentCourseService {
    private static SQLiteDatabase db;
    String FIND_COURSE_SELECT = "select course_select.sid,course.ctype,course.cname,teacher.tname,course.credit from course_select join course join student\n" +
            "    join teacher where  course.tid = teacher.tid and   course_select.cid=course.cid\n" +
            "     and course_select.sid=student.sid and student.sid='1625131017'";

    public StudentCourseService(String path) {
        db = SQLiteDatabase.openOrCreateDatabase(path,null);
    }
    // 通过学生的学号来获取 学生的选课的信息
    public List<Course> getCourseListById(String sid){
        List<Course> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(FIND_COURSE_SELECT,new String[]{});
        if(cursor.getCount()<=0)
            return null;
        else{
            while (cursor.moveToNext()){
                Course course = new Course();
                course.setTeacherName(cursor.getString(cursor.getColumnIndex("tname")));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("cname")));
                course.setCredit(Integer.parseInt(cursor.getString(cursor.getColumnIndex("credit"))));
                course.setCourseType(cursor.getString(cursor.getColumnIndex("ctype")));
                Log.i("ctype",cursor.getString(cursor.getColumnIndex("ctype")));
                Log.i("cname",cursor.getString(cursor.getColumnIndex("cname")));
                Log.i("teacher",cursor.getString(cursor.getColumnIndex("tname")));
                Log.i("credit",cursor.getString(cursor.getColumnIndex("credit")));
                list.add(course);

                }
            Log.i("courses",list.toString());
        }

        return list;

    }




}
