package com.example.bipinp.mithun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bipinp on 2/18/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Sales.db";
    public static final String TABLE_NAME1 = "Insert_table";
    public static final String TABLE_NAME2 = "Entry_table";

    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";

    public static final String COL_CUST_NAME = "NAME";
    public static final String COL_CARET = "CARET";
    public static final String COL_AMOUNT = "AMOUNT";
    public static final String COL_AMOUNT_PAID = "AMOUNT_PAID";
    public static final String COL_BALANCE = "BALANCE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");
        db.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CARET INTEGER,AMOUNT INTEGER,AMOUNT_PAID INTEGER,BALANCE INTEGER,FOREIGN KEY(ID) REFERENCES Insert_table(ID_NAME))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME2);
        onCreate(db);

    }

    public boolean deleteRow(String name)
    {   SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1, COL_NAME + "= '" + name + "';", null) > 0;
    }

    public boolean insertdata(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        long result = db.insert(TABLE_NAME1,null,contentValues);
        if(result == -1)
            return  false;
        else
            return true;
    }

    public boolean insertdata2(String name,String caret,String amount,String amount_paid,String balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CUST_NAME,name);
        contentValues.put(COL_CARET,caret);
        contentValues.put(COL_AMOUNT,amount);
        contentValues.put(COL_AMOUNT_PAID,amount_paid);
        contentValues.put(COL_BALANCE,balance);
        long result = db.insert(TABLE_NAME2,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        String selectQuery = " SELECT " + COL_NAME + " FROM " + TABLE_NAME1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do { labels.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return labels;
    }
    public String balance(String name)
    {
        String label = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = " SELECT "+"sum(" + COL_BALANCE + ")"+ " FROM " + TABLE_NAME2  +  " WHERE " + COL_CUST_NAME + "= '" + name + "';";
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor != null)
        {
            if(cursor.moveToFirst()){
                label = cursor.getString(0);
            }
            cursor.close();
        }

       return label;
    }


}
