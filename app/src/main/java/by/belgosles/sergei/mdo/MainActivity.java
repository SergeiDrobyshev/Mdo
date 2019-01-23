package by.belgosles.sergei.mdo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.belgosles.sergei.mdo.ChangeStatements.ChangeStatement;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button createVedom;
        Button changeVedom;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createVedom = findViewById(R.id.create_statement);
        changeVedom = findViewById(R.id.change_statement);

        View.OnClickListener onclickCreate = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateStatement.class);
                intent.putExtra("message","create");
                startActivity(intent);
            }
        };

        View.OnClickListener onclickChange = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChangeStatement.class);
                startActivity(intent);
            }
        };

        createVedom.setOnClickListener(onclickCreate);
        changeVedom.setOnClickListener(onclickChange);


    }
}
