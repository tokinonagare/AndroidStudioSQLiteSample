package com.tokinonagare.sqlitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public  static final String TABLE_PRODUCTS = "products";
    public  static final String COLUMN_ID = "_id";
    public  static final String COLUMN_PRODUCTNAME = "productname";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //"AUTOINCREMENT, " this comma is for COLUMN_PRODUCTNAME to avoid SQLite syntax error
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS );
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database
    public  void addProduct(Products product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_PRODUCTS, null, values);
        sqLiteDatabase.close();
    }

    //Delete the product from the database
    public void deleteProduct(String productName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //Print out the database as a string
    public String databaseToString() {
        String sqLiteDatabaseString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor point to a location in your results
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);



        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex("productname"))!= null) {
                sqLiteDatabaseString += cursor.getString(cursor.getColumnIndex("productname"));
                sqLiteDatabaseString += "\n";
            }
        }

        cursor.close();


        //In the tutorial he use cursor.moveToFirst() & while(!cursor.isAfterLast()){}
        //But I can not run it correctly, when click addButton no thing appear,
        //and it freeze by the memory seems be called times by times.
        //So I changed it.


        //Move to the first row in your results
        //cursor.moveToFirst();

        //while(!cursor.isAfterLast()) {
        //    if(cursor.getString(cursor.getColumnIndex("productname"))!= null) {
        //        sqLiteDatabaseString += cursor.getString(cursor.getColumnIndex("productname"));
        //        sqLiteDatabaseString += "\n";
        //    }
        //}



        sqLiteDatabase.close();
        return sqLiteDatabaseString;
    }
}
