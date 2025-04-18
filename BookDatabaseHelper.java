package com.example.libraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Library.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BOOKS = "Books";
    private static final String COL_ID = "ID";
    private static final String COL_TITLE = "Title";
    private static final String COL_STATUS = "Status";

    public BookDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_BOOKS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_STATUS + " TEXT)");
        insertBook(db, "Java Fundamentals", "Available");
        insertBook(db, "Android Programming", "Lent");
        insertBook(db, "Data Structures", "Available");
    }

    private void insertBook(SQLiteDatabase db, String title, String status) {
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_STATUS, status);
        db.insert(TABLE_BOOKS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    public Cursor getBooksByStatus(String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_BOOKS, null, "Status=?", new String[]{status}, null, null, null);
    }

    public void reserveBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, "Reserved");
        db.update(TABLE_BOOKS, values, "ID=?", new String[]{String.valueOf(id)});
    }
}