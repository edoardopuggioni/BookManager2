package com.example.android.bookmanager2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter
{
    private ArrayList<Book> book_list;
    private LayoutInflater thisInflater;
    int resource;

    public CustomArrayAdapter(Context context, ArrayList<Book> book_list)
    {
        super(context, 0, book_list );
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup parent )
    {
        // Get the data item for this position
        Book book = (Book) getItem(postion);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item_1,
                    parent, false);

        // Lookup view for data population
        TextView list_item_title = (TextView) convertView.findViewById(R.id.list_item_title);
        TextView list_item_author = (TextView) convertView.findViewById(R.id.list_item_author);
        ImageView list_item_image = (ImageView) convertView.findViewById(R.id.list_item_cover);


        // Populate the data into the template view using the data object
        list_item_title.setText(book.getTitle());
        list_item_author.setText(book.getAuthor());
        list_item_image.setImageResource(R.drawable.book_icon);

        // Return the completed view to render on screen
        return convertView;
    }
}
