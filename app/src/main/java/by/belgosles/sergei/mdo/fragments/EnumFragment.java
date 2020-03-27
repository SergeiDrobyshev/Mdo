package by.belgosles.sergei.mdo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.DiamDelDrov;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.adapters.DictSpinnerAdapter;
import by.belgosles.sergei.mdo.model.DictName;
import by.belgosles.sergei.mdo.model.entity.AppDb;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class EnumFragment extends Fragment {
    @BindView(R.id.spinner_poroda_value)
    Spinner spin_allSpecies;
    @BindView(R.id.editText_amount_of_whip)
    EditText ed_whip;
    @BindView(R.id.constraint_general)
    ConstraintLayout constraintLayout_general;
    @BindView(R.id.radioGroup_selected_species)
    RadioGroup radioGroupSelectedSpecies;
    @BindView(R.id.addDiameterDelDrovRow)
    Button addNewDiamRow;
    @BindView(R.id.linearLayout_add_diam)
    LinearLayout layoutAddDiamRows;

    private AppDb db;
    private Map<Integer, List<DiamDelDrov>> mapEnumSpecies = new HashMap<>();
    private ArrayList<String> listAddedSpecies = new ArrayList<>();// todo убрать, а поиск добавленных кнопок сделать в map
    private ArrayList<DictName> saveSpeciesList = new ArrayList<>();// todo убрать. сохранять общий map
    private List<Integer> listDialogCheckedDiameters = new ArrayList<>();
    private List<Integer> listLayoutAddedDiameters = new ArrayList<>();
    private int prevIdCheckedSpecies = 0;
    private static final String ID_FUND = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LayoutInflater inflater;
    private int[] diameters;

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
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = getLayoutInflater();
        //setRetainInstance(true);
        if (getArguments() != null) {
            id_fund = getArguments().getLong(ID_FUND);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        db = App.getInstance().getDatabase();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enum_add_species, container, false);
        ButterKnife.bind(this, view);
        setViewAdapters();
        diameters = getResources().getIntArray(R.array.refs_diameters);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("speciesButtons")) {
                ArrayList<DictName> list = (ArrayList<DictName>) savedInstanceState.getSerializable("speciesButtons");
                if (list != null) {
                    //restoreButtonsSpecies(list);
                }
            }
        }
        radioGroupSelectedSpecies.setOnCheckedChangeListener((radioGroup, i) -> {
            updateLayoutAddDiamRows(radioGroup.getCheckedRadioButtonId());
        });

        return view;
    }

    private void setViewAdapters() {
        ArrayList<DictName> list_allSpecies = (ArrayList<DictName>) db.getDictsDao().getAllSpecies();
        DictSpinnerAdapter adapter_species = new DictSpinnerAdapter(getContext(), R.layout.spinner_dropdown_item, list_allSpecies);
        spin_allSpecies.setAdapter(adapter_species);

    }

    private void updateLayoutAddDiamRows(int idRadioButton){
        layoutAddDiamRows.removeAllViews();
        List<DiamDelDrov> list = mapEnumSpecies.get(idRadioButton);
        addDiamRows(list);

        ConstraintLayout row = null;
        if(list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                row = (ConstraintLayout) inflater.inflate(R.layout.row_diam_del_drov, layoutAddDiamRows, false);
                TextView tvDiameterNumber = row.findViewById(R.id.textView_diameter_number);
                EditText edDelAmount = row.findViewById(R.id.editText_del);
                EditText edDrovAmount = row.findViewById(R.id.editText_drov);
                layoutAddDiamRows.addView(row);
                tvDiameterNumber.setText(String.valueOf(list.get(i).getDiameter()));
                edDelAmount.setText(list.get(i).getDelAmount());
                edDrovAmount.setText(list.get(i).getDrovAmount());
            }
        }
    }

    public void saveValues(long id_fund) {
        String whip = getInputtedText(ed_whip);

    }

    @OnItemSelected(R.id.spinner_poroda_value)
    public void onClick() {
        DictName selectedDictName = (DictName) spin_allSpecies.getSelectedItem();
        String selectedItemValue = selectedDictName.getValue();
        int id_species = selectedDictName.getId();

        //todo change orientation, remove onclick
        if (mapEnumSpecies.containsKey(id_species)) {
            Toast toast = Toast.makeText(getContext(), "Выбранная порода уже добавлена!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            addNewRadioButton(selectedItemValue, id_species);
            saveSpeciesList.add(selectedDictName);
        }
    }

    @OnClick(R.id.addDiameterDelDrovRow)
    public void onClickAddDiameterButton() {
        if(radioGroupSelectedSpecies.getCheckedRadioButtonId() != -1){
            AlertDialog dialog = dialogAddDiam();
            dialog.show();
        }else {
            // если не добавлено и не выбрано ни одной породы
            Toast toast = Toast.makeText(getContext(), "Добавьте и выберите породу!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private AlertDialog dialogAddDiam() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.addDiameterRow)
                /*.setMultiChoiceItems(diameters, null, ((dialogInterface, i, b) -> {

                }) )*/

                    // выбранные items
                    //todo если ни оди раз выбрать и отменить выбор
                    /*if (isChecked) {
                        if(listDialogCheckedDiameters.contains(i)){
                            listDialogCheckedDiameters.remove(Integer.valueOf(i));
                        } else{
                            listDialogCheckedDiameters.add(i);
                        }
                    }*/
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    if(!listDialogCheckedDiameters.isEmpty()) {
                        List<DiamDelDrov> listDiamRows = new ArrayList<>();
                        for(int k = 0; k < listDialogCheckedDiameters.size(); k++) {//
                            listDiamRows.get(i).setDiameter(diameters[listDialogCheckedDiameters.get(i)]);
                        }
                        addDiamRows(listDiamRows);
                    }
                    listDialogCheckedDiameters.clear();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                    listDialogCheckedDiameters.clear();
                });
        return builder.create();
    }
    
    private void addDiamRows(List <DiamDelDrov> listDiamRows){
        ConstraintLayout row = null;
        int id_rb = radioGroupSelectedSpecies.getCheckedRadioButtonId();
        List<DiamDelDrov> listRows = mapEnumSpecies.get(id_rb); //список с диаметрами и значениями из Hashmap, для выбранной породы
        for (DiamDelDrov i:listDiamRows) {
            if(listLayoutAddedDiameters.contains(i)){
                continue; // если уже добавлены на layout выбранные в AlertDialog диаметры, их пропускать
            }
            int diameter = i.getDiameter()*4+8;
            row = (ConstraintLayout) inflater.inflate(R.layout.row_diam_del_drov, layoutAddDiamRows, false);
            TextView tvDiameterNumber = row.findViewById(R.id.textView_diameter_number);
            tvDiameterNumber.setId(diameter);
            tvDiameterNumber.setText(String.valueOf(diameter));
            EditText edDelAmount = row.findViewById(R.id.editText_del);
            EditText edDrovAmount = row.findViewById(R.id.editText_drov);
            setEventforAmount(tvDiameterNumber, edDelAmount, edDrovAmount, listRows);

            layoutAddDiamRows.addView(row);
            listLayoutAddedDiameters.add(i.getDiameter());
        }
        mapEnumSpecies.put(id_rb, listRows);

        /*ConstraintLayout row = null;
        int id_rb = radioGroupSelectedSpecies.getCheckedRadioButtonId();
        List<DiamDelDrov> listRows = mapEnumSpecies.get(id_rb); //список с диаметрами и значениями из Hashmap, для выбранной породы
        for (Integer i:listDiamPosition) {
            if(listLayoutAddedDiameters.contains(i)){
                continue; // если уже добавлены на layout выбранные в AlertDialog диаметры, их пропускать
            }
            int diameter = i*4+8;
            row = (ConstraintLayout) inflater.inflate(R.layout.row_diam_del_drov, layoutAddDiamRows, false);
            TextView tvDiameterNumber = row.findViewById(R.id.textView_diameter_number);
            tvDiameterNumber.setId(diameter);
            tvDiameterNumber.setText(String.valueOf(diameter));
            EditText edDelAmount = row.findViewById(R.id.editText_del);
            EditText edDrovAmount = row.findViewById(R.id.editText_drov);
            setEventforAmount(tvDiameterNumber, edDelAmount, edDrovAmount, listRows);

            layoutAddDiamRows.addView(row);
            listLayoutAddedDiameters.add(i);
        }
        mapEnumSpecies.put(id_rb, listRows);// добавление списка диаметров в HashMap, для выбранной породы
    */}

    private void sortDiamRows(){}

    // слушатель на EditTexts , и сохрание значений в список list
    private void setEventforAmount(TextView tv, EditText del, EditText drov, List<DiamDelDrov> list){
        DiamDelDrov diamDelDrov = new DiamDelDrov();
        diamDelDrov.setDiameter(tv.getId());
        list.add(diamDelDrov);
        del.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                diamDelDrov.setDelAmount(charSequence.toString());
                list.set(list.indexOf(diamDelDrov), diamDelDrov);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        drov.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                diamDelDrov.setDrovAmount(charSequence.toString());
                list.set(list.indexOf(diamDelDrov), diamDelDrov);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void addNewRadioButton(String selectedItemValue, long id_species) {
        RadioButton rb = new RadioButton(getContext());
        rb.setText(selectedItemValue);
        rb.setTextSize(13);
        rb.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        //todo version sdk
        rb.setId((int) id_species);
        radioGroupSelectedSpecies.addView(rb);
        List<DiamDelDrov> listRows = new ArrayList<>();
        mapEnumSpecies.put((int) id_species, listRows);
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

    private void restoreButtonsSpecies(ArrayList<DictName> list) {
        for (DictName species : list) {
            addNewRadioButton(species.getValue(), species.getId());
        }
    }

}
