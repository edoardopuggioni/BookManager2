package com.example.android.bookmanager2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class BookEdit extends AppCompatActivity {

    String BookNumber;
    SimpleBookManager sbm = SimpleBookManager.getBookManager();

    EditText etAuthor;
    EditText etTitle;
    EditText etCourse;
    EditText etISBN ;
    EditText etPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
        Intent CaughtIntent = getIntent();
        BookNumber = CaughtIntent.getStringExtra(EXTRA_MESSAGE);

        // fill edit boxes with data
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etCourse = (EditText) findViewById(R.id.etCourse);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etISBN = (EditText) findViewById(R.id.etISBN);

        etAuthor.setText(sbm.getBook(Integer.parseInt(BookNumber)).getAuthor());
        etTitle.setText(sbm.getBook(Integer.parseInt(BookNumber)).getTitle());
        etCourse.setText(sbm.getBook(Integer.parseInt(BookNumber)).getCourse());
        etISBN.setText(sbm.getBook(Integer.parseInt(BookNumber)).getIsbn());
        etPrice.setText("" + sbm.getBook(Integer.parseInt(BookNumber)).getPrice());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //number of the book to replace
        sbm.getBook(Integer.parseInt(BookNumber));
        int id = item.getItemId();
        if (id == R.id.action_saveBook) {
            //preparing new book object
            Book book = new Book(etAuthor.getText().toString(),
                                 etTitle.getText().toString(),
                                 Integer.parseInt(etPrice.getText().toString()),
                                 etISBN.getText().toString(),
                                 etCourse.getText().toString());

            //replacing edited version of book with the old one
            sbm.getAllBooks().set(Integer.parseInt(BookNumber),book);
        }
        //go to main activity
        Intent intentFromAdd = new Intent(BookEdit.this, MainActivity.class);
        startActivity(intentFromAdd);

        return super.onOptionsItemSelected(item);
    }

}
