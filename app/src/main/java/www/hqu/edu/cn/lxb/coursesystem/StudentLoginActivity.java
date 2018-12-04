package www.hqu.edu.cn.lxb.coursesystem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import www.hqu.edu.cn.lxb.database.LoginService;

import static android.Manifest.permission.READ_CONTACTS;

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
                loginService.studentLogin(studentId.getText()+"",studentPassWord.getText()+"");
            }
        });

    }

}
