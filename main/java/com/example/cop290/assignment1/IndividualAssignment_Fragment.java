package com.example.cop290.assignment1;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


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

        TextView textview= (TextView) view.findViewById (R.id.assignment_description);
        textview.setMovementMethod(new ScrollingMovementMethod());

        TextView tv = (TextView) view.findViewById( R.id.assignment_name);
        tv.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.backfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Bundle bundle = new Bundle();
                bundle.putString("CourseID", "col732");
*/
                /*Course_Fragment newcoursefragment = new Course_Fragment();
                newcoursefragment.setArguments(bundle);
                FragmentTransaction xfragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                xfragmentTransaction.replace(R.id.containerView, newcoursefragment).commit();*/
                /*FragmentTransaction xfragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                xfragmentTransaction.replace(R.id.containerView, new course()).commit();*/
                //getActivity().getSupportFragmentManager().popBackStack();
                //Intent intent = new Intent(thisContext, HomePage_Activity.class);
                //startActivity(intent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                ///         .setAction("Action", null).show();
            }
        });


        return view;
    }


    String send_request() {

        LoadData l = new LoadData();
        //Store response of get request for particular assignments
        String st = l.InfoOfParticularAssignmentJSON;
        return st;
    }

    String ret_duration(String inputDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = (Date)formatter.parse(inputDate);
        Date d = new Date();
        long getmills = date.getTime()-d.getTime();
        long days = getmills/86400000;
        long days_residue = getmills%86400000;
        long hrs = days_residue/3600000;
        long hr_residue = getmills%3600000;
        long min = hr_residue/60000;

        String st = "";
        if(days !=0)
        {
            if (hrs !=0)
            {
                if (min !=0)
                {
                    st+=days+" days, "+hrs+" hrs, "+min+" minutes";
                }
                else
                {
                    st+=days+" days, "+hrs+" hrs ";
                }
            }
            else
            {
                if (min !=0)
                {
                    st+=days+" days, "+min+" minutes";
                }
                else
                {
                    st+=days+" days";
                }
            }
        }
        else
        {
            if (hrs !=0)
            {
                if (min !=0)
                {
                    st+=hrs+" hrs, "+min+" minutes";
                }
                else
                {
                    st+=hrs+" hrs ";
                }
            }
            else
            {
                if (min !=0)
                {
                    st+=min+" minutes";
                }
                else
                {
                    st+="";
                }
            }
        }

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
            assignment_time_remaining.setText(ret_duration(p.assignment.deadline));
            assignment_late_days.setText(p.assignment.late_days_allowed+"");

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
            TextView file_src = (TextView) convertView.findViewById(R.id.file_src);

            //SET ALL THE PARAMETERS HERE

            file_name.setText(user.filename);
            file_created_at.setText(user.created_at);
            file_src.setText(user.file);

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
