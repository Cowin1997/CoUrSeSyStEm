package www.hqu.edu.cn.lxb.coursesystem;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import www.hqu.edu.cn.lxb.database.LoginService;
import www.hqu.edu.cn.lxb.entity.Teacher;

/**
 * A login screen that offers login via email/password.
 */
public class TeacherLoginActivity extends AppCompatActivity {
    private TextView TeacherId;
    private EditText PassWord;
    private Button login;
    private LoginService loginService;
    private String path;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //获取服务
        path = this.getFilesDir()+"/test.db3";
        loginService = new LoginService(path);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_login);
        TeacherId = findViewById(R.id.TeacherID);
        PassWord = findViewById(R.id.TeacherPassWord);
        login = findViewById(R.id.teachetLogin);
        Log.i("databaseurl",this.getFilesDir()+"/my.db3");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("loginInfo",TeacherId.getText()+":"+PassWord.getText());
                if(loginService.teacherLogin(TeacherId.getText()+"",PassWord.getText()+"")==true){
                    Intent intent = new Intent(TeacherLoginActivity.this, TeacherHome.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(TeacherLoginActivity.this,"账号或密码错误!",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}

