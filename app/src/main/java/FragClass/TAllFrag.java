package FragClass;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import BeanClass.StudentBean;
import BeanClass.TeacherBean;
import ConClass.getCon;
import huo.andr1.R;

/**
 * Created by DELL on 2017/11/19.
 */

public class TAllFrag extends Fragment {

    String tno;
    String sno;
    String sname;
    int sex;
    int banji;
    int sage;
    String sdept;
    String sadress;
    TableLayout tbLy;
    TextView addBt;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.tall_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        TeacherBean teac = (TeacherBean) getActivity().getIntent().getSerializableExtra("tea");
        tno = teac.getTno();

        new Thread(networkTask).start();
        tbLy = getActivity().findViewById(R.id.tall_tb);

        addBt = getActivity().findViewById(R.id.addStuBt);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Bundle data = msg.getData();
            String val = data.getString("value");
            if(!val.isEmpty()){
                try {
                    JSONArray jsArr = new JSONArray(val);
                    for (int i = 0; i < jsArr.length(); i++) {
                        final JSONObject temp = (JSONObject) jsArr.get(i);

                        sno = temp.getString("sno");
                        sname = temp.getString("sname");
                        sex = temp.getInt("sex");
                        banji = temp.getInt("banji");
                        sage = temp.getInt("sage");
                        sdept = temp.getString("sdept");
                        sadress = temp.getString("sadress");

                        TableRow tableRow = new TableRow(getContext());
                        TextView textView1 = new TextView(getContext());
                        TextView textView2 = new TextView(getContext());
                        TextView textView3 = new TextView(getContext());
                        TextView textView4 = new TextView(getContext());
                        TextView textView5 = new TextView(getContext());
                        TextView textView6 = new TextView(getContext());
                        TextView textView7 = new TextView(getContext());
                        textView1.setText(sno);
                        textView2.setText(sname);
                        textView3.setText(String.valueOf(sex));
                        textView4.setText(String.valueOf(sage));
                        textView5.setText(String.valueOf(banji));
                        textView6.setText(sdept);
                        textView7.setText(sadress);
                        textView1.setGravity(Gravity.CENTER);
                        textView2.setGravity(Gravity.CENTER);
                        textView3.setGravity(Gravity.CENTER);
                        textView4.setGravity(Gravity.CENTER);
                        textView5.setGravity(Gravity.CENTER);
                        textView6.setGravity(Gravity.CENTER);
                        textView7.setGravity(Gravity.CENTER);

                        tableRow.addView(textView1);
                        tableRow.addView(textView2);
                        tableRow.addView(textView3);
                        tableRow.addView(textView4);
                        tableRow.addView(textView5);
                        tableRow.addView(textView6);
                        tableRow.addView(textView7);
                        tbLy.addView(tableRow);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO

            String res = null;
            try {
                res = new getCon().allStuCon();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value",res);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };


    protected void showAddDialog() {

        LayoutInflater factory = LayoutInflater.from(getContext());
        final View textEntryView = factory.inflate(R.layout.addstu_dialog, null);
        final EditText eTno = textEntryView.findViewById(R.id.eTno);
        final EditText eTname = textEntryView.findViewById(R.id.eTname);
        final EditText eTsex = textEntryView.findViewById(R.id.eTsex);
        final EditText eTbj = textEntryView.findViewById(R.id.eTbj);
        final EditText eTage = textEntryView.findViewById(R.id.eTage);
        final EditText eTdept = textEntryView.findViewById(R.id.eTdept);
        final EditText eTadress = textEntryView.findViewById(R.id.eTadress);
        final EditText eTpwd = textEntryView.findViewById(R.id.eTpwd);

        AlertDialog.Builder ad1 = new AlertDialog.Builder(getActivity());
        ad1.setView(textEntryView);
        ad1.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

                StudentBean newStu = new StudentBean();
                newStu.setSno(eTno.getText().toString());
                newStu.setSname(eTname.getText().toString());
                newStu.setSsex(Integer.valueOf(eTsex.getText().toString()));
                newStu.setBanjiId(Integer.valueOf(eTbj.getText().toString()));
                newStu.setSage(Integer.valueOf(eTage.getText().toString()));
                newStu.setSdept(eTdept.getText().toString());
                newStu.setSadress(eTadress.getText().toString());
                newStu.setPwd(eTpwd.getText().toString());
                new Thread(new addStudent(newStu)).start();

            }
        });
        ad1.setNegativeButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        ad1.show();// 显示对话框
    }

    public class addStudent implements Runnable{
        StudentBean student;
        public addStudent(StudentBean student){
            this.student = student;
        }
        @Override
        public void run() {
            // TODO
            String res = null;
            try {
                res = new getCon().addStudent(student.getSno(),student.getSname(),
                        student.getSsex(),student.getBanjiId(),student.getSage(),
                        student.getSdept(),student.getSadress(),student.getPwd());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value",res);
            msg.setData(data);
            handlerAdd.sendMessage(msg);
        }
    }

    Handler handlerAdd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            if (val.equals("true")){
                Toast.makeText(getContext(),"增加成功",Toast.LENGTH_SHORT).show();
            }
            else if (val.equals("false")){
                Toast.makeText(getContext(),"该学生已经存在",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
