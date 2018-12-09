package www.hqu.edu.cn.lxb.entity;

public class Teacher {
     private String tId;
     private String tName;
     private String tCollege;

    @Override
    public String toString() {
        return "Teacher{" +
                "tId='" + tId + '\'' +
                ", tName='" + tName + '\'' +
                ", tCollege='" + tCollege + '\'' +
                '}';
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettCollege() {
        return tCollege;
    }

    public void settCollege(String tCollege) {
        this.tCollege = tCollege;
    }

    public Teacher() {
    }
}
