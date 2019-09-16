package by.belgosles.sergei.mdo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.StockAndTax;
import by.belgosles.sergei.mdo.adapters.RVAdapterListStatements;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.Fund;

public class CreateStatementActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editText_date_zapoln) EditText edittextDate;
    @BindView(R.id.editText_forestry)EditText forestry;
    @BindView(R.id.sp_tax_category)  Spinner tax_category;
    @BindView(R.id.sp_realization) Spinner realization;
    @BindView(R.id.inaccessibility) CheckBox inaccessibility;
    @BindView(R.id.fond) CheckBox stateFundForest;

    private AppDb db;
    public static final String EXTRA_id_fund = "id_fund";
    String messagetext, id;
    Bundle bundle;
    private Fund fund;
    private int request_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statement);
        ButterKnife.bind(this);
        db = App.getInstance().getDatabase();
        setAdapters();
        fillFields();
    }

    private void setAdapters() {
        ArrayList<String> refsDataRealiz = (ArrayList<String>) db.getDictsDao().getAllRealizs();
        ArrayList <String> refsDataTax = (ArrayList<String>) db.getDictsDao().getAllTaxRate();
        ArrayAdapter<String> tax_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, refsDataTax);
        tax_adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        tax_category.setAdapter(tax_adapter);
        //setListeners();

        ArrayAdapter<String> realiz_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, refsDataRealiz);
        realiz_adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        realization.setAdapter(realiz_adapter);
        //setListeners();
    }

    private void fillFields() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            request_code = bundle.getInt("REQUEST_CODE");
            if (request_code == RVAdapterListStatements.REQUEST_CODE_CHANGE) {
                 fillfieldsfromDB();

            } else {
                Calendar calendar = Calendar.getInstance();
                edittextDate.setText(calendar.get(Calendar.DAY_OF_MONTH) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR));
                stateFundForest.setChecked(true);
                inaccessibility.setChecked(false);
            }
        }
    }

    private void fillfieldsfromDB()  {
        // заполнение полей из бд
        fund = db.getstatementDao().getFundById(bundle.getLong(EXTRA_id_fund));

        if(fund.getFilling_date() != null){
            edittextDate.setText(fund.getFilling_date());
        }
        if(fund.getId_forestry() != null){
            forestry.setText(fund.getId_forestry());
        }
        //todo remove -1
        tax_category.setSelection(fund.getId_tax_rate() - 1);
        realization.setSelection(fund.getId_implementation() - 1);

        inaccessibility.setChecked(fund.isInaccessibility());
        stateFundForest.setChecked(fund.isState_fund_forest());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.statement_mdo, R.id.statement_perechet, R.id.statement_podrost, R.id.cannot_be_cutting, R.id.stock_and_tax, R.id.save_create_statement})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()) {
            case R.id.statement_mdo:
                intent = new Intent(CreateStatementActivity.this, StatementInfoActivity.class);
                startActivity(intent);
               /* intent = new Intent(CreateStatementActivity.this, StatementMdoFragment.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);*/
                break;
            /*case R.id.statement_perechet:
                intent = new Intent(CreateStatementActivity.this, PerechetAddSpecies.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.statement_podrost:
                intent = new Intent(CreateStatementActivity.this, UndergrowthAndPollution.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.cannot_be_cutting:
                intent = new Intent(CreateStatementActivity.this, CannotBeCutting.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;*/
            case R.id.stock_and_tax:
                intent = new Intent(CreateStatementActivity.this , StockAndTax.class);
                intent.putExtra("message", messagetext);
                startActivity(intent);
                break;
            case R.id.save_create_statement:
                if(isEmptyFields()){
                    break;
                }
                new  AlertDialog.Builder(this)
                        .setMessage("Вы действительно хотите сохранить?")
                        .setPositiveButton("Да", (dialogInterface, i) -> {
                            if(request_code == RVAdapterListStatements.REQUEST_CODE_CHANGE){
                                db.getstatementDao().update(setDataToFund());
                            }else {
                                Fund fund = setDataToFund();
                                db.getstatementDao().insert(fund);
                            }
                            setResult(RESULT_OK);
                            finish();
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;
        }
    }

    private Fund setDataToFund() {
        // убрать проверку на заполнеине полей
        if(fund == null){
            fund = new Fund();
        }
        if(!getInputtedText(edittextDate).isEmpty()){
            fund.setFilling_date(getInputtedText(edittextDate));
        }
        if(!getInputtedText(forestry).isEmpty()){
            fund.setId_forestry(getInputtedText(forestry));
        }

        fund.setId_tax_rate(getinputTextSp(tax_category));
        fund.setId_implementation(getinputTextSp(realization));
        fund.setInaccessibility(inaccessibility.isChecked());
        fund.setState_fund_forest(stateFundForest.isChecked());

        return fund;
    }

    @Override
    public void onBackPressed() {
        new  AlertDialog.Builder(this)
                .setMessage("Выйти без сохранения?")
                .setPositiveButton("Да", (dialogInterface, i) -> finish())
                .setNegativeButton("Нет", null)
                .show();
    }

    private String getInputtedText(EditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString();
        }
        return "";
    }

    private int getinputTextSp(Spinner sp) {
            String name = sp.getSelectedItem().toString();
            if(sp == tax_category){
                return db.getDictsDao().getIdRankTax(name);
            }
            else if(sp == realization){
                return db.getDictsDao().getIdMethReal(name);
            }
            //TODO
        return 0;
    }

    private boolean isEmptyFields (){
        if(edittextDate.getText().toString().isEmpty()){
            showWarnWindow("",getString(R.string.date_error));
            return true;
        }
        if(forestry.getText().toString().isEmpty()){
            showWarnWindow("",getString(R.string.forestry_error));
            return true;
        }
        if(tax_category.getSelectedItem() == null){
            showWarnWindow("",getString(R.string.tax_category_error));
            return true;
        }
        if(realization.getSelectedItem() == null){
            showWarnWindow("",getString(R.string.realization_error));
            return true;
        }
        return false;
    }

    private void showWarnWindow(String title, String mes){
        Toast toast = Toast.makeText(this, mes,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
