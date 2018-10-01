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

        String text = "Collection tab: received " + Integer.toString(args.getInt(ARG_OBJECT));
        TextView t = (TextView) rootView.findViewById(R.id.summary_text);
        t.setText(text);

        return rootView;
    }
}
