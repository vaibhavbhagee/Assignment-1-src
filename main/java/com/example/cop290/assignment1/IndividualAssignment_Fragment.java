package com.example.cop290.assignment1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndividualAssignment_Fragment extends Fragment {


    public IndividualAssignment_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_individual_assignment_, container, false);
        populateView(view);

        return view;
    }

    void populateView(View view) {

        // GET RELEVANT DATA HERE

        TextView assignment_name = (TextView) view.findViewById(R.id.assignment_name);
        WebView assignment_description = (WebView) view.findViewById(R.id.assignment_description);
        TextView assignment_created_at = (TextView) view.findViewById(R.id.assignment_created_at);
        TextView assignment_deadline = (TextView) view.findViewById(R.id.assignment_deadline);
        TextView assignment_time_remaining = (TextView) view.findViewById(R.id.assignment_time_remaining);
        TextView assignment_late_days = (TextView) view.findViewById(R.id.assignment_late_days);

        //SET THE PARAMETERS HERE

        UserAdapter adapter = new UserAdapter(getActivity(), gl);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    public class UserAdapter extends ArrayAdapter<AssignmentsRowObject> {
        public UserAdapter(Context context, ArrayList<AssignmentsRowObject> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AssignmentsRowObject user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_individual_assignment, parent, false);
            }
            TextView file_name = (TextView) convertView.findViewById(R.id.file_name);
            TextView file_created_at = (TextView) convertView.findViewById(R.id.file_created_at);

            //SET ALL THE PARAMETERS HERE
            

            return convertView;
        }
    }

}
