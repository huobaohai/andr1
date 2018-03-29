package FragClass;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import huo.andr1.R;
import BeanClass.*;

public class MFragment extends Fragment {

    TextView oneTV;
    TextView twoTV;
    TextView threeTV;
    TextView fourTV;
    TextView fiveTV;
    TextView sixTV;
    TextView sevenTV;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.mpage_layout, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        StudentBean stu = (StudentBean) getActivity().getIntent().getSerializableExtra("stu");

        oneTV = getActivity().findViewById(R.id.m_snoTVWT);
        twoTV = getActivity().findViewById(R.id.m_nameTVWT);
        threeTV = getActivity().findViewById(R.id.m_sexTVWT);
        fourTV = getActivity().findViewById(R.id.m_bjTVWT);
        fiveTV = getActivity().findViewById(R.id.m_ageTVWT);
        sixTV = getActivity().findViewById(R.id.m_deptTVWT);
        sevenTV = getActivity().findViewById(R.id.m_adressTVWT);

        oneTV.setText(stu.getSno());
        twoTV.setText(stu.getSname());
        threeTV.setText(String.valueOf(stu.getSsex()));
        fourTV.setText(String.valueOf(stu.getBanjiId()));
        fiveTV.setText(String.valueOf(stu.getSage()));
        sixTV.setText(stu.getSdept());
        sevenTV.setText(stu.getSadress());
    }
}
