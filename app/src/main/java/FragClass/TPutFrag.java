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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import ConClass.getCon;
import huo.andr1.R;

/**
 * Created by DELL on 2017/11/19.
 */

public class TPutFrag extends Fragment {
    String input;
    Button getCnoBt;
    EditText getcno;
    TableLayout tbLy;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.put_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        tbLy = getActivity().findViewById(R.id.putCno_tb);
        getCnoBt = getActivity().findViewById(R.id.finCno);
        getCnoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tbLy.removeAllViews();
                new Thread(networkTask).start();
            }
        });


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
                        final JSONObject temp = (JSONObject) jsArr.get(i);
                        TableRow tableRow = new TableRow(getContext());
                        TextView textView1 = new TextView(getContext());
                        TextView textView2 = new TextView(getContext());
                        TextView textView3 = new TextView(getContext());
                        TextView textView4 = new TextView(getContext());
                        TextView textView5 = new TextView(getContext());
                        textView1.setText(temp.getString("sno"));
                        textView2.setText(temp.getString("cno"));
                        textView3.setText(temp.getString("cname"));
                        textView4.setText(temp.getString("cxz"));
                        textView5.setText("录分");
                        textView5.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                final EditText et = new EditText(getContext());

                                new AlertDialog.Builder(getContext()).setTitle("分数")
                                        .setIcon(android.R.drawable.ic_dialog_info)
                                        .setView(et)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                input = et.getText().toString();
                                                Log.d("mylog", "---------->" + input);
                                                new Thread(new putScore(temp)).start();
                                            }
                                        })
                                        .setNegativeButton("取消", null)
                                        .show();

                            }
                        });
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

            getcno = getActivity().findViewById(R.id.putCno);

            String res = null;
            try {
                res = new getCon().getCno(getcno.getText().toString());
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

    public class putScore implements Runnable{
        JSONObject temp1;
        public putScore(JSONObject temp1){
            this.temp1 = temp1;
        }
        @Override
        public void run() {
            // TODO



            String res = null;
            try {
                Log.d("mylog", "xuanke1111111111111-->" + temp1.toString() );
                res = new getCon().putStuScore(temp1.get("sno").toString(),temp1.get("cno").toString(),temp1.get("cname").toString(),input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value",res);
            msg.setData(data);
            handlerPut.sendMessage(msg);
        }
    }

    Handler handlerPut = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            if (val.equals("true")){
                Toast.makeText(getContext(),"录入成功",Toast.LENGTH_SHORT).show();
            }
            else if (val.equals("false")){
                Toast.makeText(getContext(),"已经存在分数",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
