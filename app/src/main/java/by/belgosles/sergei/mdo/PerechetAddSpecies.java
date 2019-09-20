package by.belgosles.sergei.mdo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import by.belgosles.sergei.mdo.adapters.ListAddedSpeciesAdapter;

public class PerechetAddSpecies extends AppCompatActivity implements View.OnClickListener  {

    private static final int DELETE_ADDED_LIST_ID = 1;
    ListAddedSpeciesAdapter listAddedSpeciesAdapter;
    ArrayList<String> arrayListAddedSpecies = new ArrayList<>();
    String messagetext;
    Button saveTotalPerechet;
    Perechet perechet;
    ArrayList<EnumTemp> listEnum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_enum_add_species);
        Intent intent = getIntent();
        messagetext = intent.getStringExtra("message");
        //saveTotalPerechet = findViewById(R.id.save_total_perechet);

        NoDefaultSpinner spinner = findViewById(R.id.spinner_poroda_value);
       // ListView listviewAddedSpecies = (ListView) findViewById(R.id.listview_added_species);
        ArrayAdapter arrayadapterspinner = ArrayAdapter.createFromResource(this, R.array.species_trees, R.layout.spinner_item);
        arrayadapterspinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(arrayadapterspinner);
        listAddedSpeciesAdapter = new ListAddedSpeciesAdapter(this, arrayListAddedSpecies);
        //listviewAddedSpecies.setAdapter(listAddedSpeciesAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {  // добавление в arraylist выбранного item из spinner и обновление адаптера списка

                String selectedItem = spinner.getSelectedItem().toString();
                if(arrayListAddedSpecies.contains(selectedItem)){
                    //выбранная порода уже добавлена
                }else {
                    arrayListAddedSpecies.add(selectedItem);
                    listAddedSpeciesAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

       // registerForContextMenu(listviewAddedSpecies);
       /* listviewAddedSpecies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String addedSpeciesTolist = listAddedSpeciesAdapter.getItem(position).toString();
                Intent intent = new Intent(PerechetAddSpecies.this, Perechet.class);
                intent.putExtra(Perechet.Extra_OnClickAddedSpecies,addedSpeciesTolist);
                intent.putExtra("message", messagetext);
                startActivity(intent);

            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
       // listEnum.add(perechet.enumCopy);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(0, DELETE_ADDED_LIST_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == DELETE_ADDED_LIST_ID) {
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            arrayListAddedSpecies.remove(acmi.position);  // удаление из arraylist выбранной записи в списке
            listAddedSpeciesAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View view) {
       /* switch (view.getId()){
            case R.id.save_total_perechet:
                new  AlertDialog.Builder(this)
                        .setMessage("Вы действительно хотите сохранить?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //perechet.enumCopy
                                // продумать уничтожение активити из стэка и сохранение данных

                                finish();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;
        }*/
    }
}
