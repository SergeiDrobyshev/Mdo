package by.belgosles.sergei.mdo.ChangeStatements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import by.belgosles.sergei.mdo.R;

public class ChangeStatement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_statement);

    //AppDb database = App.getInstance().getDatabase();
        int mas [] = new int[3];
        mas[0] = 1;
        mas[1] = 2;
        mas[2] = 3;


        RecyclerView recyclerView =  findViewById(R.id.recyclerViewStatementsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RVAdapterChangeStatement adapter = new RVAdapterChangeStatement(mas,this);
        recyclerView.setAdapter(adapter);
    }
}
