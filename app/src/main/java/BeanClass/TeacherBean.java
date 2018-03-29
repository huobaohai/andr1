package BeanClass;

import java.io.Serializable;

/**
 * Created by DELL on 2017/11/15.
 */

public class TeacherBean implements Serializable {

    private String Tno;
    private String Tname;
    private int Tsex;
    private int Tage;
    private String Tadress;
    private String Txuewei;

    public TeacherBean() {
    }

    public String getTno() {
        return Tno;
    }

    public void setTno(String tno) {
        Tno = tno;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public int getTsex() {
        return Tsex;
    }

    public void setTsex(int tsex) {
        Tsex = tsex;
    }

    public int getTage() {
        return Tage;
    }

    public void setTage(int tage) {
        Tage = tage;
    }

    public String getTadress() {
        return Tadress;
    }

    public void setTadress(String tadress) {
        Tadress = tadress;
    }

    public String getTxuewei() {
        return Txuewei;
    }

    public void setTxuewei(String txuewei) {
        Txuewei = txuewei;
    }
}
