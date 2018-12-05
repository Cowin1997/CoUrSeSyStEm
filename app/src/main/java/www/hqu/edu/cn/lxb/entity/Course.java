package www.hqu.edu.cn.lxb.entity;

public class Course {
    private String courseName; //课程名字
    private String teacherName; //任课老师
    private Integer credit;   //学分
    private String college; //学院
    private String major; //专业
    private String courseId; //课程id
    private Integer number; //可选人数
    private String courseType;

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", credit=" + credit +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", courseId='" + courseId + '\'' +
                ", number=" + number +
                ", courseType='" + courseType + '\'' +
                '}';
    }

    public String getCourseType() {
        return courseType;
    }

    public Course(String courseName, String teacherName, Integer credit, String college, String major, String courseId, Integer number, String courseType) {
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.credit = credit;
        this.college = college;
        this.major = major;
        this.courseId = courseId;
        this.number = number;
        this.courseType = courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
