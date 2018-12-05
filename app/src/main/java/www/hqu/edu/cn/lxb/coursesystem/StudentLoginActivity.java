package www.hqu.edu.cn.lxb.coursesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import www.hqu.edu.cn.lxb.database.LoginService;

/**
 * A login screen that offers login via email/password.
 */
public class StudentLoginActivity extends AppCompatActivity {


    // UI references.
    private TextView studentId;
    private EditText studentPassWord;
    private Button login;


    private LoginService loginService;
    private String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //获取服务
        path = this.getFilesDir()+"/test.db3";
        loginService = new LoginService(path);
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);

        // 获取输入
        studentId =  findViewById(R.id.StudentID);
        studentPassWord = findViewById(R.id.StudentPassWord);
        login = findViewById(R.id.studentLogin);

        // 登录监听
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginService.studentLogin(studentId.getText()+"",studentPassWord.getText()+"")==true){
                    Intent intent = new Intent(StudentLoginActivity.this, StudentHome.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(StudentLoginActivity.this,"账号或密码错误!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
