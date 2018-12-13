package www.hqu.edu.cn.lxb.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import www.hqu.edu.cn.lxb.entity.Teacher;
import www.hqu.edu.cn.lxb.entity.Student;

public class LoginService {
    private static SQLiteDatabase db;
    private static String STUDENT_LOGIN_QUERY="select * from studentlogin where sid= ? and spasswd =?";
    private static String getStudentLoginQuery=" select * from student where sid = ?";
    private static String Teacher_LOGIN_QUERY="select * from teacherlogin where tid= ? and tpasswd =?";
    String FIND_STUDENT_BY_ID ="select * from Student where sid = ?";
    private String getTeacherInfo = "select * from teacher where tid=?";



    public LoginService(String path){
        db = SQLiteDatabase.openOrCreateDatabase(path,null);
    }


    public boolean studentLogin(String username,String password){

        Cursor cursor = db.rawQuery(STUDENT_LOGIN_QUERY,new String[]{username,password});

        if(cursor.getCount()==1){
            Log.i("studentlogin","login success!");
            cursor.close();
            return true;
        }
        else
            Log.i("studentlogin","login failed!");
            return false;
    }


    public boolean teacherLogin(String username,String password){

        Cursor cursor = db.rawQuery(Teacher_LOGIN_QUERY,new String[]{username,password});

        if(cursor.getCount()==1){
            Log.i("teacherlogin","login success!");
            cursor.close();
            return true;
        }
        else
            Log.i("teacherlogin","login failed!");
        return false;
    }




    public Cursor getStudentInfoById(String sid){
        Cursor cursor = db.rawQuery(getStudentLoginQuery,new String[]{sid});
        Log.i("studentinfonumbers",cursor.getCount()+"");
        return cursor;
    }

    public Student getStudentById(String id){
        Student student = new Student();
        Cursor cursor = db.rawQuery(FIND_STUDENT_BY_ID,new String[]{id});
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


        public Teacher getTeacherInfoByTid(String tid) {
            Teacher teacher = new Teacher();
            Cursor cursor = db.rawQuery(getTeacherInfo, new String[]{tid});
            if (cursor.getCount() == 1) {
                cursor.moveToNext();
                teacher.settId(cursor.getString(0));
                teacher.settName(cursor.getString(1));
                teacher.settCollege(cursor.getString(2));
                Log.i("查询到的教师信息结果", teacher.toString());

                return teacher;
            }
            else{
                Log.e("error","教师信息查询结果不唯一");
                return null;
            }

        }

}
