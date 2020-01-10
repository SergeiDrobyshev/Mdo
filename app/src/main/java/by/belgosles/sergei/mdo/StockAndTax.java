package by.belgosles.sergei.mdo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class StockAndTax extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_and_tax);

        final Spinner spinner = findViewById(R.id.spinner_stock_and_tax_speecies);
        // взять из бд список добавленных пород в перечетке и передать в адаптер спиннера


       ArrayAdapter arrayadapterspinner = ArrayAdapter.createFromResource(this, R.array.species_trees, R.layout.spinner_item11);
       arrayadapterspinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
       spinner.setAdapter(arrayadapterspinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String selectedItem = spinner.getSelectedItem().toString();
                // выбрать из бд  для выбранной породы запас и такс. стоим.
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


}
