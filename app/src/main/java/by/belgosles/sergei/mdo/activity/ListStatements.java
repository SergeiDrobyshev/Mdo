package by.belgosles.sergei.mdo.activity;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import by.belgosles.sergei.mdo.App;
import by.belgosles.sergei.mdo.R;
import by.belgosles.sergei.mdo.TEstDb.AppDbDict;
import by.belgosles.sergei.mdo.adapters.RVAdapterListStatements;
import by.belgosles.sergei.mdo.model.entity.AppDb;
import by.belgosles.sergei.mdo.model.entity.Fund;

public class ListStatements extends AppCompatActivity {
    public static final long firstId = -1;
    private RVAdapterListStatements adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_statement);

        AppDb database = App.getInstance().getDatabase();
        ArrayList<Fund> fundlist  = (ArrayList<Fund>) database.getstatementDao().getAllStatements();

        RecyclerView recyclerView =  findViewById(R.id.recyclerViewStatementsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // сделать запрос в базу данных, и выбрать созданные ведомости МДО, и передать адаптеру
        RVAdapterListStatements adapter = new RVAdapterListStatements(fundlist,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addFloatButtonCreateStatement(adapter, this);
        tesstDict();
    }

    private void tesstDict() {
        AppDbDict appDbDict = App.getInstance_dict().getDatabase_dict();
    }

    private void addFloatButtonCreateStatement(final RVAdapterListStatements adapter, final Context ctx) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Fb_new_Statement);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.statementDetail(ctx, firstId); // передать новый id для лесничества
            }
        });

    }
}
