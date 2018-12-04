package www.hqu.edu.cn.lxb.coursesystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import www.hqu.edu.cn.lxb.database.myDataBase;

public class IndexActivity extends AppCompatActivity {
    private String databasepath;
    private myDataBase myDataBase;
    private Button Teacher; // 教师
    private Button Student; // 学生
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.index);
        //打印一下数据库路径
        databasepath = this.getFilesDir()+"/test.db3";
        Log.i("databaseurl",databasepath);
        //分别获取教师登录和学生登录按钮和点击跳转登录
        Teacher = findViewById(R.id.Teacher);
        Student = findViewById(R.id.Student);
        // 学生跳转
        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this,StudentLoginActivity.class);
                startActivity(intent);
            }
        });
        // 教师跳转
        Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this,TeacherLoginActivity.class);
                startActivity(intent);
            }
        });


        //初始化一下 数据库
        myDataBase = new myDataBase(databasepath);



        // test
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(databasepath,null);
        //
        Log.i("database","数据库初始化成功!");


    }



}
