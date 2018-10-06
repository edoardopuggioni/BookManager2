package com.example.android.bookmanager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class BookDetails extends AppCompatActivity {

    String BookNumber;
    SimpleBookManager sbm = SimpleBookManager.getBookManager();

    TextView tvAuthor;
    TextView tvTitle;
    TextView tvCourse;
    TextView tvISBN ;
    TextView tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //catch number of book from previous activity
        Intent CaughtIntent = getIntent();
        BookNumber = CaughtIntent.getStringExtra(AlarmClock.EXTRA_MESSAGE);

        tvAuthor = findViewById(R.id.label_author);
        tvTitle =  findViewById(R.id.label_title);
        tvCourse = findViewById(R.id.label_course);
        tvPrice =  findViewById(R.id.label_price);
        tvISBN =   findViewById(R.id.label_isbn);

        //fill TextViews with data
        tvAuthor.setText(sbm.getBook(Integer.parseInt(BookNumber)).getAuthor());
        tvTitle.setText(sbm.getBook(Integer.parseInt(BookNumber)).getTitle());
        tvCourse.setText(sbm.getBook(Integer.parseInt(BookNumber)).getCourse());
        tvISBN.setText(sbm.getBook(Integer.parseInt(BookNumber)).getIsbn());
        tvPrice.setText("" + sbm.getBook(Integer.parseInt(BookNumber)).getPrice());
        Log.d("tag1",BookNumber);
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
            //go to BookEdit activity
            case R.id.action_saveBook:

                Intent intentFromEdit = new Intent(BookDetails.this, BookEdit.class);
                intentFromEdit.putExtra( EXTRA_MESSAGE, BookNumber );
                startActivity(intentFromEdit);
                return true;

            // delete item
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
