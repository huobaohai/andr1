package FragClass;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import BeanClass.StudentBean;
import ConClass.getCon;
import huo.andr1.ListDetailActivity;
import huo.andr1.R;

public class CLFragment extends Fragment {


    String sno;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.clpage_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        StudentBean stu = (StudentBean) getActivity().getIntent().getSerializableExtra("stu");

        sno = stu.getSno();
        new Thread(networkTask).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Bundle data = msg.getData();
            String val = data.getString("value");
            listView = getActivity().findViewById(R.id.allCou);
            List<String> item = new ArrayList<>();
            final HashMap<Integer,JSONObject> map = new HashMap();
            if(!val.isEmpty()){
                try {
                    JSONArray jsArr = new JSONArray(val);
                    for (int i = 0; i < jsArr.length(); i++) {
                        final JSONObject temp = (JSONObject) jsArr.get(i);
                        item.add(temp.getString("cno") + "____" + temp.getString("cname"));
                        map.put(i,temp);
                        Log.i("............",temp.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1,item));//----------------------------
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("<----GetPictureStartTime" , "...");
                    Log.i("............",map.get(i).toString());
                    Intent intent = new Intent();
                    intent.setClass(getContext(), ListDetailActivity.class);
                    intent.putExtra("value",map.get(i).toString());
                    intent.putExtra("snoValue",sno);
                    startActivity(intent);

                }
            });
        }
    };

    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO

            String res = null;
            try {
                res = new getCon().classCon();
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
