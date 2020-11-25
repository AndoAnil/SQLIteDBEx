package com.example.sqlitedbex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyActivity extends AppCompatActivity {

    private EditText subject,description;
    private Button update,delete;
    private DBManager dbManager;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify);
        subject=(EditText)findViewById(R.id.subject_edit);
        description=(EditText)findViewById(R.id.des_edit);
        update=(Button)findViewById(R.id.Update);
        delete=(Button)findViewById(R.id.delete);

        dbManager=new DBManager(this);
        dbManager.open();

        Intent intent=getIntent();
        String ids=intent.getStringExtra("id");
        String sub=intent.getStringExtra("title");
        String des=intent.getStringExtra("description");

        id=Long.parseLong(ids);
        subject.setText(sub);
        description.setText(des);
        
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateRecord();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord();
            }
        });

    }

    private void deleteRecord() {
        dbManager.delete(id);
        goHome();
    }

    public void updateRecord()
    {
     String s=subject.getText().toString();
     String d=description.getText().toString();
     dbManager.update(id,s,d);
     goHome();
    }

    public void goHome()
    {
        Intent intent=new Intent(getApplicationContext(),CountryListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}