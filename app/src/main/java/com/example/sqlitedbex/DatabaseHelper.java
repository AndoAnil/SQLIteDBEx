package com.example.sqlitedbex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Table name
    public static final String TABLE_NAME="COUNTRIES";

    //Table Columns
    public static final String ID="_id";
    public static final String SUBJECT="subject";
    public static final String DES="description";

    //Database information
    static final String DB_NAME="MASTER_ANDROID_APP.DB";

    //Database version
    static final int DB_VERSION=1;

    //Creating table query

    public static final String CREATE_TABLE="create table "
            + TABLE_NAME +"(" + ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+SUBJECT+ " TEXT NOT NULL, "
            +DES +" TEXT );";

    //Constructor

    public DatabaseHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
          onCreate(sqLiteDatabase);
    }
}
