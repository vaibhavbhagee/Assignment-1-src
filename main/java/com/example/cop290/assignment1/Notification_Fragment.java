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
public class Notification_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_, container, false);

        populateListView(view);


        return view;
    }


    String send_request(){

        // Send request to server and return string reply

        return "bla bla";
    }


    void populateListView(View view) {

        ArrayList<ParseNotificationsJSON> test_array = new ArrayList<ParseNotificationsJSON>();

        for(int i=0; i<100; ++i) test_array.add( new ParseNotificationsJSON(""+i));

        UserAdapter adapter = new UserAdapter(getActivity(), test_array);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    class ParseNotificationsJSON{
        public String s;
        public ParseNotificationsJSON(String s){
            this.s = s;
        }
    }

    public class UserAdapter extends ArrayAdapter<ParseNotificationsJSON> {
        public UserAdapter(Context context, ArrayList<ParseNotificationsJSON> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ParseNotificationsJSON user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_notification, parent, false);
            }
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
            tvName.setText(user.s);
            tvHome.setText(user.s+"bla bla");
            return convertView;
        }
    }
}
