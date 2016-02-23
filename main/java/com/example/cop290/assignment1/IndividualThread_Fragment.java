package com.example.cop290.assignment1;


import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

    String send_request(){

        LoadData l = new LoadData();
        //Store response of get request for individual threads
        String st = l.InfoThreadJSON;
        return st;
    }

    String getUsername(int id) throws JSONException {
        //function to get username by making a get request to /users/user.json/id
        LoadData l = new LoadData();
        l.ObtainUserInfo(id+"");
        timer_user_info(l);
        l.flag[10] = false;

        JSONObject user_json = new JSONObject(l.UserInfoJSON);
        JSONObject user = new JSONObject(user_json.getJSONObject("user").toString());

        return user.getString("first_name")+user.getString("last_name");
    }

    public boolean timer_user_info(final LoadData l){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(l.flag[10]){
                    //System.out.println("done \t"+l.ListOfCoursesJSON);
//                    swipeRefreshLayout.setRefreshing(false);
//                    Toast.makeText(HomePage_Activity.this,"Done", Toast.LENGTH_LONG).show();
                } else {
                    timer_user_info(l);
                    //System.out.println("pocessing \t" + l.ListOfCoursesJSON);
                }
            }
        }.start();
        return true;
    }

    void populateView(View view) {

        ArrayList<CommentsRowObject> gl=new ArrayList<CommentsRowObject>();
        // GET RELEVANT DATA HERE

        TextView thread_user = (TextView) view.findViewById(R.id.thread_user);
        TextView thread_title = (TextView) view.findViewById(R.id.thread_title);
        TextView thread_description = (TextView) view.findViewById(R.id.thread_description);
        TextView thread_created_at = (TextView) view.findViewById(R.id.thread_created_at);
        TextView thread_updated_at = (TextView) view.findViewById(R.id.thread_updated_at);

        try {
            ParseParticularThreadJSON p = new ParseParticularThreadJSON(send_request());
            for (int i = 0; i <p.comments.length; i++)
            {
                gl.add(new CommentsRowObject(p.comment_users[i].first_name+p.comment_users[i].last_name,p.comments[i].description,p.times_readable[i]));
            }

            thread_user.setText(getUsername(p.thread.user_id));
            thread_title.setText(p.thread.title);
            thread_description.setText(p.thread.description);
            thread_created_at.setText(p.thread.created_at);
            thread_updated_at.setText(p.thread.updated_at);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



        //SET THE PARAMETERS HERE

        UserAdapter adapter = new UserAdapter(getActivity(), gl);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }


    public class UserAdapter extends ArrayAdapter<CommentsRowObject> {
        public UserAdapter(Context context, ArrayList<CommentsRowObject> users) {
            super(context, 0, users);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentsRowObject user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_individual_thread, parent, false);
            }
            TextView user_name = (TextView) convertView.findViewById(R.id.user);
            TextView comment = (TextView) convertView.findViewById(R.id.comment);
            TextView time_readable = (TextView) convertView.findViewById(R.id.time_readable);

            //SET ALL THE PARAMETERS HERE

            user_name.setText(user.user_name);
            comment.setText(user.comment);
            time_readable.setText(user.time_readable);


            return convertView;
        }
    }

    public class CommentsRowObject
    {
        public String user_name;
        public String comment;
        public String time_readable;

        public CommentsRowObject(String user_name, String comment, String time_readable) {
            this.user_name = user_name;
            this.comment = comment;
            this.time_readable = time_readable;
        }
    }
}
