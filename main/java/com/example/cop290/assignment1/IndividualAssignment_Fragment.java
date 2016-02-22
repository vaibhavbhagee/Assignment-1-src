package com.example.cop290.assignment1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
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


    String send_request() {

        LoadData l = new LoadData();
        //Store response of get request for particular assignments
        String st = l.InfoOfParticularAssignmentJSON;
        return st;
    }

    void populateView(View view) {

        ArrayList<SubmissionsRowObject> gl=new ArrayList<SubmissionsRowObject>();
        // GET RELEVANT DATA HERE

        TextView assignment_name = (TextView) view.findViewById(R.id.assignment_name);
        TextView assignment_description = (TextView) view.findViewById(R.id.assignment_description);
        TextView assignment_created_at = (TextView) view.findViewById(R.id.assignment_created_at);
        TextView assignment_deadline = (TextView) view.findViewById(R.id.assignment_deadline);
        TextView assignment_time_remaining = (TextView) view.findViewById(R.id.assignment_time_remaining);
        TextView assignment_late_days = (TextView) view.findViewById(R.id.assignment_late_days);

        //SET THE PARAMETERS HERE
        try
        {
            ParseAssignmentDetailJSON p = new ParseAssignmentDetailJSON(send_request());
            for (int i = 0; i <p.submissions.length; i++)
            {
                gl.add(new SubmissionsRowObject(p.submissions[i].name,p.submissions[i].created_at,p.submissions[i].file_));
            }

            assignment_name.setText(p.assignment.name);
            assignment_description.setText(Html.fromHtml(p.assignment.description));
            assignment_created_at.setText(p.assignment.created_at);
            assignment_deadline.setText(p.assignment.deadline);
//            assignment_time_remaining.setText(p.assignment.);
            assignment_late_days.setText(p.assignment.late_days_allowed);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        UserAdapter adapter = new UserAdapter(getActivity(), gl);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    public class UserAdapter extends ArrayAdapter<SubmissionsRowObject> {
        public UserAdapter(Context context, ArrayList<SubmissionsRowObject> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SubmissionsRowObject user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_individual_assignment, parent, false);
            }
            TextView file_name = (TextView) convertView.findViewById(R.id.file_name);
            TextView file_created_at = (TextView) convertView.findViewById(R.id.file_created_at);

            //SET ALL THE PARAMETERS HERE

            file_name.setText(user.filename);
            file_created_at.setText(user.created_at);

            return convertView;
        }
    }

    public class SubmissionsRowObject
    {
        public String filename;
        public String created_at;
        public String file;

        public SubmissionsRowObject(String filename, String created_at, String file) {
            this.filename = filename;
            this.created_at = created_at;
            this.file = file;
        }
    }

}
