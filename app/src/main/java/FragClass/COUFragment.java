package FragClass;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import BeanClass.StudentBean;
import ConClass.getCon;
import huo.andr1.R;

public class COUFragment extends Fragment {

    String stuSno;
    TableLayout tbLy;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.coupage_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        StudentBean stu = (StudentBean) getActivity().getIntent().getSerializableExtra("stu");

        stuSno = stu.getSno();

        new Thread(networkTask).start();
        tbLy = getActivity().findViewById(R.id.tableCou);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");

            if(!val.isEmpty()){
                try {
                    JSONArray jsArr = new JSONArray(val);
                    for (int i = 0; i < jsArr.length(); i++) {
                        JSONObject temp = (JSONObject) jsArr.get(i);
                        TableRow tableRow = new TableRow(getContext());
                        TextView textView1 = new TextView(getContext());
                        TextView textView2 = new TextView(getContext());
                        TextView textView3 = new TextView(getContext());
                        TextView textView4 = new TextView(getContext());
                        TextView textView5 = new TextView(getContext());
                        textView1.setText(temp.getString("cno"));
                        textView2.setText(temp.getString("cname"));
                        textView3.setText(temp.getString("cxz"));
                        textView4.setText(temp.getString("cdate"));
                        textView5.setText(temp.getString("cpl"));
                        textView1.setGravity(Gravity.CENTER);
                        textView2.setGravity(Gravity.CENTER);
                        textView3.setGravity(Gravity.CENTER);
                        textView4.setGravity(Gravity.CENTER);
                        textView5.setGravity(Gravity.CENTER);

                        tableRow.addView(textView1);
                        tableRow.addView(textView2);
                        tableRow.addView(textView3);
                        tableRow.addView(textView4);
                        tableRow.addView(textView5);

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
                res = new getCon().myClaCon(stuSno);
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
}
