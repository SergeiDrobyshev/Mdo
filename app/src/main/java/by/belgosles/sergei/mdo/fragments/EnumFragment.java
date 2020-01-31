package by.belgosles.sergei.mdo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.adapters.DictSpinnerAdapter;
import by.belgosles.sergei.mdo.adapters.PerechetRecyclerViewAdapter;
import by.belgosles.sergei.mdo.model.DictName;
import by.belgosles.sergei.mdo.model.entity.AppDb;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class EnumFragment extends Fragment {
    @BindView(R.id.spinner_poroda_value) Spinner spin_allSpecies;
    @BindView(R.id.recyclerViewPerechet) RecyclerView rvPerechet;
    @BindView(R.id.editText_amount_of_whip) EditText ed_whip;
    @BindView(R.id.constraint_general) ConstraintLayout constraintLayout_general;
    @BindView(R.id.radioGroup_selected_species) RadioGroup radioGroupSelectedSpecies;

    private AppDb db;
    private ArrayList<String> listAddedSpecies = new ArrayList<>();
    private int [] diameters = new int[30];
    private ArrayList<DictName> saveSpeciesList = new ArrayList<>();
    private static final String ID_FUND = "param1";
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private long id_fund;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EnumFragment() {
        // Required empty public constructor
    }

    public static EnumFragment newInstance(long id_fund) {
        EnumFragment fragment = new EnumFragment();
        Bundle args = new Bundle();
        args.putLong(ID_FUND, id_fund);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            id_fund = getArguments().getLong(ID_FUND);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = App.getInstance().getDatabase();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_enum_add_species, container,false);
        ButterKnife.bind(this,view);
        setViewAdapters();
        if(savedInstanceState != null){
            if (savedInstanceState.containsKey("speciesButtons")) {
                ArrayList<DictName> list = (ArrayList<DictName>) savedInstanceState.getSerializable("speciesButtons") ;
                if (list != null) {
                    restoreButtonsSpecies(list);
                }
            }
        }
        radioGroupSelectedSpecies.setOnCheckedChangeListener((radioGroup, i) -> {
            int id_checkedSpecies = radioGroup.getCheckedRadioButtonId();


        });
        return view;
    }

    private void setViewAdapters() {
        ArrayList<DictName> list_allSpecies = (ArrayList<DictName>) db.getDictsDao().getAllSpecies();
        DictSpinnerAdapter adapter_species = new DictSpinnerAdapter(getContext(), R.layout.spinner_dropdown_item, list_allSpecies);
        spin_allSpecies.setAdapter(adapter_species);

        int k = 8;
        for (int i = 0; i < 30; i++ ){
            diameters [i] = k;
            k = k + 4;
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPerechet.setLayoutManager(layoutManager);
        PerechetRecyclerViewAdapter adapter = new PerechetRecyclerViewAdapter(diameters, getContext());
        rvPerechet.setAdapter(adapter);
        rvPerechet.setItemViewCacheSize(diameters.length);
    }

    public void saveValues(long id_fund){
        String whip = getInputtedText(ed_whip);
    }

    @OnItemSelected(R.id.spinner_poroda_value)
    public void onClick(){
        DictName selectedDictName = (DictName) spin_allSpecies.getSelectedItem();
        String selectedItemValue = selectedDictName.getValue();
        int id_species = selectedDictName.getId();

        //todo change orientation, remove onclick
        if(listAddedSpecies.contains(selectedItemValue)){
            Toast toast = Toast.makeText(getContext(),"Выбранная порода уже добавлена!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else {
            listAddedSpecies.add(selectedItemValue);
            addNewButton(selectedItemValue, id_species);
            saveSpeciesList.add(selectedDictName);
            //listAddedSpeciesAdapter.notifyDataSetChanged();
        }
    }

    private void addNewButton(String selectedItemValue, long id_species) {
        RadioButton rb = new RadioButton(getContext());
        rb.setText(selectedItemValue);
        //rb.setTag(id_species);
        rb.setTextSize(13);
        rb.setLayoutParams(new LinearLayout .LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        //todo version sdk
        rb.setId((int) id_species);
        radioGroupSelectedSpecies.addView(rb);


           /* Log.e("onClickButtonSpecies", rb.getText().toString());
            clickButton(id_species);*/
    }

    private void clickButton(long id_species) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        db.close();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("speciesButtons", saveSpeciesList);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private String getInputtedText(EditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString();
        }
        return "";
    }

    private void restoreButtonsSpecies(ArrayList<DictName> list){
        for (DictName species: list) {
            addNewButton(species.getValue(), species.getId());
        }
    }
}
