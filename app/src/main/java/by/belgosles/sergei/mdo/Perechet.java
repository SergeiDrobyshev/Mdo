package by.belgosles.sergei.mdo;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Perechet extends AppCompatActivity implements View.OnClickListener {
    public static final String Extra_OnClickAddedSpecies = "m";
    TextView tv, sum_del,sum_drov;
    Spinner spinner_heights_level;
    int [] diameters = new int [30];
    String messagetext;
    public EnumCopy enumCopy = new EnumCopy();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perechet);
        findFields();

        int k = 8;
        for (int i = 0; i < 30; i++ ){
            diameters [i] = k;
            k = k + 4;
        }

        Intent intent = getIntent();
        tv.setText(intent.getStringExtra(Extra_OnClickAddedSpecies));
        messagetext = intent.getStringExtra("message");
        RecyclerView recyclerView =  findViewById(R.id.recyclerViewPerechet);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(messagetext.equals("create")){
            PerechetAdapter adapter = new PerechetAdapter(diameters, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemViewCacheSize(diameters.length);

        }//заполнить перечетку данными из бд

    }

    public void findFields(){
        tv = findViewById(R.id.textview_species_forPerechet);
        sum_del = findViewById(R.id.sum_del);
        sum_drov = findViewById(R.id.sum_drov);
        spinner_heights_level = findViewById(R.id.spinner_heights_level);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.height_measure:
                break;
            case R.id.model_tree:
                break;
            case R.id.save_perechet:
                new  AlertDialog.Builder(this)
                        .setMessage("Вы действительно хотите сохранить?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                PerechetAdapter.masvalues.clear();
                                enumCopy.sum_del_copy = sum_del.getText().toString();
                                enumCopy.sum_drov_copy = sum_drov.getText().toString();
                                enumCopy.heights_level_copy = spinner_heights_level.getSelectedItem().toString();
                                enumCopy.species_copy = tv.getText().toString();
                                enumCopy.perechettrees = PerechetAdapter.masvalues;
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new  AlertDialog.Builder(this)
                .setMessage("Выйти без сохранения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PerechetAdapter.masvalues.clear();
                        finish();
                    }
                })
                .setNegativeButton("Нет", null)
                .show();
    }
}
