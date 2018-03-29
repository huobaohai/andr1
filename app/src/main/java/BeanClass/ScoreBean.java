package BeanClass;

/**
 * Created by DELL on 2017/11/15.
 */

public class ScoreBean {
    private String Sno;
    private String Cno;
    private String Cname;
    private int Score;
    private String Sbu;

    public ScoreBean() {
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
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

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getSbu() {
        return Sbu;
    }

    public void setSbu(String sbu) {
        Sbu = sbu;
    }
}
