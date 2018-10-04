package com.example.android.bookmanager2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class BookDetails extends AppCompatActivity {

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
        setContentView(R.layout.activity_book_details);
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
        inflater.inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_addBook:

                //number of the book to replace
                sbm.getBook(Integer.parseInt(BookNumber));


                Book book = new Book(etAuthor.getText().toString(),
                        etTitle.getText().toString(),
                        Integer.parseInt( etPrice.getText().toString()),
                        etISBN.getText().toString(),
                        etCourse.getText().toString()
                        );


                Intent intentFromAdd = new Intent(BookDetails.this, MainActivity.class);
                startActivity(intentFromAdd);
                return true;

            case R.id.action_deleteBook:

                sbm.removeBook(sbm.getBook(Integer.parseInt(BookNumber)));
                Context context = getApplicationContext();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                sbm.saveChanges(prefs);
                Intent intentFromDel = new Intent(BookDetails.this, MainActivity.class);
                startActivity(intentFromDel);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
