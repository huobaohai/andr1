package FragClass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import BeanClass.TeacherBean;
import huo.andr1.R;

/**
 * Created by DELL on 2017/11/19.
 */

public class TmFrag extends Fragment {
    TextView oneTV;
    TextView twoTV;
    TextView threeTV;
    TextView fourTV;
    TextView fiveTV;
    TextView sixTV;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.tm_layout, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        TeacherBean teacher = (TeacherBean) getActivity().getIntent().getSerializableExtra("tea");

        oneTV = getActivity().findViewById(R.id.tm_tnoTVWT);
        twoTV = getActivity().findViewById(R.id.tm_tnameTVWT);
        threeTV = getActivity().findViewById(R.id.tm_tsexTVWT);
        fourTV = getActivity().findViewById(R.id.tm_tageTVWT);
        fiveTV = getActivity().findViewById(R.id.tm_tadressTVWT);
        sixTV = getActivity().findViewById(R.id.tm_txueweiTVWT);

        oneTV.setText(teacher.getTno());
        twoTV.setText(teacher.getTname());
        threeTV.setText(String.valueOf(teacher.getTsex()));
        fourTV.setText(String.valueOf(teacher.getTage()));
        fiveTV.setText(teacher.getTadress());
        sixTV.setText(teacher.getTxuewei());
    }}
