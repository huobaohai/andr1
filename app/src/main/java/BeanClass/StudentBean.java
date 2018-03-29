package BeanClass;

import java.io.Serializable;

/**
 * Created by DELL on 2017/11/15.
 */

public class StudentBean implements Serializable{
    private String Sno;
    private String Sname;
    private int Ssex;
    private int BanjiId;
    private int Sage;
    private String Sdept;
    private String Sadress;
    private String pwd;

    public StudentBean() {
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public int getSsex() {
        return Ssex;
    }

    public void setSsex(int ssex) {
        Ssex = ssex;
    }

    public int getBanjiId() {
        return BanjiId;
    }

    public void setBanjiId(int banjiId) {
        BanjiId = banjiId;
    }

    public int getSage() {
        return Sage;
    }

    public void setSage(int sage) {
        Sage = sage;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getSadress() {
        return Sadress;
    }

    public void setSadress(String sadress) {
        Sadress = sadress;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
