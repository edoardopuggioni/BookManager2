package com.example.android.bookmanager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class AddBook extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //initializing input
        Button btn = (Button) findViewById(R.id.button);
        final EditText edTitle = (EditText)  findViewById(R.id.editTextTitle);
        final EditText edAuthor = (EditText) findViewById(R.id.editTextAuthor);
        final EditText edCourse = (EditText) findViewById(R.id.editTextCourse);
        final EditText edIsbn = (EditText) findViewById(R.id.editTextIsbn);
        final EditText edPrice = (EditText) findViewById(R.id.editTextPrice);



        btn.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = getApplicationContext();

                String title;
                String author;
                String course;
                String isbn;
                String price_string; // later to be converted to int value
                Integer price;
                SimpleBookManager sbm = SimpleBookManager.getBookManager();
                Toast toast;

                title = edTitle.getText().toString();
                if( title.matches("") )
                {
                    toast = Toast.makeText(context,"Title missing", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                author = edAuthor.getText().toString();
                if( author.matches("") )
                {
                    toast = Toast.makeText(context,"Author missing", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                course = edCourse.getText().toString();
                if( course.matches("") )
                {
                    toast = Toast.makeText(context,"Course missing", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                isbn = edIsbn.getText().toString();
                if( isbn.matches("") )
                {
                    toast = Toast.makeText(context,"ISBN missing", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                price_string = edPrice.getText().toString();
                if( price_string.matches("") )
                {
                    toast = Toast.makeText(context,"Price missing", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                try
                {
                    price = Integer.parseInt( edPrice.getText().toString() );
                }
                catch(Exception e)
                {
                    toast = Toast.makeText(context,"Price format error", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                sbm.createBook(
                        edTitle.getText().toString(),
                        edAuthor.getText().toString(),
                        Integer.parseInt( edPrice.getText().toString()),
                        edIsbn.getText().toString(),
                        edCourse.getText().toString()
                );

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                sbm.saveChanges(prefs);
                Intent intent = new Intent(AddBook.this, MainActivity.class);
                startActivity(intent);
                toast = Toast.makeText(context,"Book added", Toast.LENGTH_SHORT);
                toast.show();
            }
        } );

        Gson gson = new Gson();
    }







}
