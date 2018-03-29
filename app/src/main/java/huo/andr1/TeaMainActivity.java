package huo.andr1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import FragClass.TmFrag;
import FragClass.TmanageFrag;
import FragClass.TAllFrag;
import FragClass.TPutFrag;

public class TeaMainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout tmLayout;
    private LinearLayout tmanageLayout;
    private LinearLayout tallLayout;
    private LinearLayout tputLayout;

    private TextView tmView;
    private TextView tmanageView;
    private TextView tallView;
    private TextView tputView;

    private Fragment tmFrag;
    private Fragment tmanageFrag;
    private Fragment tallFrag;
    private Fragment tputFrag;

    private Drawable p1;
    private Drawable p2;
    private Drawable p3;
    private Drawable p4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_main);

        initView();
        initEvent();
        Intent intent = getIntent();
        initFragment(Integer.parseInt(intent.getStringExtra("Fragment")));
    }


    public void initFragment(int i) {
        restart();
        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction transaction = fManager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                tmView.setTextColor(Color.BLACK);
                p1 = getResources().getDrawable(R.mipmap.mon,null);
                p1.setBounds(0,0,p1.getIntrinsicWidth(),p1.getIntrinsicHeight());
                tmView.setCompoundDrawables(null,p1,null,null);
                if (tmFrag == null) {
                    tmFrag = new TmFrag();
                    transaction.add(R.id.Tmain_frag, tmFrag);
                }
                else {
                    transaction.show(tmFrag);
                }
                break;
            case 1:
                tmanageView.setTextColor(Color.BLACK);
                p2 = getResources().getDrawable(R.mipmap.manageon,null);
                p2.setBounds(0,0,p2.getIntrinsicWidth(),p2.getIntrinsicHeight());
                tmanageView.setCompoundDrawables(null,p2,null,null);
                if (tmanageFrag == null) {
                    tmanageFrag = new TmanageFrag();
                    transaction.add(R.id.Tmain_frag, tmanageFrag);
                }
                else {
                    transaction.show(tmanageFrag);
                }
                break;
            case 2:
                tallView.setTextColor(Color.BLACK);
                p3 = getResources().getDrawable(R.mipmap.allon,null);
                p3.setBounds(0,0,p3.getIntrinsicWidth(),p3.getIntrinsicHeight());
                tallView.setCompoundDrawables(null,p3,null,null);
                if (tallFrag == null) {
                    tallFrag = new TAllFrag();
                    transaction.add(R.id.Tmain_frag, tallFrag);
                }
                else {
                    transaction.show(tallFrag);
                }
                break;
            case 3:
                tputView.setTextColor(Color.BLACK);
                p4 = getResources().getDrawable(R.mipmap.scoreon,null);
                p4.setBounds(0,0,p4.getIntrinsicWidth(),p4.getIntrinsicHeight());
                tputView.setCompoundDrawables(null,p4,null,null);
                if (tputFrag == null) {
                    tputFrag = new TPutFrag();
                    transaction.add(R.id.Tmain_frag, tputFrag);
                }
                else {
                    transaction.show(tputFrag);
                }
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(tmFrag != null){
            transaction.hide(tmFrag);
        }
        if(tmanageFrag != null){
            transaction.hide(tmanageFrag);
        }
        if(tallFrag != null){
            transaction.hide(tallFrag);
        }
        if(tputFrag != null){
            transaction.hide(tputFrag);
        }
    }

    private void initEvent(){
        tmLayout.setOnClickListener(this);
        tmanageLayout.setOnClickListener(this);
        tallLayout.setOnClickListener(this);
        tputLayout.setOnClickListener(this);
    }

    private void initView() {
        this.tmLayout = findViewById(R.id.TmPage_bt);
        this.tmanageLayout = findViewById(R.id.TmanagePage_bt);
        this.tallLayout = findViewById(R.id.TallPage_bt);
        this.tputLayout = findViewById(R.id.TputPage_bt);

        this.tmView = findViewById(R.id.TmPage_bt_text);
        this.tmanageView = findViewById(R.id.TmanagePage_bt_text);
        this.tallView = findViewById(R.id.TallPage_bt_text);
        this.tputView = findViewById(R.id.TputPage_bt_text);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.TmPage_bt:
                initFragment(0);
                break;
            case R.id.TmanagePage_bt:
                initFragment(1);
                break;
            case R.id.TallPage_bt:
                initFragment(2);
                break;
            case R.id.TputPage_bt:
                initFragment(3);
                break;
            default:
                break;
        }
    }

    private void restart() {
        tmView.setTextColor(Color.rgb(255, 255, 255));
        p1 = getResources().getDrawable(R.mipmap.moff,null);
        p1.setBounds(0,0,p1.getIntrinsicWidth(),p1.getIntrinsicHeight());
        tmView.setCompoundDrawables(null,p1,null,null);

        tmanageView.setTextColor(Color.rgb(255, 255, 255));
        p2 = getResources().getDrawable(R.mipmap.addoff,null);
        p2.setBounds(0,0,p2.getIntrinsicWidth(),p2.getIntrinsicHeight());
        tmanageView.setCompoundDrawables(null,p2,null,null);

        tallView.setTextColor(Color.rgb(255, 255, 255));
        p3 = getResources().getDrawable(R.mipmap.courseoff,null);
        p3.setBounds(0,0,p3.getIntrinsicWidth(),p3.getIntrinsicHeight());
        tallView.setCompoundDrawables(null,p3,null,null);

        tputView.setTextColor(Color.rgb(255, 255, 255));
        p4 = getResources().getDrawable(R.mipmap.scoreoff,null);
        p4.setBounds(0,0,p4.getIntrinsicWidth(),p4.getIntrinsicHeight());
        tputView.setCompoundDrawables(null,p4,null,null);
    }
}
