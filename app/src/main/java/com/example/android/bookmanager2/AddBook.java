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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class AddBook extends AppCompatActivity
{
    private EditText edTitle = null;
    private EditText edAuthor = null;
    private EditText edCourse = null;
    private EditText edIsbn = null;
    private EditText edPrice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //initializing input
        edTitle = (EditText)  findViewById(R.id.editTextTitle);
        edAuthor = (EditText) findViewById(R.id.editTextAuthor);
        edCourse = (EditText) findViewById(R.id.editTextCourse);
        edIsbn = (EditText) findViewById(R.id.editTextIsbn);
        edPrice = (EditText) findViewById(R.id.editTextPrice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu items for use in the actionbar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        Context context = getApplicationContext();

        String title;
        String author;
        String course;
        String isbn;
        String price_string; //later to be converted to int value
        Integer price;
        SimpleBookManager sbm = SimpleBookManager.getBookManager();
        Toast toast;

        title = edTitle.getText().toString();
        author = edAuthor.getText().toString();
        course = edCourse.getText().toString();
        isbn=edIsbn.getText().toString();
        price_string=edPrice.getText().toString();

        price=sbm.validateData(context,title,author,course,isbn,price_string);

        if(price!=-1)
            sbm.createBook(author,title,price,isbn,course);
        else
            return false;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        sbm.saveChanges(prefs);

        Intent intent = new Intent(AddBook.this,MainActivity.class);
        startActivity(intent);

        toast = Toast.makeText(context, "Book added", Toast.LENGTH_SHORT);
        toast.show();

        return super.onOptionsItemSelected(item);
    }




}
