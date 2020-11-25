package com.example.sqlitedbex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCounrtryAcivity extends AppCompatActivity {

    private EditText subject,description;
    private Button addRecord;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_counrtry_acivity);

        subject=(EditText)findViewById(R.id.subject_edittext);
        description=(EditText)findViewById(R.id.des_edittext);
        addRecord=(Button)findViewById(R.id.addRecord);
        dbManager=new DBManager(this);
        dbManager.open();

        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=subject.getText().toString();
                String d=description.getText().toString();
                dbManager.insert(s,d);
                Intent intent=new Intent(getApplicationContext(),CountryListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
