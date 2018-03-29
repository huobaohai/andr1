package BeanClass;

/**
 * Created by DELL on 2017/11/15.
 */

public class CourseBean {

    private String Cno;
    private String Cname;
    private String Cxingzhi;
    private String Cdate;
    private String Cplace;
    private String Cpic;
    private String Cvideo;

    public CourseBean() {
    }

    public String getCpic(){return Cpic;}

    public void setCpic(String cpic) {
        Cpic = cpic;
    }

    public void setCvideo(String cvideo) {
        Cvideo = cvideo;
    }

    public String getCvideo() {
        return Cvideo;
    }

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCxingzhi() {
        return Cxingzhi;
    }

    public void setCxingzhi(String cxingzhi) {
        Cxingzhi = cxingzhi;
    }

    public String getCdate() {
        return Cdate;
    }

    public void setCdate(String cdate) {
        Cdate = cdate;
    }

    public String getCplace() {
        return Cplace;
    }

    public void setCplace(String cplace) {
        Cplace = cplace;
    }
}
