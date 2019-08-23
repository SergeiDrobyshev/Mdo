package by.belgosles.sergei.mdo;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import by.belgosles.sergei.mdo.adapters.ListTestesAdapter;

import static by.belgosles.sergei.mdo.R.id.spinner_view_of_testes;

public class SpeciesCannotbeCutting extends AppCompatActivity implements View.OnClickListener {

    private  static final int DELETE_LIST_ID = 1;
    public static final String EXTRA_ONCLICKWOOD = "message";
    Button addTestes;
    EditText diametresTestes, amountTestes,  numberTestes;
    Spinner spinner;;
    ListTestesAdapter listTestesAdapter;
    ArrayList<KindOFTestesTrees> kindOFTestesTreesArrayList = new ArrayList<>();
    ListView listViewTestes;

    // номера семенников как записываются, одно или несколько чисел, дробные или нет

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_cannotbe_cutting);

        Intent intent = getIntent();
        String onClickWood = intent.getStringExtra(EXTRA_ONCLICKWOOD); // получение значения выбранной породы дерева из AlertDialog
        TextView textView = findViewById(R.id.textview_onclickSpecies_value);
        textView.setText(onClickWood);

        spinner = findViewById(spinner_view_of_testes);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.view_of_testes, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        addTestes = (Button) findViewById(R.id.add_testes);
        diametresTestes = (EditText) findViewById(R.id.editText_diameter_testes);
        amountTestes = (EditText) findViewById(R.id.editText_amount_testes);
        numberTestes = (EditText) findViewById(R.id.editText_number_testes);

        listViewTestes = (ListView)findViewById(R.id.listview_every_wood_cannot_be_cutting);
        listTestesAdapter = new ListTestesAdapter(this, kindOFTestesTreesArrayList);// создание адаптера
        listViewTestes.setAdapter(listTestesAdapter);

        addTestes.setOnClickListener(this);
        setListViewHeightBasedOnChildren(listViewTestes);
        // прокрутку листвью и экрана
    }

    @Override
    public void onClick(View view) {
            String selectedViewOfTestesItem = spinner.getSelectedItem().toString();
            String diameter = diametresTestes.getText().toString();
            String amount = amountTestes.getText().toString();
            String number = numberTestes.getText().toString();
            kindOFTestesTreesArrayList.add(new KindOFTestesTrees(selectedViewOfTestesItem, diameter, amount, number));// заполнение введенных в editText данных в ArrayList<KindOFTestesTrees>
            listTestesAdapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(listViewTestes);
            registerForContextMenu(listViewTestes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(0, DELETE_LIST_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == DELETE_LIST_ID){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            kindOFTestesTreesArrayList.remove(acmi.position);
            listTestesAdapter.notifyDataSetChanged();
            return  true;
        }
        return super.onContextItemSelected(item);

    }

   public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //реализовать сохранение данных семенников каждой породы при переключении экранов
}
