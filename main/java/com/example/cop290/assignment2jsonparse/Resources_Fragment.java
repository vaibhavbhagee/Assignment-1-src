package com.example.cop290.assignment2jsonparse;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cop290.assignment2jsonparse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Resources_Fragment extends Fragment {


    public Resources_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resources_, container, false);
    }

}
