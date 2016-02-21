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
public class GlobalGrades_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_global_grades_, container, false);
        populateListView(view);

        return view;
    }

    String send_request(){

        // Send request to server and return string reply

        return "bla bla";
    }

    void populateListView(View view) {

        try {
            ParseAllGradesJSON p = new ParseAllGradesJSON(send_request());
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_global_grades, parent, false);
            }
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            TextView course = (TextView) convertView.findViewById(R.id.course);
            TextView grade_item = (TextView) convertView.findViewById(R.id.grade_item);
            TextView score = (TextView) convertView.findViewById(R.id.score);
            TextView weight = (TextView) convertView.findViewById(R.id.weight);
            TextView absolute_marks = (TextView) convertView.findViewById(R.id.absolute_marks);

            //SET ALL THE PARAMETERS HERE

            return convertView;
        }
    }

}
