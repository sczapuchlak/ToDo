package com.sczapuchlak.todo;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "toDo.db";
    private static final String TABLE_NAME = "toDo";
    private static final String COL_NAME = "items";

    DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
//        sqLiteDatabase.delete("ToDo",null,null );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
}

    void add(String item){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, item);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public ArrayList<String> getAll(){
        String sql = "select * from"+ TABLE_NAME;
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
