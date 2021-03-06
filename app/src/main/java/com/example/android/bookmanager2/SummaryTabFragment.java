package com.example.android.bookmanager2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryTabFragment extends Fragment
{
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate( R.layout.fragment_summary_tab, container, false );

        Bundle args = getArguments();

        // Below there is a way to know the position of the current tab, don't need it here.
        //String text = "Collection tab: received " + Integer.toString(args.getInt(ARG_OBJECT));

        // this is how you call only one instance of SimpleBookManager:

        TextView t;
        t = (TextView) rootView.findViewById(R.id.summary_number);
        t.setText( "You have " + SimpleBookManager.getBookManager().count() + " books in your library");
        t = (TextView) rootView.findViewById(R.id.summary_total);
        t.setText( String.valueOf( SimpleBookManager.getBookManager().getTotalCost() ) );
        t = (TextView) rootView.findViewById(R.id.summary_most);
        t.setText( String.valueOf( SimpleBookManager.getBookManager().getMaxPrice() ) );
        t = (TextView) rootView.findViewById(R.id.summary_least);
        t.setText( String.valueOf( SimpleBookManager.getBookManager().getMinPrice() ) );
        t = (TextView) rootView.findViewById(R.id.summary_average);
        t.setText( String.valueOf( SimpleBookManager.getBookManager().getMeanPrice() ) );

        return rootView;
    }
}
