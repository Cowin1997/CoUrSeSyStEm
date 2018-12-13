package www.hqu.edu.cn.lxb.entity;

public class Student {
    private String sid;
    private String sName;
    private String sGrade;
    private String sMajor;
    private String sCollege;
    private Integer gender;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sName='" + sName + '\'' +
                ", sGrade='" + sGrade + '\'' +
                ", sMajor='" + sMajor + '\'' +
                ", sCollege='" + sCollege + '\'' +
                ", gender=" + gender +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public String getsMajor() {
        return sMajor;
    }

    public void setsMajor(String sMajor) {
        this.sMajor = sMajor;
    }

    public String getsCollege() {
        return sCollege;
    }

    public void setsCollege(String sCollege) {
        this.sCollege = sCollege;
    }

    public Student() {

    }
}
