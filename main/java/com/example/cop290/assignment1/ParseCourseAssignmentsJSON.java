package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseCourseAssignmentsJSON {

    public ParseAssignmentJSON[] assignments;
    public ParseRegisteredJSON registered;
    public ParseCourseJSON course;
    public int year;
    public int sem;
    public ParseCourseAssignmentsJSON(String inputJSON) throws JSONException {
        JSONObject course_assign = new JSONObject(inputJSON);

        //create array of course assignments
        JSONArray ass_array = new JSONArray(course_assign.getJSONArray("assignments"));
        this.assignments = new ParseAssignmentJSON[ass_array.length()];
        for (int i = 0; i <ass_array.length() ; i++)
        {
            this.assignments[i] = new ParseAssignmentJSON(ass_array.get(i).toString());
        }
        //create registered object
        this.registered = new ParseRegisteredJSON(course_assign.getJSONObject("registered").toString());
        this.course = new ParseCourseJSON(course_assign.getJSONObject("course").toString());
        this.year = course_assign.getInt("year");
        this.sem = course_assign.getInt("sem");
    }

    public String print()
    {
        String st = "";
        for (int i = 0; i <this.assignments.length ; i++)
        {
            st+=this.assignments[i].print()+"\n";
        }
        st+=this.registered.print()+"\n"+this.course.print()+"\n"+this.year+" "+this.sem;

        return st;
    }
}
