package com.example.sqlitedbex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from=new String[]{DatabaseHelper.ID,DatabaseHelper.SUBJECT,DatabaseHelper.DES};
    final int[] to=new int[]{R.id.id,R.id.title,R.id.des};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbManager=new DBManager(this);
        dbManager.open();
        Cursor cursor=dbManager.fetch();

        ListView listView=findViewById(R.id.listView);
        listView.setEmptyView(findViewById(R.id.empty));
        adapter=new SimpleCursorAdapter(this,R.layout.activitu_view_record,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextView=view.findViewById(R.id.id);
                TextView titleTextView=view.findViewById(R.id.title);
                TextView descriptionTextView=view.findViewById(R.id.des);
                String id=idTextView.getText().toString();
                String title=titleTextView.getText().toString();
                String des=descriptionTextView.getText().toString();

                Intent modify_intent=new Intent(getApplicationContext(),ModifyActivity.class);
                modify_intent.putExtra("title",title);
                modify_intent.putExtra("description",des);
                modify_intent.putExtra("id",id);
                startActivity(modify_intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.addRecord)
        {
            startActivity(new Intent(getApplicationContext(),AddCounrtryAcivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}