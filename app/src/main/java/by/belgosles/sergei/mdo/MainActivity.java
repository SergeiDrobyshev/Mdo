package by.belgosles.sergei.mdo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import by.belgosles.sergei.mdo.activity.CreateStatement;
import by.belgosles.sergei.mdo.activity.ListStatements;

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
                Intent intent = new Intent(MainActivity.this, ListStatements.class);
                startActivity(intent);
            }
        };

        createVedom.setOnClickListener(onclickCreate);
        changeVedom.setOnClickListener(onclickChange);

    }
}
