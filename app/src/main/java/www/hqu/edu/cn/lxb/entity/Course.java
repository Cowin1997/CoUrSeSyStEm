package www.hqu.edu.cn.lxb.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {
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

    protected Course(Parcel in) {
        courseName = in.readString();
        teacherName = in.readString();
        if (in.readByte() == 0) {
            credit = null;
        } else {
            credit = in.readInt();
        }
        college = in.readString();
        major = in.readString();
        courseId = in.readString();
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        courseType = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(teacherName);
        if (credit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(credit);
        }
        dest.writeString(college);
        dest.writeString(major);
        dest.writeString(courseId);
        if (number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(number);
        }
        dest.writeString(courseType);
    }
}
