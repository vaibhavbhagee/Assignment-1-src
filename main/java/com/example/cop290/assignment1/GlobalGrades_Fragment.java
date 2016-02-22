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
        String st = "{\"courses\": [{\"code\": \"cop290\", \"name\": \"Design Practices in Computer Science\", \"description\": \"Design Practices in Computer Science.\", \"credits\": 3, \"id\": 1, \"l_t_p\": \"0-0-6\"}, {\"code\": \"cop290\", \"name\": \"Design Practices in Computer Science\", \"description\": \"Design Practices in Computer Science.\", \"credits\": 3, \"id\": 1, \"l_t_p\": \"0-0-6\"}, {\"code\": \"cop290\", \"name\": \"Design Practices in Computer Science\", \"description\": \"Design Practices in Computer Science.\", \"credits\": 3, \"id\": 1, \"l_t_p\": \"0-0-6\"}], \"grades\": [{\"weightage\": 10.0, \"user_id\": 4, \"name\": \"Assignment 1\", \"out_of\": 15.0, \"registered_course_id\": 1, \"score\": 15.0, \"id\": 10}, {\"weightage\": 15.0, \"user_id\": 4, \"name\": \"Assignment 2\", \"out_of\": 20.0, \"registered_course_id\": 1, \"score\": 20.0, \"id\": 11}, {\"weightage\": 25.0, \"user_id\": 4, \"name\": \"Minor 1\", \"out_of\": 30.0, \"registered_course_id\": 1, \"score\": 15.0, \"id\": 12}]}";
        return st;
    }

    void populateListView(View view) {

        ArrayList<GradesRowObject> gl=new ArrayList<GradesRowObject>();
        try {
            ParseAllGradesJSON p = new ParseAllGradesJSON(send_request());
//            gl = new ArrayList<GradesRowObject>();
            for (int i = 0; i <p.courses.length; i++)
            {
                gl.add(new GradesRowObject(p.courses[i].code,p.grades[i].name,p.grades[i].score,p.grades[i].weightage,
                        p.grades[i].out_of));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        UserAdapter adapter = new UserAdapter(getActivity(), gl);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }


    public class UserAdapter extends ArrayAdapter<GradesRowObject> {
        public UserAdapter(Context context, ArrayList<GradesRowObject> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GradesRowObject item = getItem(position);
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
            slno.setText((position+1)+"");
            course.setText(item.course.toUpperCase()+"");
            grade_item.setText(item.grade_item);
            score.setText(item.score+"/"+item.absolute_marks);
            weight.setText("Weightage: " + item.weight);
           // absolute_marks.setText(item.absolute_marks+"");

            return convertView;
        }
    }

    public class GradesRowObject
    {
        public String course;
        public String grade_item;
        public double score;
        public double weight;
        public double absolute_marks;

        public GradesRowObject(String course,String grade_item,double score, double weight,double absolute_marks)
        {
            this.course = course;
            this.grade_item = grade_item;
            this.score = score;
            this.weight = weight;
            this.absolute_marks = absolute_marks;
        }
    }

}


