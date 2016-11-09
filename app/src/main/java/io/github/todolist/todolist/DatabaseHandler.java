package io.github.todolist.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divay Prakash on 09-Nov-16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todolist_db";
    private static final String TABLE_TODO = "todolist";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC = "desc";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_DESC + " TEXT"
                + ")";
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public void addItem(TodoItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getId());
        values.put(KEY_TITLE, item.getTitle());
        values.put(KEY_DESC, item.getDesc());
        db.insert(TABLE_TODO, null, values);
        db.close();
    }

    public List<TodoItem> getAllStudents() {
        List<TodoItem> todoItemList = new ArrayList<TodoItem>();
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                TodoItem item = new TodoItem();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setTitle(cursor.getString(1));
                item.setDesc(cursor.getString(2));
                todoItemList.add(item);
            } while (cursor.moveToNext());
        }
        return todoItemList;
    }

    public int updateItem(TodoItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getId());
        values.put(KEY_TITLE, item.getTitle());
        values.put(KEY_DESC, item.getDesc());
        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    public void deleteItem(TodoItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId())});
        db.close();
    }
}
