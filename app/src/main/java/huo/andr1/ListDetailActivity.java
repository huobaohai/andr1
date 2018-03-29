package huo.andr1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ConClass.Globals;
import ConClass.getCon;


public class ListDetailActivity extends AppCompatActivity {
    ViewPager vp;
    int Which_Bitmap = 0;
    ListView listView;
    Button button;
    Button addCbtn;
    String pics[] = null;
    String vid;
    List<Bitmap> bitmapList = new ArrayList<>();
    List<View> lt = new ArrayList<>();
    String sno1;
    String cno1;
    String cname1;
    String cxz1;
    String cdate1;
    String cpl1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        vp = findViewById(R.id.image_vp);
        listView = findViewById(R.id.list);
        button = findViewById(R.id.btn);
        addCbtn = findViewById(R.id.addCoubtn);

        List<String> item = new ArrayList<>();
        String ss = getIntent().getStringExtra("value");
        sno1 = getIntent().getStringExtra("snoValue");
        try {
            JSONObject js = new JSONObject(ss);
            Log.d("111111111111",js.toString());
            cno1 = js.getString("cno");
            cname1 = js.getString("cname");
            cxz1 = js.getString("cxz");
            cdate1 = js.getString("cdate");
            cpl1 = js.getString("cpl");
            item.add("课程编号：   " + cno1);
            item.add("课程名称：   " + cname1);
            item.add("课程性质：   " + cxz1);
            item.add("上课日期：   " + cdate1);
            item.add("上课地点：   " + cpl1);
            Log.d("test","--------------------> " + js.getString("cpic"));
            pics = js.getString("cpic").split(",");
            vid = Globals.localhost + js.getString("cvidio");
            new Thread(setPicture).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,item));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ListDetailActivity.this,VidioActivity.class);
                intent.putExtra("videoAdress",vid);
                startActivity(intent);
            }
        });
        addCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(addCourse).start();
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Which_Bitmap = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    Runnable setPicture = new Runnable() {
        @Override
        public void run() {

            getHttpBitmap();
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    };
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    final LinearLayout.LayoutParams param = (android.widget.LinearLayout.LayoutParams) vp.getLayoutParams();
                    Log.i("image=========",bitmapList.size()+"");
                    for (int i = 0;i<bitmapList.size();i++){
                        ImageView imageView = new ImageView(getBaseContext());
                        imageView.setImageBitmap(bitmapList.get(i));
                        lt.add(imageView);
                        registerForContextMenu(imageView);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.i("??????","????????????????????");
//                                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                vp.setLayoutParams(param);
                            }
                        });
                    }
                    vp.setAdapter(new vpAdapter());

                    break;
                case 2:
                    Bundle data = msg.getData();
                    String val = data.getString("value");
                    if (val.equals("true")){
                        Toast.makeText(ListDetailActivity.this,"选课成功，可在课表查询",Toast.LENGTH_SHORT).show();
                    }
                    else if (val.equals("false")){
                        Toast.makeText(ListDetailActivity.this,"已经选过了，可在课表查询",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    public void getHttpBitmap(){
        URL myFileURL;
        Bitmap bitmap = null;
        for (int i = 0;i<pics.length;i++){
            try{
                myFileURL = new URL(Globals.localhost + pics[i]);
                Log.i("aaa","-------------->" + Globals.localhost + pics[i]);
                //获得连接
                HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
                //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
                conn.setConnectTimeout(6000);
                //连接设置获得数据流
                conn.setDoInput(true);
                //不使用缓存
                conn.setUseCaches(false);
                //这句可有可无，没有影响
                //conn.connect();
                //得到数据流
                InputStream is = conn.getInputStream();
                //解析得到图片
                bitmap = BitmapFactory.decodeStream(is);
                //关闭数据流
                is.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            bitmapList.add(bitmap);
        }

    }
    class vpAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return lt.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = lt.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = lt.get(position);
            container.removeView(v);
        }
    }

    Runnable addCourse = new Runnable(){

        @Override
        public void run() {
            // TODO
            String res = null;
            try {
                res = new getCon().addClCon(sno1,cno1,cname1,cxz1,cdate1,cpl1);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.i("selected","which bitmap is selected = " +  Which_Bitmap);
        switch (item.getItemId()){
            case R.id.save:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
                }
                else{
                    try {
                        saveBitmap(bitmapList.get(Which_Bitmap));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.big:
                LinearLayout.LayoutParams big_params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                vp.setLayoutParams(big_params);
                Toast.makeText(ListDetailActivity.this,"查看图片",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void saveBitmap(Bitmap bitmap) throws IOException{

        File appDir = new File(Environment.getExternalStorageDirectory(),"myImage");
        if (!appDir.exists()){
            appDir.mkdir();
        }
        String fileMame = System.currentTimeMillis()+".png";
        File file = new File(appDir,fileMame);
        Log.i("=====>>>","path ....."+file);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG ,100,out)){
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Toast.makeText(ListDetailActivity.this,"保存成功-> " + file,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                try {
                    saveBitmap(bitmapList.get(Which_Bitmap));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(ListDetailActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
