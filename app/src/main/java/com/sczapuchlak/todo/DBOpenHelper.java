package com.sczapuchlak.todo;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper{


    DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table time (ToDo text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists ToDo");
    }

    void add(String item){
        String sql = "insert into table ToDo (ToDo) values (" + item+ ")";

        ContentValues values = new ContentValues();
        values.put("ToDo", item);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("ToDo", null, values);
        db.close();
    }
    ArrayList<String> getAll(){
        String sql = "select * from ToDo";
        SQLiteDatabase db = this. getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<String> items = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                items.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

return items;

    }
}
