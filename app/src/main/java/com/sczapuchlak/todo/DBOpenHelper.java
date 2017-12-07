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
        String sql = "CREATE TABLE"+ TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
//        sqLiteDatabase.delete("ToDo",null,null );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
}

    void add(String item){
        String sql = "insert into table"+TABLE_NAME+ "(Items) values (" + item+ ")";

        ContentValues values = new ContentValues();
        values.put("Items", item);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("ToDo", null, values);
        db.close();
    }
    ArrayList<String> getAll(){
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
