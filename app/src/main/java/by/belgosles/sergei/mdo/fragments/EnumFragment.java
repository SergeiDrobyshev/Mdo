package by.belgosles.sergei.mdo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
    @BindView(R.id.new_layout_enum) Flow layout_enum;
    @BindView(R.id.editText_amount_of_whip) EditText ed_whip;
    @BindView(R.id.constraint_general) ConstraintLayout constraintLayout_general;

    private AppDb db;
    private ArrayList<String> listAddedSpecies = new ArrayList<>();
    private int [] diameters = new int[30];
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ID_FUND = "param1";
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

    private void saveValues(){
        String whip = String.valueOf(ed_whip.getText());
    }

    @OnItemSelected(R.id.spinner_poroda_value)
    public void onClick(){
        String selectedItemValue = spin_allSpecies.getSelectedItem().toString();
        int id_species = (Integer) spin_allSpecies.getSelectedView().getTag();

        if(listAddedSpecies.contains(selectedItemValue)){
            Toast toast = Toast.makeText(getContext(),"Выбранная порода уже добавлена!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else {
            listAddedSpecies.add(selectedItemValue);
            addNewButtonToView(selectedItemValue, id_species);
            //listAddedSpeciesAdapter.notifyDataSetChanged();
        }
    }


    private void addNewButtonToView(String selectedItemValue, long id_species) {
        Button btn = new Button(getContext());
        btn.setText(selectedItemValue);
        btn.setTag(id_species);
        btn.setTextSize(10);
        btn.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        //todo version sdk
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            btn.setId(View.generateViewId());
        }
        constraintLayout_general.addView(btn);
        layout_enum.addView(btn);
        btn.setOnClickListener(view -> {

        });
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



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
