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
public class IndividualThread_Fragment extends Fragment {


    public IndividualThread_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_individual_thread_, container, false);
        populateView(view);

        return view;
    }

    void populateView(View view) {

        // GET RELEVANT DATA HERE

        TextView thread_user = (TextView) view.findViewById(R.id.thread_user);
        TextView thread_title = (TextView) view.findViewById(R.id.thread_title);
        TextView thread_description = (TextView) view.findViewById(R.id.thread_description);
        TextView thread_created_at = (TextView) view.findViewById(R.id.thread_created_at);
        TextView thread_updated_at = (TextView) view.findViewById(R.id.thread_updated_at);

        //SET THE PARAMETERS HERE

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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_individual_thread, parent, false);
            }
            TextView user_name = (TextView) convertView.findViewById(R.id.user);
            TextView comment = (TextView) convertView.findViewById(R.id.comment);
            TextView time_readable = (TextView) convertView.findViewById(R.id.time_readable);

            //SET ALL THE PARAMETERS HERE

            return convertView;
        }
    }
}
