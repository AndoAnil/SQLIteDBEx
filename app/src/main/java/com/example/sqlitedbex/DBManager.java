package com.example.sqlitedbex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper databaseHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    //Constructor
    public DBManager(Context c)
    {
        context=c;
    }

    public DBManager open() throws SQLException{
        databaseHelper=new DatabaseHelper(context);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        return this;
    }

    public void close()
    {
        databaseHelper.close();
    }

    public void insert(String name,String des)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,name);
        contentValues.put(DatabaseHelper.DES,des);
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public Cursor fetch()
    {
        String[] columns=new String[]{DatabaseHelper.ID,
                            DatabaseHelper.SUBJECT,DatabaseHelper.DES};
        Cursor cursor=sqLiteDatabase.query(DatabaseHelper.CREATE_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id,String name,String des)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT,name);
        contentValues.put(DatabaseHelper.DES,des);
        int i=sqLiteDatabase.update(DatabaseHelper.TABLE_NAME,
                contentValues,DatabaseHelper.ID+
                " = " + id,null);
        return  i;
    }

    public void delete(long id)
    {
        sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID+" = ",null);
    }
}
