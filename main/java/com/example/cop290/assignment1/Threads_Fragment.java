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
public class Threads_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_threads_, container, false);
        populateListView(view);

        return view;
    }

    String send_request(){

        // Send request to server and return string reply

        return "return course threads json";
    }

    void populateListView(View view) {

        ArrayList<ThreadsRowObject> gl=new ArrayList<ThreadsRowObject>();
        try {
            ParseCourseThreadsJSON p = new ParseCourseThreadsJSON(send_request());
            for (int i = 0; i <p.threads.length; i++)
            {
                gl.add(new ThreadsRowObject(p.threads[i].title,p.threads[i].updated_at));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        UserAdapter adapter = new UserAdapter(getActivity(), gl);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }


    public class UserAdapter extends ArrayAdapter<ThreadsRowObject> {
        public UserAdapter(Context context, ArrayList<ThreadsRowObject> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ThreadsRowObject user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_threads, parent, false);
            }
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView updatedon = (TextView) convertView.findViewById(R.id.updatedon);

            //SET ALL THE PARAMETERS HERE

            slno.setText((position+1)+"");
            title.setText(user.title);
            updatedon.setText(user.updatedon);

            return convertView;
        }
    }

    public class ThreadsRowObject
    {
        public String title;
        public String updatedon;

        public ThreadsRowObject(String  title, String updatedon) {
            this.title = title;
            this.updatedon = updatedon;
        }
    }
}
