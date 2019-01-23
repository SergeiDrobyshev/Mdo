package by.belgosles.sergei.mdo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;

import by.belgosles.sergei.mdo.Db.AppDb;
import by.belgosles.sergei.mdo.Db.Statement;
import by.belgosles.sergei.mdo.Db.StatementDao;

public class CreateStatement extends AppCompatActivity implements View.OnClickListener {

    Button statementMdo, statementPerechet, statementPodrost, cannotBeCutting, stockAndTtax, saveCreateStatement;
    EditText edittextDate, forestry, tax_category, realization;
    private AppDb db;
    public static final String EXTRA_MESSAGE = "message";
    String messagetext, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statement);

        Intent intent = getIntent();
        messagetext = intent.getStringExtra(EXTRA_MESSAGE);
        //id = intent.getStringExtra("id");
        findFields();

        statementMdo.setOnClickListener(this);
        statementPerechet.setOnClickListener(this);
        statementPodrost.setOnClickListener(this);
        cannotBeCutting.setOnClickListener(this);
        stockAndTtax.setOnClickListener(this);
        saveCreateStatement.setOnClickListener(this);
        db = App.getInstance().getDatabase();

        if(messagetext.equals("create")){
            Calendar calendar = Calendar.getInstance();
            edittextDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR));
        } else {
            // заполнить пустые ячейки из бд
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StatementMdo.species.clear();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.statement_mdo:
                intent = new Intent(CreateStatement.this, StatementMdo.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.statement_perechet:
                intent = new Intent(CreateStatement.this, PerechetAddSpecies.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.statement_podrost:
                intent = new Intent(CreateStatement.this, UndergrowthAndPollution.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.cannot_be_cutting:
                intent = new Intent(CreateStatement.this, CannotBeCutting.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.stock_and_tax:
                intent = new Intent(CreateStatement.this , StockAndTax.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
            case R.id.save_create_statement:
                final Statement statement = new Statement();
                statement.filling_date = edittextDate.getText().toString();
                statement.forestry = forestry.getText().toString();
                statement.tax_rate = tax_category.getText().toString();
                statement.implementation = realization.getText().toString();

                statement.forest_fund_year = StatementMdo.species.get("forest_fund_year");
                statement.allotment_year = StatementMdo.species.get("allotment_year");

                new  AlertDialog.Builder(this)
                        .setMessage("Вы действительно хотите сохранить?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.getstatementDao().insert(statement);
                                finish();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;

        }
    }

    public void findFields (){
        edittextDate = (EditText) findViewById(R.id.editText_date_zapoln);

        statementMdo = (Button) findViewById(R.id.statement_mdo);
        statementPerechet = (Button) findViewById(R.id.statement_perechet);
        statementPodrost = (Button) findViewById(R.id.statement_podrost);
        cannotBeCutting = (Button) findViewById(R.id.cannot_be_cutting);
        stockAndTtax = (Button) findViewById(R.id.stock_and_tax);
        saveCreateStatement = (Button) findViewById(R.id.save_create_statement);

        forestry = findViewById(R.id.editText_forestry);
        tax_category = findViewById(R.id.editText_tax_category);
        realization = findViewById(R.id.editText_realization);
    }

    @Override
    public void onBackPressed() {
        new  AlertDialog.Builder(this)
                .setMessage("Выйти без сохранения?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        // dfjgsljgbdgfsgsd
                    }
                })
                .setNegativeButton("Нет", null)
                .show();
    }
}
