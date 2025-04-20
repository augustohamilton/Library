package com.example.libraryapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {
    ListView listView;
    BookDatabaseHelper dbHelper;
    ArrayList<String> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        listView = findViewById(R.id.listView);
        dbHelper = new BookDatabaseHelper(this);
        bookList = new ArrayList<>();

        Cursor cursor = dbHelper.getBooksByStatus("Available");
        if (cursor.moveToFirst()) {
            do {
                bookList.add(cursor.getString(cursor.getColumnIndexOrThrow("Title")));
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);
    }
}