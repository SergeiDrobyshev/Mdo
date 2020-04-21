package by.belgosles.sergei.mdo.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.adapters.DictSpinnerAdapter;
import by.belgosles.sergei.mdo.model.DictName;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.Fund;

public class StatementMdoFragment extends Fragment {

    @BindView(R.id.spinner_vidpolz) Spinner spin_type_use;
    @BindView(R.id.spinner_forest_category) Spinner spin_category_for;
    @BindView(R.id.spinner_hoz_section) Spinner spin_hoz_section;
    @BindView(R.id.spinner_species_group) Spinner spin_species_group;
    @BindView(R.id.spinner_type_cutting) Spinner spin_type_cut;
    @BindView(R.id.spinner_accounting_method) Spinner spin_acc_meth;
    @BindView(R.id.spinner_cleaning_method) Spinner spin_clean_meth;
    @BindView(R.id.spinner_recovery_method) Spinner spin_recovery_meth;
    @BindView(R.id.spinner_bonitet) Spinner spin_bonitet;
    @BindView(R.id.spinner_forest_type) Spinner spin_type_for;
    @BindView(R.id.spinner_soil) Spinner spin_soil;
    @BindView(R.id.spinner_stand_condition) Spinner spin_stand_cond;

    @BindView(R.id.editText_year_forest_fund) EditText year_forest_fund;
    @BindView(R.id.editText_year_otvod) EditText year_otvod;
    @BindView(R.id.editText_forest_quarter) EditText kvart_n;
    @BindView(R.id.editText_number_lesosek) EditText lesosek_n;
    @BindView(R.id.editText_number_videl) EditText tax_vydel_n;
    @BindView(R.id.editText_square) EditText square;
    @BindView(R.id.editText_sostav) EditText sostav;
    @BindView(R.id.editText_cover)EditText cover;
    @BindView(R.id.editText_age) EditText age;
    @BindView(R.id.editText_fullness) EditText fullness;
    @BindView(R.id.editText_sr_height) EditText sr_height;
    @BindView(R.id.editText_sr_diameter) EditText sr_diameter;
    @BindView(R.id.editText_note) EditText note;

    private AppDb db;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ID_Fund = "id_fund";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private long id_fund;
    private String mParam2;

    public StatementMdoFragment() {
        // Required empty public constructor
    }


    public static StatementMdoFragment newInstance(long id_fund) {
        StatementMdoFragment fragment = new StatementMdoFragment();
        Bundle args = new Bundle();
        args.putLong(ID_Fund, id_fund);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            id_fund = getArguments().getLong(ID_Fund);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db =  App.getInstance().getDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_cut_area, container, false);
        ButterKnife.bind(this, view);
        setViewAdapters();

        return view;
    }

    private void setViewAdapters() {
        Fund fund = db.getstatementDao().getFundById(id_fund);
        //вид пользования
        ArrayList<DictName> list_kind_use = (ArrayList<DictName>) db.getDictsDao().getAllKindUse();
        DictSpinnerAdapter adapter_type_use = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_kind_use);
        spin_type_use.setAdapter(adapter_type_use);
        if(getPosition(list_kind_use, fund.getId_type_use())!= 0){
            spin_type_use.setSelection(getPosition(list_kind_use, fund.getId_type_use()));
        }
        //категория леса
        ArrayList<DictName> list_group_for = (ArrayList<DictName>) db.getDictsDao().getAllGroupForest();
        DictSpinnerAdapter adapter_category_forest = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_group_for);
        spin_category_for.setAdapter(adapter_category_forest);
        if(getPosition(list_group_for, fund.getId_categ()) != 0 ){
            spin_category_for.setSelection(getPosition(list_group_for, fund.getId_categ()));
        }
        //год лес.фонда
        if(fund.getYear_fund() != null && !fund.getYear_fund().isEmpty()){
            year_forest_fund.setText(fund.getYear_fund());
        }
        //год отвода
        if(fund.getYear_allot() != null &&!fund.getYear_allot().isEmpty()){
            year_otvod.setText(fund.getYear_allot());
        }
        //номер лесного квартала
        if(fund.getKvart_n() != null && !fund.getKvart_n().isEmpty()){
            kvart_n.setText(fund.getKvart_n());
        }
        //номер лесосеки
        if(fund.getCut_area_n() != null && !fund.getCut_area_n().isEmpty()){
            lesosek_n.setText(fund.getCut_area_n());
        }
        //номер таксац.выдела
        if(fund.getTax_vydel_n() != null && !fund.getTax_vydel_n().isEmpty()){
            tax_vydel_n.setText(fund.getTax_vydel_n());
        }
        //площадь экспл
        if(fund.getSquare() != null && !fund.getSquare().isEmpty()){
            square.setText(fund.getSquare());
        }
        //хозсекция
        ArrayList<DictName> list_hoz_section = (ArrayList<DictName>) db.getDictsDao().getAllSections();
        DictSpinnerAdapter adapter_hoz_section = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_hoz_section);
        spin_hoz_section.setAdapter(adapter_hoz_section);
        if (getPosition(list_hoz_section,fund.getId_hoz_section()) != 0){
            spin_hoz_section.setSelection(getPosition(list_hoz_section,fund.getId_hoz_section()));
        }
        //группа пород
        ArrayList<DictName> list_species_group = (ArrayList<DictName>) db.getDictsDao().getAllSpeciesGroup();
        DictSpinnerAdapter adapter_species_group = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_species_group);
        spin_species_group.setAdapter(adapter_species_group);
        if (getPosition(list_species_group, fund.getId_group_species()) != 0){
            spin_species_group.setSelection(getPosition(list_species_group, fund.getId_group_species()));
        }
        //вид рубки
        ArrayList<DictName> list_type_cut = (ArrayList<DictName>) db.getDictsDao().getAllCutTypes();
        DictSpinnerAdapter adapter_type_cut = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_type_cut);
        spin_type_cut.setAdapter(adapter_type_cut);
        if(getPosition(list_type_cut,fund.getId_type_cut()) != 0 ){
            spin_type_cut.setSelection(getPosition(list_type_cut,fund.getId_type_cut()));
        }
        //метод учета
        ArrayList<DictName> list_acc_meth = (ArrayList<DictName>) db.getDictsDao().getAllAccMethods();
        DictSpinnerAdapter adapter_acc_meth = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_acc_meth);
        spin_acc_meth.setAdapter(adapter_acc_meth);
        if(getPosition(list_acc_meth, fund.getId_account_method()) != 0){
            spin_acc_meth.setSelection(getPosition(list_acc_meth, fund.getId_account_method()));
        }
        //способ очистки
        ArrayList<DictName> list_clean_meth = (ArrayList<DictName>) db.getDictsDao().getAllCleanMethods();
        DictSpinnerAdapter adaper_clean_meth = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_clean_meth);
        spin_clean_meth.setAdapter(adaper_clean_meth);
        if(getPosition(list_clean_meth, fund.getId_clean_method()) != 0){
            spin_clean_meth.setSelection(getPosition(list_clean_meth, fund.getId_clean_method()));
        }
        //способ восстановления
        ArrayList<DictName> list_recovery_meth = (ArrayList<DictName>) db.getDictsDao().getAllRecovMeths();
        DictSpinnerAdapter adapter_recovery_meth = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_recovery_meth);
        spin_recovery_meth.setAdapter(adapter_recovery_meth);
        if(getPosition(list_recovery_meth,fund.getId_recovery_method()) != 0){
            spin_recovery_meth.setSelection(getPosition(list_recovery_meth,fund.getId_recovery_method()));
        }
        //состав
        if(fund.getSostav() != null && !fund.getSostav().isEmpty()){
            sostav.setText(fund.getSostav());
        }
        //бонитет
        ArrayList<DictName> list_bonitet = (ArrayList<DictName>) db.getDictsDao().getAllBonitets();
        DictSpinnerAdapter adapter_bonitet = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_bonitet);
        spin_bonitet.setAdapter(adapter_bonitet);
        if(getPosition(list_bonitet, fund.getId_bonitet()) != 0){
            spin_bonitet.setSelection(getPosition(list_bonitet, fund.getId_bonitet()));
        }
        //тип леса
        ArrayList<DictName> list_type_for = (ArrayList<DictName>) db.getDictsDao().getAllTypeFor();
        DictSpinnerAdapter adapter_type_for = new DictSpinnerAdapter(getContext(),R.layout.spinner_title, list_type_for);
        spin_type_for.setAdapter(adapter_type_for);
        if(getPosition(list_type_for, fund.getId_forest_type()) != 0){
            spin_type_for.setSelection(getPosition(list_type_for, fund.getId_forest_type()));
        }
        //покров
        if(fund.getForest_cover() != null && !fund.getForest_cover().isEmpty()){
            cover.setText(fund.getForest_cover());
        }
        //почвы
        ArrayList<DictName> list_soil = (ArrayList<DictName>) db.getDictsDao().getAllSoils();
        DictSpinnerAdapter adapter_soil = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_soil);
        spin_soil.setAdapter(adapter_soil);
        if(getPosition(list_soil,fund.getId_soil()) != 0){
            spin_soil.setSelection(getPosition(list_soil,fund.getId_soil()));
        }
        //состояние насаждений
        ArrayList<DictName> list_stand_cond = (ArrayList<DictName>) db.getDictsDao().getAllStatus();
        DictSpinnerAdapter adapter_stand_cond = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, list_stand_cond);
        spin_stand_cond.setAdapter(adapter_stand_cond);
        if(getPosition(list_stand_cond,fund.getId_plaint_state()) != 0){
            spin_stand_cond.setSelection(getPosition(list_stand_cond,fund.getId_plaint_state()));
        }
        //возраст
        if(fund.getAge() != null && !fund.getAge().isEmpty()){
            age.setText(fund.getAge());
        }
        //полнота
        if (fund.getFullness() != null && !fund.getFullness().isEmpty()){
            fullness.setText(fund.getFullness());
        }
        //средняя высота
        if (fund.getAve_height() != null && !fund.getAve_height().isEmpty()){
            sr_height.setText(fund.getAve_height());
        }
        //средний диаметр
        if(fund.getAve_diameter() != null && !fund.getAve_diameter().isEmpty()){
            sr_diameter.setText(fund.getAve_diameter());
        }
        //примечание
        if(fund.getComment() != null && !fund.getComment().isEmpty()){
            note.setText(fund.getComment());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        db.close();
    }

    //сохранение в БД установленной во Views информации
    public void saveMDOValues(long id_fund){
        int id_type_use = (Integer) spin_type_use.getSelectedView().getTag();
        int id_category_for = (Integer) spin_category_for.getSelectedView().getTag();
        String yearForestFund = getInputtedText(year_forest_fund);
        String yeatOtvod = getInputtedText(year_otvod);

        String kvartN = getInputtedText(kvart_n);
        String cut_area_n = getInputtedText(lesosek_n);
        String taxVydelN = getInputtedText(tax_vydel_n);
        String squareValue = getInputtedText(square);

        int id_hoz_section = (Integer) spin_hoz_section.getSelectedView().getTag();
        int id_species_group = (Integer) spin_species_group.getSelectedView().getTag();
        int id_type_cut = (Integer) spin_type_cut.getSelectedView().getTag();
        int id_acc_meth = (Integer) spin_acc_meth.getSelectedView().getTag();
        int id_clean_meth = (Integer) spin_clean_meth.getSelectedView().getTag();
        int id_recovery_meth = (Integer) spin_recovery_meth.getSelectedView().getTag();

        String sostavValue = getInputtedText(sostav);
        int id_bonitet = (Integer) spin_bonitet.getSelectedView().getTag();
        int id_type_forest = (Integer) spin_type_for.getSelectedView().getTag();
        String coverValue = getInputtedText(cover);
        int id_soil = (Integer) spin_soil.getSelectedView().getTag();
        int id_stand_cond = (Integer) spin_stand_cond.getSelectedView().getTag();
        String ageValue = getInputtedText(age);

        String fullnessValue = getInputtedText(fullness);
        String sr_heightValue = getInputtedText(sr_height);
        String sr_diameterValue = getInputtedText(sr_diameter);
        String noteValue = getInputtedText(note);

        db.getstatementDao().updateFromStatementMdoFragment(id_type_use, id_category_for, yearForestFund, yeatOtvod,
                kvartN, cut_area_n, taxVydelN, squareValue,
                id_hoz_section, id_species_group, id_type_cut, id_acc_meth, id_clean_meth, id_recovery_meth,
                sostavValue, id_bonitet, id_type_forest, coverValue, id_soil, id_stand_cond, ageValue,
                fullnessValue, sr_heightValue, sr_diameterValue, noteValue, id_fund);
    }

    private String getInputtedText(EditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString();
        }
        return "";
    }

    private int getPosition(ArrayList<DictName> list, int id){
        int i = 0;
        for (DictName row: list) {
            if(row.getId()== id){
                return i;
            }
            i++;
        }
        return  0;
    }

}
