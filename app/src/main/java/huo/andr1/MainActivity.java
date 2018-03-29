package huo.andr1;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import ConClass.* ;
import BeanClass.* ;
public class MainActivity extends AppCompatActivity {

    private EditText LUser;
    private EditText LPwd;
    private Button LBt;
    private RadioGroup gp;
    private RadioButton stuBt;
    private RadioButton teaBt;
    boolean identity = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LUser = findViewById(R.id.LoginUser);
        LPwd = findViewById(R.id.LoginPwd);
        LBt = findViewById(R.id.LoginBt);
        gp = findViewById(R.id.group);
        stuBt = findViewById(R.id.stu);
        teaBt = findViewById(R.id.tea);

        stuBt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if(arg1){
                    teaBt.setChecked(false);
                    identity = true;
                }
            }
        });
        teaBt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (arg1) {
                    stuBt.setChecked(false);
                    identity = false;
                }
            }
        });

        LBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (identity){
                    Log.d("<----startTime" , "...");
                    new Thread(networkTask).start();
                }
                else {
                    Log.d("<----startTime" , "...");
                    new Thread(networkTaskTea).start();
                }
            }
        });

    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Bundle data1 = msg.getData();
                    String val2 = data1.getString("value");
                    if(val2.equals("mmfalse")){
                        Toast.makeText(MainActivity.this,val2,Toast.LENGTH_SHORT).show();
                    }
                    else if(val2.equals("nouser")){
                        Toast.makeText(MainActivity.this,val2,Toast.LENGTH_SHORT).show();
                    }else {
                        StudentBean newStu = new StudentBean();
                        try {
                            JSONObject jsonObj = new JSONObject(val2);

                            newStu.setSno(jsonObj.getString("Sno"));
                            newStu.setSname(jsonObj.getString("Sname"));
                            newStu.setSsex(jsonObj.getInt("Ssex"));
                            newStu.setBanjiId(jsonObj.getInt("BanjiId"));
                            newStu.setSage(jsonObj.getInt("Sage"));
                            newStu.setSdept(jsonObj.getString("Sdept"));
                            newStu.setSadress(jsonObj.getString("Sadress"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent Login = new Intent();
                        Login.setClass(MainActivity.this,mainPage.class);
                        Login.putExtra("Fragment","0");
                        Login.putExtra("stu",newStu);
                        startActivity(Login);
                        finish();
                        Log.d("<----endTime" , "...");
                    }
                    break;
                case 2:
                    Bundle data = msg.getData();
                    String val = data.getString("value");
                    if(val.equals("mmfalse")){
                        Toast.makeText(MainActivity.this,val,Toast.LENGTH_SHORT).show();
                    }
                    else if(val.equals("nouser")){
                        Toast.makeText(MainActivity.this,val,Toast.LENGTH_SHORT).show();
                    }else {
                        TeacherBean newTea = new TeacherBean();
                        try {
                            JSONObject jsonObj = new JSONObject(val);

                            newTea.setTno(jsonObj.getString("Tno"));
                            newTea.setTname(jsonObj.getString("Tname"));
                            newTea.setTsex(jsonObj.getInt("Tsex"));
                            newTea.setTage(jsonObj.getInt("Tage"));
                            newTea.setTadress(jsonObj.getString("Tadress"));
                            newTea.setTxuewei(jsonObj.getString("Txuewei"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent Login = new Intent();
                        Login.setClass(MainActivity.this,TeaMainActivity.class);
                        Login.putExtra("Fragment","0");
                        Login.putExtra("tea",newTea);
                        startActivity(Login);
                        finish();
                        Log.d("<----endTime" , "...");
                    }
                    break;
            }

        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            String userId = LUser.getText().toString();
            String userPwd = LPwd.getText().toString();
            String res = null;
            try {
                res = new getCon().loginCon(userId,userPwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value",res);
            msg.setData(data);
            msg.what = 1;
            handler.sendMessage(msg);
        }
    };

    Runnable networkTaskTea = new Runnable() {
        @Override
        public void run() {
            String userId = LUser.getText().toString();
            String userPwd = LPwd.getText().toString();
            String res = null;
            try {
                res = new getCon().loginConTea(userId,userPwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value",res);
            msg.setData(data);
            msg.what = 2;
            handler.sendMessage(msg);
        }
    };
}
