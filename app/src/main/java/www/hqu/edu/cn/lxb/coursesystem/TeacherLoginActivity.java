package www.hqu.edu.cn.lxb.coursesystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class TeacherLoginActivity extends AppCompatActivity {
    private TextView TeacherId;
    private EditText PassWord;
    private Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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


            }
        });




    }
}

