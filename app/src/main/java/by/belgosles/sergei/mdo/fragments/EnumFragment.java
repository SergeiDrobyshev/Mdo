package by.belgosles.sergei.mdo.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import androidx.annotation.Nullable;
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
    @BindView(R.id.spinner_trf_height) Spinner spin_trf_height;

    private AppDb db;
    private Map<Integer, List<DiamDelDrov>> mapEnumSpecies = new HashMap<>();
    private ArrayList<DictName> saveSpeciesList = new ArrayList<>();// todo убрать. сохранять общий map
    private List<Integer> listDialogCheckedDiameters = new ArrayList<>();
    private static final String ID_FUND = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LayoutInflater inflater;
    private String [] diameters;

    private long id_fund;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private final int MENU_ITEM_REMOVE = 1;

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
        diameters = getResources().getStringArray(R.array.refs_diameters);
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

    // получение справочной информации из БД
    private void setViewAdapters() {
        ArrayList<DictName> listAllSpecies = (ArrayList<DictName>) db.getDictsDao().getAllSpecies();
        DictSpinnerAdapter adapterSpecies = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, listAllSpecies);
        spin_allSpecies.setAdapter(adapterSpecies);

        ArrayList<DictName> listAllTrfHeights = (ArrayList<DictName>) db.getDictsDao().getAllTrfHeight();
        DictSpinnerAdapter adapterAllTrfHeight = new DictSpinnerAdapter(getContext(), R.layout.spinner_title, listAllTrfHeights);
        spin_trf_height.setAdapter(adapterAllTrfHeight);

    }

    private void addNewRadioButton(String selectedItemValue, long id_species) {
        RadioButton rb = new RadioButton(getContext());
        rb.setText(selectedItemValue);
        rb.setTextSize(12);
        rb.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        //todo version sdk
        rb.setId((int) id_species);
        radioGroupSelectedSpecies.addView(rb);
        //registerForContextMenu(rb);
        rb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setEventOnLongClickRadioButtons(rb);
                return false;
            }
        });
        List<DiamDelDrov> listRows = new ArrayList<>();
        mapEnumSpecies.put((int) id_species, listRows);
    }

    //при смене породы в RadioGroup обновить строки с диаметрами
    private void updateLayoutAddDiamRows(int idRadioButton){
        layoutAddDiamRows.removeAllViews();
        List<DiamDelDrov> list = mapEnumSpecies.get(idRadioButton);
        if (list != null && !list.isEmpty()) {
            addDiamRows(list);
        }
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
            //если не добавлено и не выбрано ни одной породы
            Toast toast = Toast.makeText(getContext(), "Добавьте и выберите породу!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private AlertDialog dialogAddDiam() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.addDiameterRow)
                .setMultiChoiceItems(diameters, null, (dialogInterface, i, isChecked) -> {
                    //сохранение выбранных диаметров, и удаление при снятии флажка выбора
                    if (isChecked) listDialogCheckedDiameters.add(i);
                    else if(listDialogCheckedDiameters.contains(i)) listDialogCheckedDiameters.remove(Integer.valueOf(i));

                })
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    if(!listDialogCheckedDiameters.isEmpty()) {
                        List<DiamDelDrov> listDiamRows = new ArrayList<>();
                        for(int k = 0; k < listDialogCheckedDiameters.size(); k++) {
                            DiamDelDrov d = new DiamDelDrov();
                            d.setDiameter(Integer.parseInt(diameters[listDialogCheckedDiameters.get(k)]));
                            listDiamRows.add(d);
                        }
                        checkDiamDuplication(listDiamRows);
                        addDiamRows(listDiamRows);
                        if(!listDiamRows.isEmpty()) mapEnumSpecies.get(radioGroupSelectedSpecies.getCheckedRadioButtonId()).addAll(listDiamRows);
                    }
                    listDialogCheckedDiameters.clear();
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                    listDialogCheckedDiameters.clear();
                });
        return builder.create();
    }
    // добавление строк с диаметрами
    private void addDiamRows(List <DiamDelDrov> listDiamRows){
        ConstraintLayout row = null;
        for (DiamDelDrov i:listDiamRows) {
            row = (ConstraintLayout) inflater.inflate(R.layout.row_diam_del_drov, layoutAddDiamRows, false);
            TextView tvDiameterNumber = row.findViewById(R.id.textView_diameter_number);
            tvDiameterNumber.setText(String.valueOf(i.getDiameter()));
            EditText edDelAmount = row.findViewById(R.id.editText_del);
            edDelAmount.setText(i.getDelAmount());
            EditText edDrovAmount = row.findViewById(R.id.editText_drov);
            edDrovAmount.setText(i.getDrovAmount());

            setEventforAmount(tvDiameterNumber, edDelAmount, edDrovAmount, i);
            layoutAddDiamRows.addView(row);

            ConstraintLayout finalRow = row;
            tvDiameterNumber.setOnLongClickListener(view -> {
                layoutAddDiamRows.removeView(finalRow);
                listDiamRows.remove(i);
                mapEnumSpecies.get(radioGroupSelectedSpecies.getCheckedRadioButtonId()).remove(i);
                return false;
            });
        }
    }

    // слушатель на EditTexts количества деревьев, и сохрание значений в обьект выбранной строки
    private void setEventforAmount(TextView tv, EditText del, EditText drov, DiamDelDrov row){
        del.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                row.setDelAmount(charSequence.toString());
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
                row.setDrovAmount(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    //сравнение уже добавленных диаметров для породы и выбранных в AlertDialog
    private void checkDiamDuplication(List<DiamDelDrov> listDiamRows){
        List <DiamDelDrov> list = mapEnumSpecies.get(radioGroupSelectedSpecies.getCheckedRadioButtonId());
        if(list != null && !list.isEmpty()){
            for (int j = 0; j < listDiamRows.size(); j++) {
                int newDiam = listDiamRows.get(j).getDiameter();
                for (DiamDelDrov i : list) {
                    if(newDiam == i.getDiameter()){
                        listDiamRows.remove(j);
                        j--;
                    }
                }
            }
        }
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

    public void saveEnumValues(long id_fund) {
        String whip = getInputtedText(ed_whip);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setEventOnLongClickRadioButtons (RadioButton rb){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String mes = getString(R.string.remove) + " " + rb.getText() + "?";
        builder.setMessage(mes)
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    radioGroupSelectedSpecies.removeView(rb);
                    mapEnumSpecies.remove(rb.getId());
                    updateLayoutAddDiamRows(rb.getId());
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
        builder.show();
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
