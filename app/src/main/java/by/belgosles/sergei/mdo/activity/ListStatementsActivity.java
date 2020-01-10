package by.belgosles.sergei.mdo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wajahatkarim3.roomexplorer.RoomExplorer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.adapters.RVAdapterListStatements;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.Fund;


public class ListStatementsActivity extends androidx.appcompat.app.AppCompatActivity {

    @BindView(R.id.recyclerViewStatementsList) RecyclerView recyclerView;
    @BindView(R.id.Fb_new_Statement)FloatingActionButton fab;
    private  RVAdapterListStatements adapter;
    private AppDb database;
    public static final int REQUEST_CODE_CREATE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_statements);
        ButterKnife.bind(this);

        database = App.getInstance().getDatabase();
        ArrayList<Fund> fundList = (ArrayList<Fund>) database.getstatementDao().getAllStatements();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapterListStatements(fundList,this);
        recyclerView.setAdapter(adapter);

        addFloatButtonCreateStatement(this);
        //todo remove in release
        //RoomExplorer.show(this, AppDb.class, "databaseMdo");
    }

    private void addFloatButtonCreateStatement(Context ctx) {
        fab.setOnClickListener(view -> {
            toStatementActivity(ctx, REQUEST_CODE_CREATE);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (resultCode == RESULT_OK) {
            adapter.dataChanged((ArrayList<Fund>) database.getstatementDao().getAllStatements());
        }*/
    }

    public void toStatementActivity(Context context, int REQUEST_CODE){
        Intent intent = new Intent(context, CreateStatementActivity.class);
        intent.putExtra("REQUEST_CODE", REQUEST_CODE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void deleteStatementFromDb(long fundIdByPosition) {
        new AlertDialog.Builder(this)
                .setMessage("Вы действительно хотите удалить ведомость?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    Fund fund = database.getstatementDao().getFundById(fundIdByPosition);
                    database.getstatementDao().delete(fund);
                    ArrayList <Fund> fundList  = (ArrayList<Fund>) database.getstatementDao().getAllStatements();
                    adapter.dataChanged(fundList);
                })
                .setNegativeButton("Нет", null)
                .show();

    }

    @Override
    protected void onResume() {
        adapter.dataChanged((ArrayList<Fund>) database.getstatementDao().getAllStatements());
        super.onResume();
    }
}
