package by.belgosles.sergei.mdo.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.activity.CreateStatementActivity;
import by.belgosles.sergei.mdo.adapters.DictSpinnerAdapter;
import by.belgosles.sergei.mdo.model.DictName;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.FundEnum;
import by.belgosles.sergei.mdo.model.entity.Growth;

public class UndegrowthPollutionFragment extends Fragment {
    @BindView(R.id.spinner_poroda_value1)
    Spinner spin_poroda;
    @BindView(R.id.editText_amount_thousand)
    EditText edAmount;
    @BindView(R.id.editText_preservation_area)
    EditText edPreserveArea;
    @BindView(R.id.editText_structure)
    EditText edSostav;
    @BindView(R.id.editText_report_number)
    EditText edReportNumber;
    @BindView(R.id.editText_report_date)
    EditText edReportDate;
    @BindView(R.id.editText_radiation_pollution_density)
    EditText edRadDensity;
    @BindView(R.id.editText_specific_activity_del)
    EditText edSpecActivityDel;
    @BindView(R.id.editText_specific_activity_drov)
    EditText edSpecActivityDrov;

    private AppDb db;
    private long id_fund;
    private static final String ID_FUND = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UndegrowthPollutionFragment() {
        // Required empty public constructor
    }

    public static UndegrowthPollutionFragment newInstance(long id_fund) {
        UndegrowthPollutionFragment fragment = new UndegrowthPollutionFragment();
        Bundle args = new Bundle();
        args.putLong(ID_FUND, id_fund);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_fund = getArguments().getLong(ID_FUND);
        }
        db = App.getInstance().getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_undegrowth_pollut, container, false);
        ButterKnife.bind(this, view);
        setViewAdapters();
        getDataFromDb(id_fund);
        return view;
    }

    private void setViewAdapters() {
        ArrayList<DictName> listAllSpecies = (ArrayList<DictName>) db.getDictsDao().getAllSpecies();
        DictSpinnerAdapter adapterSpecies = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, listAllSpecies);

        spin_poroda.setAdapter(adapterSpecies);
    }

    private void getDataFromDb(long id_fund) {
        /*ArrayList<Growth> listGrowth = (ArrayList<FundEnum>) db.getstatementDao().getGrowth(id_fund);
        if(!listGrowth.isEmpty()){
            for (Growth growth:listGrowth) {
                
            }
        }*/

    }

    public void saveUnderGrowthValues(long id_fund) {
        long idGrowth = db.getstatementDao().getGrowthByFundId(id_fund);


            Growth growth = new Growth();
            growth.setId_species((int) spin_poroda.getSelectedView().getTag());
            growth.setAmount(CreateStatementActivity.getInputtedText(edAmount));
            growth.setSquare_preserved(CreateStatementActivity.getInputtedText(edPreserveArea));
            growth.setSostav(CreateStatementActivity.getInputtedText(edSostav));

            growth.setAct_rad_n(CreateStatementActivity.getInputtedText(edReportNumber));
            growth.setAct_rad_date(CreateStatementActivity.getInputtedText(edReportDate));
            growth.setSoil_rad_density(CreateStatementActivity.getInputtedText(edRadDensity));
            growth.setSpec_activ_del(CreateStatementActivity.getInputtedText(edSpecActivityDel));
            growth.setSpec_activ_drov(CreateStatementActivity.getInputtedText(edSpecActivityDrov));
            growth.setId_fund(id_fund);// id текущей ведомости
            growth.setId_growth(idGrowth);

           // db.getstatementDao().updateGrowth(growth);// обновляем по id

            //db.getstatementDao().insertGrowth(growth);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
