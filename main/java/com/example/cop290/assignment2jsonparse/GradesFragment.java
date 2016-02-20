package com.example.cop290.assignment2jsonparse;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aayan Kumar on 20-02-2016.
 */
public class GradesFragment extends Fragment{
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.grades_layout,container,false);
        return myView;
    }
}
