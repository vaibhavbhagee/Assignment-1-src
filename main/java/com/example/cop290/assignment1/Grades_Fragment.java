package com.example.cop290.assignment1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Grades_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_, container, false);

        //GET THE BUNDLE AND ALL THAT SHIZ AND PASS IT TO POPULATE

        populateListView(view, "PASS THE BUNDLE ELEMENT HERE");
        return view;
    }


    void populateListView(View view, String s) {

        try {
            ParseAllGradesJSON p = new ParseAllGradesJSON("PASS THE BUNDLE ELEMENT HERE");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        UserAdapter adapter = new UserAdapter(getActivity(), new ArrayList<ParseAllGradesJSON>());
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }


    public class UserAdapter extends ArrayAdapter<ParseAllGradesJSON> {
        public UserAdapter(Context context, ArrayList<ParseAllGradesJSON> users) {
            super(context, 0, users);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ParseAllGradesJSON user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_grades, parent, false);
            }
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            TextView grade_item = (TextView) convertView.findViewById(R.id.grade_item);
            TextView score = (TextView) convertView.findViewById(R.id.score);
            TextView weight = (TextView) convertView.findViewById(R.id.weight);
            TextView absolute_marks = (TextView) convertView.findViewById(R.id.absolute_marks);

            //SET ALL THE PARAMETERS HERE

            return convertView;
        }
    }

}
