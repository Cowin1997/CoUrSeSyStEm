package www.hqu.edu.cn.lxb.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoginService {
    private static SQLiteDatabase db;
    private static String STUDENT_LOGIN_QUERY="select * from studentlogin where id= ? and passwd =?";
    private static String getStudentLoginQuery=" select * from student where id = ?";



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

    public Cursor getStudentInfoById(String sid){
        Cursor cursor = db.rawQuery(getStudentLoginQuery,new String[]{sid});
        Log.i("studentinfonumbers",cursor.getCount()+"");
        return cursor;
    }



}
