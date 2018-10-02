package com.example.android.bookmanager2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //initializing input
        Button btn = (Button) findViewById(R.id.button);
        final EditText edTitle = (EditText)  findViewById(R.id.editTextTitle);
        final EditText edAuthor = (EditText) findViewById(R.id.editTextAuthor);
        final EditText edCourse = (EditText) findViewById(R.id.editTextCourse);
        final EditText edIsbn = (EditText) findViewById(R.id.editTextIsbn);
        final EditText edPrice = (EditText) findViewById(R.id.editTextPrice);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Book book = new Book(
                        edTitle.getText().toString(),
                        edAuthor.getText().toString(),
                        Integer.parseInt( edPrice.getText().toString()),
                        edIsbn.getText().toString(),
                        edCourse.getText().toString()


                );


                Toast toast = Toast.makeText(context,"ok", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Gson gson = new Gson();

    }







}
