package com.example.libraryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReserveBook extends AppCompatActivity {
    BookDatabaseHelper dbHelper;
    EditText bookIdInput;
    Button reserveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_book);
        dbHelper = new BookDatabaseHelper(this);
        bookIdInput = findViewById(R.id.bookIdInput);
        reserveBtn = findViewById(R.id.reserveBtn);

        reserveBtn.setOnClickListener(view -> {
            int bookId = Integer.parseInt(bookIdInput.getText().toString());
            dbHelper.reserveBook(bookId);
            Toast.makeText(ReserveBook.this, "Book Reserved!", Toast.LENGTH_SHORT).show();
        });
    }
}