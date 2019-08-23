package by.belgosles.sergei.mdo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.CannotBeCutting;
import by.belgosles.sergei.mdo.PerechetAddSpecies;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.StatementMdo;
import by.belgosles.sergei.mdo.StockAndTax;
import by.belgosles.sergei.mdo.UndergrowthAndPollution;
import by.belgosles.sergei.mdo.adapters.RVAdapterListStatements;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.Fund;

public class CreateStatement extends AppCompatActivity implements View.OnClickListener {

    Button statementMdo, statementPerechet, statementPodrost, cannotBeCutting, stockAndTtax, saveCreateStatement;
    EditText edittextDate, forestry, tax_category, realization;
    private AppDb db;
    public static final String EXTRA_id_fund = "id_fund";
    String messagetext, id;
    Bundle bundle;
    private Fund fund;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statement);

        findFields();
        buttonListeners();

        db = App.getInstance().getDatabase();

        fillFields();
    }

    private void fillFields() {
        bundle = getIntent().getExtras();
        int request_code;
        if (bundle != null) {
            request_code = bundle.getInt("REQUEST_CODE");
            if (request_code == RVAdapterListStatements.REQUEST_CODE_CHANGE) {
                 fund = db.getstatementDao().getSelectedFundForChange(bundle.getLong(EXTRA_id_fund));
                 fillfieldsfromDB(fund);

            } else {
                Calendar calendar = Calendar.getInstance();
                edittextDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR));
            }
        }
    }

    private void fillfieldsfromDB(Fund fund) {
        // заполнение полей из бд
        forestry.setText(fund.getId_forestry());
        realization.setText(fund.getImplementation());
    }

    private void buttonListeners() {
        statementMdo.setOnClickListener(this);
        statementPerechet.setOnClickListener(this);
        statementPodrost.setOnClickListener(this);
        cannotBeCutting.setOnClickListener(this);
        stockAndTtax.setOnClickListener(this);
        saveCreateStatement.setOnClickListener(this);
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
                final Fund fund = new Fund();
                //fund.filling_date = edittextDate.getText().toString();
                fund.setId_forestry(forestry.getText().toString());
               // fund.tax_rate = tax_category.getText().toString();
                fund.setImplementation(realization.getText().toString());

                //fund.year_fund = StatementMdo.species.get("year_fund");
                //fund.year_allot = StatementMdo.species.get("year_allot");

                new  AlertDialog.Builder(this)
                        .setMessage("Вы действительно хотите сохранить?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.getstatementDao().insert(fund);
                                setResult(RESULT_OK);
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
                .setPositiveButton("Да", (dialogInterface, i) -> finish())
                .setNegativeButton("Нет", null)
                .show();
    }
}
