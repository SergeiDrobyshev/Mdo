package by.belgosles.sergei.mdo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CannotBeCutting extends AppCompatActivity implements View.OnClickListener {

    Button pressToSelectSpecies;
    String[] trees = {"береза", "дуб", "сосна", "ель", "осина"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannot_be_cutting);

        pressToSelectSpecies = (Button) findViewById(R.id.press_to_select);
        pressToSelectSpecies.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        ContextThemeWrapper cw = new ContextThemeWrapper( this, R.style.AlertDialogTheme );
        AlertDialog.Builder builder = new AlertDialog.Builder(cw);
        builder.setTitle("Выберите породу").setCancelable(true);
        builder.setItems(trees, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String onClickWood = trees[item];
                Intent intent = new Intent(CannotBeCutting.this, SpeciesCannotbeCutting.class);
                intent.putExtra(SpeciesCannotbeCutting.EXTRA_ONCLICKWOOD, onClickWood);
                startActivity(intent);
            }
        });
        switch (view.getId()){
            case R.id.press_to_select:
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.save_cannot_be_cutting:
                //save parametres 
        }
    }
}
