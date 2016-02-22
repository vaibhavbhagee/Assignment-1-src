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
public class Grades_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_, container, false);

        //GET THE BUNDLE AND ALL THAT SHIZ AND PASS IT TO POPULATE

        populateListView(view);
        return view;
    }

    String send_request()
    {
        LoadData l = new LoadData();
        //Store response of get request for course grades
        String st = l.CourseGradesJSON;
        return st;
    }

    void populateListView(View view) {
        ArrayList<CourseGradesRowObject> gl=new ArrayList<CourseGradesRowObject>();

        try {
            ParseCourseGradesJSON p = new ParseCourseGradesJSON(send_request());

            for (int i = 0; i <p.grades.length; i++)
            {
                gl.add(new CourseGradesRowObject(p.grades[i].name,p.grades[i].score,p.grades[i].weightage,
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


    public class UserAdapter extends ArrayAdapter<CourseGradesRowObject> {
        public UserAdapter(Context context, ArrayList<CourseGradesRowObject> users) {
            super(context, 0, users);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CourseGradesRowObject item = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_grades, parent, false);
            }
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            TextView grade_item = (TextView) convertView.findViewById(R.id.grade_item);
            TextView score = (TextView) convertView.findViewById(R.id.score);
            TextView weight = (TextView) convertView.findViewById(R.id.weight);
            TextView absolute_marks = (TextView) convertView.findViewById(R.id.absolute_marks);

            //SET ALL THE PARAMETERS HERE

            slno.setText((position+1)+"");
            grade_item.setText(item.grade_item+"");
            score.setText(item.score+"");
            weight.setText(item.weight+"");
            absolute_marks.setText(item.absolute_marks+"");

            return convertView;
        }
    }

    public class CourseGradesRowObject
    {
        public String grade_item;
        public double score;
        public double weight;
        public double absolute_marks;

        public CourseGradesRowObject(String grade_item,double score, double weight,double absolute_marks)
        {
            this.grade_item = grade_item;
            this.score = score;
            this.weight = weight;
            this.absolute_marks = absolute_marks;
        }
    }

}
