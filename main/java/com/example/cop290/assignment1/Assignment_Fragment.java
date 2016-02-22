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
public class Assignment_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment_, container, false);
        populateListView(view);

        return view;
    }

    String send_request(){

        // Send request to server and return string reply

        return "bla bla";
    }

    void populateListView(View view) {

        ArrayList<AssignmentsRowObject> gl=new ArrayList<AssignmentsRowObject>();
        try {
            ParseCourseAssignmentsJSON p = new ParseCourseAssignmentsJSON(send_request());
            for (int i = 0; i <p.assignments.length; i++)
            {
                gl.add(new AssignmentsRowObject(p.assignments[i].name,p.assignments[i].deadline));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_assignment, parent, false);
            }
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView time = (TextView) convertView.findViewById(R.id.time);

            //SET ALL THE PARAMETERS HERE

            slno.setText((position+1)+"");
            name.setText(user.name);
            time.setText(user.time);

            return convertView;
        }
    }

    public  class AssignmentsRowObject
    {
        public String name;
        public String time;

        public AssignmentsRowObject(String name, String time) {
            this.name = name;
            this.time = time;
        }
    }
}
