package by.belgosles.sergei.mdo;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Perechet extends AppCompatActivity implements View.OnClickListener {
    public static final String Extra_OnClickAddedSpecies = "m";
    int [] diameters = new int [30];
    String messagetext;
    public Map<String,String> amountTreesbydiameters = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perechet);
        TextView tv = findViewById(R.id.textview_species_forPerechet);

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
            PerechetAdapter adapter = new PerechetAdapter(diameters);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemViewCacheSize(diameters.length);
        }else{
            //заполнить перечетку данными из бд
        }
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

                                finish();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;
        }
    }
}
