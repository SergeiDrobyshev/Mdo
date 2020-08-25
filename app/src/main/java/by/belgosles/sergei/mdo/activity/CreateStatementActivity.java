package by.belgosles.sergei.mdo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import by.belgosles.sergei.mdo.adapters.DictSpinnerAdapter;
import by.belgosles.sergei.mdo.adapters.RVAdapterListStatements;
import by.belgosles.sergei.mdo.model.DictName;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.EnumTreesAmount;
import by.belgosles.sergei.mdo.model.entity.Fund;
import by.belgosles.sergei.mdo.model.entity.FundEnum;

public class CreateStatementActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.editText_date_zapoln) EditText edittextDate;
    @BindView(R.id.editText_forestry)EditText forestry;
    @BindView(R.id.sp_tax_category)  Spinner tax_category;
    @BindView(R.id.sp_realization) Spinner realization;
    @BindView(R.id.inaccessibility) CheckBox inaccessibility;
    @BindView(R.id.fond) CheckBox stateFundForest;

    private AppDb db;
    public static final String EXTRA_id_fund = "id_fund";
    String messagetext;
    Bundle bundle;
    private Fund fund;
    private int request_code;
    private long newFundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_statement);
        ButterKnife.bind(this);
        db = App.getInstance().getDatabase();
        setAdapters();
        fillFields();
        // без заполнения лесничества, даты , не активировать кнопку на ведомость мдо, создание строки и возвращение ид повесить на эту кнопку
    }

    private void setAdapters() {
        ArrayList <DictName> list_tax_rate = (ArrayList<DictName>) db.getDictsDao().getAllRankTrf();
        DictSpinnerAdapter tax_adapter = new DictSpinnerAdapter(this, R.layout.spinner_title , list_tax_rate);
        tax_category.setAdapter(tax_adapter);

        ArrayList<DictName> list_realiz = (ArrayList<DictName>) db.getDictsDao().getAllMethReal();
        DictSpinnerAdapter adapter_realiz = new DictSpinnerAdapter(this, R.layout.spinner_title, list_realiz);
        realization.setAdapter(adapter_realiz);
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

                fund = new Fund();
                newFundId = db.getstatementDao().insertFund(fund);// id созданной ведомости
            }
        }
    }

    private void fillfieldsfromDB()  {
        // заполнение полей из бд
        //todo не доставать весь обьект fund, а только те что нужны
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

    @OnClick({R.id.statement_mdo, R.id.stock_and_tax, R.id.save_create_statement})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()) {
            case R.id.statement_mdo:
                intent = new Intent(CreateStatementActivity.this, StatementInfoActivity.class);
                if(request_code == RVAdapterListStatements.REQUEST_CODE_CHANGE) {
                    intent.putExtra(EXTRA_id_fund, fund.getId_fund());
                }
                else{
                    intent.putExtra(EXTRA_id_fund, newFundId);
                }
                startActivityForResult(intent, request_code);
                break;
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
                            if(request_code == RVAdapterListStatements.REQUEST_CODE_CHANGE){// проверка на создание новой ведомости или изменение старой
                                updateFund(fund.getId_fund());
                            }else {
                                updateFund(newFundId);
                            }
                            setResult(RESULT_OK);
                            finish();

                        })
                        .setNegativeButton("Нет", null)
                        .show();
                break;
        }
    }

    // сохранение данных из вью в бд
    private void updateFund(long id_fund) {
        String filling_date = getInputtedText(edittextDate);
        //todo сохранять id виесто значения
        String id_forestry = getInputtedText(forestry);

        int id_tax_category = (Integer) tax_category.getSelectedView().getTag();
        int id_implementation = (Integer) realization.getSelectedView().getTag();

        boolean inaccessibilityValue = inaccessibility.isChecked();
        boolean stateFundForestValue = stateFundForest.isChecked();

        db.getstatementDao().updateFromCreateStatementActivity(filling_date, id_forestry, id_tax_category, id_implementation,
                inaccessibilityValue, stateFundForestValue, id_fund);
    }

    @Override
    public void onBackPressed() {
        new  AlertDialog.Builder(this)
                .setMessage("Выйти без сохранения?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    if(request_code == ListStatementsActivity.REQUEST_CODE_CREATE){
                        db.getstatementDao().deleteNotSaveStatement(newFundId);
                    }
                    finish();
                })
                .setNegativeButton("Нет", null)
                .show();
    }

    private String getInputtedText(EditText editText) {
        if (editText.getText() != null) {
            return editText.getText().toString();
        }
        return "";
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
