package com.example.android.bookmanager2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class CollectionTabFragment extends Fragment
{
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate( R.layout.fragment_collection_tab, container, false );

        Bundle args = getArguments();

        // Below there is a way to know the position of the current tab, don't need it here.
        //String text = "Collection tab: received " + Integer.toString(args.getInt(ARG_OBJECT));

        ListView listView;
        SimpleBookManager sbm = SimpleBookManager.getBookManager();
        ArrayList<Book> book_list = sbm.getAllBooks();

        listView = (ListView) rootView.findViewById(R.id.books_list);

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
//        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter<Book>(
//                getActivity().getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                (ArrayList<Book>) book_list );

        // I try to use the CustomArrayAdapter

        CustomArrayAdapter arrayAdapter = new CustomArrayAdapter( getActivity().getApplicationContext(), sbm.getAllBooks() );

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener( new CustomOnItemClickListener() );

        return rootView;
    }

    public class CustomOnItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Intent intent = new Intent( getActivity().getApplicationContext(), BookDetails.class );
            String message = String.valueOf(position);
            intent.putExtra( EXTRA_MESSAGE, message );
            startActivity(intent);
        }
    }
}
