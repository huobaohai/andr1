package huo.andr1;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import FragClass.CLFragment;
import FragClass.COUFragment;
import FragClass.MFragment;
import FragClass.SCFragment;

public class mainPage extends FragmentActivity implements View.OnClickListener{

    private LinearLayout mLayout;
    private LinearLayout clLayout;
    private LinearLayout couLayout;
    private LinearLayout scLayout;

    private TextView mView;
    private TextView clView;
    private TextView couView;
    private TextView scView;

    private Fragment mFragment;
    private Fragment clFragment;
    private Fragment couFragment;
    private Fragment scFragment;

    private Drawable p1;
    private Drawable p2;
    private Drawable p3;
    private Drawable p4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_layout);

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
                mView.setTextColor(Color.BLACK);
                p1 = getResources().getDrawable(R.mipmap.mon,null);
                p1.setBounds(0,0,p1.getIntrinsicWidth(),p1.getIntrinsicHeight());
                mView.setCompoundDrawables(null,p1,null,null);
                if (mFragment == null) {
                    mFragment = new MFragment();
                    transaction.add(R.id.main_frag, mFragment);
                }
                else {
                    transaction.show(mFragment);
                }
                break;
            case 1:
                clView.setTextColor(Color.BLACK);
                p2 = getResources().getDrawable(R.mipmap.addon,null);
                p2.setBounds(0,0,p2.getIntrinsicWidth(),p2.getIntrinsicHeight());
                clView.setCompoundDrawables(null,p2,null,null);
                if (clFragment == null) {
                    clFragment = new CLFragment();
                    transaction.add(R.id.main_frag, clFragment);
                }
                else {
                    transaction.show(clFragment);
                }
                break;
            case 2:
                couView.setTextColor(Color.BLACK);
                p3 = getResources().getDrawable(R.mipmap.courseon,null);
                p3.setBounds(0,0,p3.getIntrinsicWidth(),p3.getIntrinsicHeight());
                couView.setCompoundDrawables(null,p3,null,null);
                if (couFragment == null) {
                    couFragment = new COUFragment();
                    transaction.add(R.id.main_frag, couFragment);
                }
                else {
                    transaction.show(couFragment);
                }
                break;
            case 3:
                scView.setTextColor(Color.BLACK);
                p4 = getResources().getDrawable(R.mipmap.scoreon,null);
                p4.setBounds(0,0,p4.getIntrinsicWidth(),p4.getIntrinsicHeight());
                scView.setCompoundDrawables(null,p4,null,null);
                if (scFragment == null) {
                    scFragment = new SCFragment();
                    transaction.add(R.id.main_frag, scFragment);
                }
                else {
                    transaction.show(scFragment);
                }
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(mFragment != null){
            transaction.hide(mFragment);
        }
        if(clFragment != null){
            transaction.hide(clFragment);
        }
        if(couFragment != null){
            transaction.hide(couFragment);
        }
        if(scFragment != null){
            transaction.hide(scFragment);
        }
    }

    private void initEvent(){
        mLayout.setOnClickListener(this);
        clLayout.setOnClickListener(this);
        couLayout.setOnClickListener(this);
        scLayout.setOnClickListener(this);
    }

    private void initView() {
        this.mLayout = findViewById(R.id.mPage_bt);
        this.clLayout = findViewById(R.id.clPage_bt);
        this.couLayout = findViewById(R.id.couPage_bt);
        this.scLayout = findViewById(R.id.scPage_bt);

        this.mView = findViewById(R.id.mPage_bt_text);
        this.clView = findViewById(R.id.clPage_bt_text);
        this.couView = findViewById(R.id.couPage_bt_text);
        this.scView = findViewById(R.id.scPage_bt_text);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.mPage_bt:
                initFragment(0);
                break;
            case R.id.clPage_bt:
                initFragment(1);
                break;
            case R.id.couPage_bt:
                initFragment(2);
                break;
            case R.id.scPage_bt:
                initFragment(3);
                break;
            default:
                break;
        }
    }

    private void restart() {
        mView.setTextColor(Color.rgb(255, 255, 255));
        p1 = getResources().getDrawable(R.mipmap.moff,null);
        p1.setBounds(0,0,p1.getIntrinsicWidth(),p1.getIntrinsicHeight());
        mView.setCompoundDrawables(null,p1,null,null);

        clView.setTextColor(Color.rgb(255, 255, 255));
        p2 = getResources().getDrawable(R.mipmap.addoff,null);
        p2.setBounds(0,0,p2.getIntrinsicWidth(),p2.getIntrinsicHeight());
        clView.setCompoundDrawables(null,p2,null,null);

        couView.setTextColor(Color.rgb(255, 255, 255));
        p3 = getResources().getDrawable(R.mipmap.courseoff,null);
        p3.setBounds(0,0,p3.getIntrinsicWidth(),p3.getIntrinsicHeight());
        couView.setCompoundDrawables(null,p3,null,null);

        scView.setTextColor(Color.rgb(255, 255, 255));
        p4 = getResources().getDrawable(R.mipmap.scoreoff,null);
        p4.setBounds(0,0,p4.getIntrinsicWidth(),p4.getIntrinsicHeight());
        scView.setCompoundDrawables(null,p4,null,null);
    }

}
