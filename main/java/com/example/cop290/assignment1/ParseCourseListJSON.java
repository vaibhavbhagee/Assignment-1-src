package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseCourseListJSON {

public int current_sem;
public ParseCourseJSON[] courses;
public ParseUserJSON user;
public int current_year;

public ParseCourseListJSON(String inputJSON) throws JSONException {
        JSONObject cljson = new JSONObject(inputJSON);

        this.current_sem = cljson.getInt("current_sem");
        //get list of courses as array
        JSONArray course_array = cljson.getJSONArray("courses");
        //create array of course objects
        this.courses  = new ParseCourseJSON[course_array.length()];
        for(int i=0; i<course_array.length(); i++)
        {
        this.courses[i] = new ParseCourseJSON(course_array.get(i).toString());
        }
        this.user = new ParseUserJSON(cljson.getJSONObject("user").toString());
        this.current_year = cljson.getInt("current_year");
        }

public String print()
        {
        String st = this.current_sem+" ";

        for(int i=0; i<this.courses.length; i++)
        {
        st=st+this.courses[i].print()+"\n";
        }

        st+=this.user.print()+"\n";
        st+=this.current_year;

        return st;
        }
}