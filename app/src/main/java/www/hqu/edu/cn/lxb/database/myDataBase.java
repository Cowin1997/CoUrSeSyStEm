package www.hqu.edu.cn.lxb.database;

import android.database.sqlite.SQLiteDatabase;

public class myDataBase {
    // sqlite3 默认没有开启外键约束
    String OPEN_FOREIGEN_KEY = " PRAGMA FOREIGN_KEYS=ON";
    // 创建学生表
    String STUDENT =
            "create table if not exists student("
                    +" sid varchar(10) primary key,"
                    +"sname varchar(20),"
                    +" sgrade varchar(4),"
                    +" smajor varchar(20),"
                    +"scollege varchar(20)"
                    +")";
    // 创建教师表t
    String TEACHER =
            "create table if not exists teacher("
                    +" tid varchar(10) primary key,"
                    +"tname varchar(20),"
                    +"tcollege varchar(20)"
                    +")";
    // 创建学生登录表
    String STUDENT_LOGIN =  "create table if not exists studentlogin("
            +"sid varchar(10) primary key,"
            +"spasswd varchar(20),"

            +"foreign key (sid) references student(sid)"
            +")";
    // 创建教师登录表
    String TEACHER_LOGIN =  "create table if not exists teacherlogin("
            +"tid varchar(10) primary key,"
            +"tpasswd varchar(20),"
            +"foreign key (tid) references teacher(tid)"
            +")";
    // 创建课程表
    String COURSE = "create table if not exists course("
            +"cid varchar(5) primary key,"
            +"cname varchar(20),"
            +"ctype varchar(20),"
            +"grade int,"
            +"number int,"
            +"credit int,"
            +"tid varchar(10),"
            +"college varchar(20),"
            +"major varchar(20),"
            +"foreign key(tid) references teacher(tid)"
            +")";
    // 选课信息表
    String COURSE_SELECT = "create table if not exists course_select("
            +"sid varchar(10),"
            +" cid varchar(5),"
            +"foreign key(sid) references student(sid),"
            +" foreign key(cid) references course(cid)"
            +")";


    // 测试: 手动插入学生数据
    String DEFAULTINSERT = "insert into student values (\"1625131017\",\"李溪滨\",\"2016\",\"网络工程\",\"计算机科学与技术\")";
    // 测试: 手动插入学生的登录账户和密码
    String DEFAULT_INSERT_STUDENT_LOGIN = " insert into studentlogin values (\"1625131017\",\"admin\")";
    // 测试: 手动插入教师信息
    String DEFAULT_INSERT_TEACHER = "insert into teacher values ('123456','张三','计算机科学与技术')";
    String DEFAULT_INSERT_TEACHER2 = "insert into teacher values ('123457','李四','计算机科学与技术')";
    // 测试: 手动插入课程信息
    String DEFAULT_INSERT_COURSE = "insert into course values('12345','软件工程','专业选修课',2016,50,2,'123456','计算机科学与技术','网络工程')";
    String DEFAULT_INSERT_COURSE2 = "insert into course values('12346','高等数学','专业必修课',2016,50,3,'123457','计算机科学与技术','网络工程')";
    // ces: 手动插入学生选课信息
    String DEFAULT_SELECT_COURSE = "insert into course_select values('1625131017','12345')";
    String DEFAULT_SELECT_COURSE2 = "insert into course_select values('1625131017','12346')";
    // 多表查询
    // select course_select.sid,course.cname from course_select join course join student where course_select.cid=course.cid
    // and course_select.sid=student.id and student.id='1625131017';
    // 多表查询 --》 1625131017|软件工程|张三



//      select course_select.sid,course.cname,teacher.name from course_select join course join student
//     join teacher where  course.tid = teacher.id and   course_select.cid=course.cid
//     and course_select.sid=student.id and student.id='1625131017';


    /**
     *
     *  初始化建立数据库
     *
     */
    public myDataBase(String path) {
        SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(path,null);


        //如果表存在先删除

        database.execSQL("DROP TABLE IF EXISTS course");
        database.execSQL("DROP TABLE IF EXISTS course_select");
        database.execSQL("DROP TABLE IF EXISTS teacherlogin");

        database.execSQL("DROP TABLE IF EXISTS studentlogin");
        database.execSQL("DROP TABLE IF EXISTS teacher");
        database.execSQL("DROP TABLE IF EXISTS student");










        database.execSQL(OPEN_FOREIGEN_KEY); //开启使用外键
        database.execSQL(STUDENT); //创建学生表
        database.execSQL(TEACHER); //创建教师表
        database.execSQL(STUDENT_LOGIN); // 学生登录
        database.execSQL(TEACHER_LOGIN); // 教师登录
        database.execSQL(COURSE); // 创建课程表
        database.execSQL(COURSE_SELECT); //创建课程选择表
        // 测试的时候 每次这边都会初始化 程序报错 所以开始时候先删除所有表

        database.execSQL(DEFAULTINSERT); // 插入学生信息
        database.execSQL(DEFAULT_INSERT_STUDENT_LOGIN);// 插入账号密码
        database.execSQL(DEFAULT_INSERT_TEACHER); //插入教师信息
        database.execSQL(DEFAULT_INSERT_TEACHER2); //插入教师信息
        database.execSQL(DEFAULT_INSERT_COURSE); //插入课程信息
        database.execSQL(DEFAULT_INSERT_COURSE2); //插入课程信息
        database.execSQL(DEFAULT_SELECT_COURSE); //插入选课
        database.execSQL(DEFAULT_SELECT_COURSE2); //插入选课

}

}
