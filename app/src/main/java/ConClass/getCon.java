package ConClass;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class getCon {
    public String loginCon(String username ,String password) throws Exception{//学生登录

        String data = null;

        URL url = new URL(Globals.Login_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("userId="+username + "&pwd="+password);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为-->" + data);
            in.close();
            br.close();
        }
        return data;
    }

    public String loginConTea(String username ,String password) throws Exception{//教师登录

        String data = null;

        URL url = new URL(Globals.LoginTea_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("userId="+username + "&pwd="+password);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为-->" + data);
            in.close();
            br.close();
        }
        return data;
    }

    public String classCon() throws Exception{//所有课程
        String data = null;

        URL url = new URL(Globals.ALLC_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String myClaCon(String sno) throws Exception{//我的课表
        String data = null;

        URL url = new URL(Globals.MyCl_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("sno="+sno);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String mySCCon(String sno) throws Exception{//我的分数
        String data = null;

        URL url = new URL(Globals.Score_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("sno="+sno);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String addClCon(String sno,String cno,String cname,String cxz,String cdate,String cpl) throws Exception{//选课
        String data = null;

        URL url = new URL(Globals.AddCl_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("sno="+sno + "&cno="+cno+ "&cname="+cname+ "&cxz="+cxz+ "&cdate="+cdate+ "&cpl="+cpl);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String allStuCon() throws Exception{//所有学生
        String data = null;

        URL url = new URL(Globals.AllStu_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String addStudent(String sno,String sname,int sex,int bj ,int age,String dept,String adress,String pwd) throws Exception{//增加学生
        String data = null;

        URL url = new URL(Globals.AddStu_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("sno="+sno + "&sname="+sname+ "&sex="+sex+ "&bj="+bj+ "&age="+age+ "&dept="+ dept+"&adress="+adress+ "&pwd="+pwd);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String getCno(String cno) throws Exception{//获取学生的课程
        String data = null;

        URL url = new URL(Globals.GetCno_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("cno="+cno);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为course-->" + data);
            in.close();
            br.close();
        }

        return data;
    }

    public String putStuScore(String sno ,String cno,String cname,String score) throws Exception{//put分数

        String data = null;

        URL url = new URL(Globals.PutSco_Servlet_Url);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.connect();

        PrintWriter pWriter = new PrintWriter(con.getOutputStream());
        pWriter.write("sno="+sno + "&cno="+cno+ "&cname="+cname+ "&score="+score);
        pWriter.flush();
        pWriter.close();

        if(con.getResponseCode()==HttpURLConnection.HTTP_OK){
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while((str = br.readLine())!=null){//BufferedReader特有功能，一次读取一行数据
                buffer.append(str);
            }

            data=buffer.toString();
            Log.d("mylog", "请求结果为-->" + data);
            in.close();
            br.close();
        }
        return data;
    }
}
