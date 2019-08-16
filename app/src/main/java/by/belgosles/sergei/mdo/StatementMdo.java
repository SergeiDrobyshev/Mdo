package by.belgosles.sergei.mdo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

public class StatementMdo extends AppCompatActivity implements View.OnClickListener {

    public static Map<String,String> species = new HashMap<>();
    public  Spinner spinner_vidpolz, spinner_forest_category, spinner_hoz_section, spinner_species_group, spinner_type_cutting,
            spinner_accounting_method, spinner_cleaning_method, spinner_recovery_method, spinner_bonitet, spinner_forest_type, spinner_soil,
            spinner_stand_condition;
    public  EditText editText_year_forest_fund, editText_year_otvod, editText_forest_quarter, editText_number_lesosek,
            editText_number_videl, editText_square, editText_sostav, editText_cover, editText_age, editText_fullness, editText_sr_height,
            editText_sr_diameter, editText_note;

    public  Button savestatementmdo;
    public String messagetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedom_mdo);
        findViews();
        savestatementmdo.setOnClickListener(this);
        Intent intent = getIntent();
        messagetext = intent.getStringExtra("message");
        if(messagetext.equals("create")){
            if(!species.isEmpty()) {
                fillingFields(species);
            }
        }else{
            //заполнить поля из базы по ид
        }

    }

    @Override
    public void onBackPressed() {
        new  AlertDialog.Builder(this)
                .setMessage("Выйти без сохранения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        species.clear();
                        finish();
                    }
                })
                .setNegativeButton("Нет", null)
                .show();
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case  R.id.save_statement_mdo:
               new  AlertDialog.Builder(this)
                       .setMessage("Вы действительно хотите сохранить?")
                       .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               species.clear();
                               species.put("year_fund", editText_year_forest_fund.getText().toString());
                               species.put("year_allot",editText_year_otvod.getText().toString());
                               finish();
                           }
                       })
                       .setNegativeButton("Нет", null)
                       .show();
               break;
       }
    }

    public void fillingFields(Map<String,String> species){
        editText_year_forest_fund.setText(species.get("year_fund"));
        editText_year_otvod.setText(species.get("year_allot"));
        //добавить остальные поля
    }

    private void findViews (){
        savestatementmdo = findViewById(R.id.save_statement_mdo);
        editText_year_forest_fund = findViewById(R.id.editText_year_forest_fund);
        editText_year_otvod = findViewById(R.id.editText_year_otvod);
        //остальные поля
    }
}
