package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseCourseGradesJSON {

    public ParseRegisteredJSON registered;
    public ParseCourseJSON course;
    public ParseGradeJSON[] grades;
    public int year;
    public int sem;

    public ParseCourseGradesJSON(String inputJSON) throws JSONException {
        JSONObject course_grades = new JSONObject(inputJSON);

        this.registered = new ParseRegisteredJSON(course_grades.getJSONObject("registered").toString());
        this.course = new ParseCourseJSON(course_grades.getJSONObject("course").toString());
        JSONArray grades = course_grades.getJSONArray("grades");
        this.grades = new ParseGradeJSON[grades.length()];
        for (int i = 0; i <this.grades.length ; i++)
        {
            this.grades[i] = new ParseGradeJSON(grades.get(i).toString());
        }
        this.year = course_grades.getInt("year");
        this.sem = course_grades.getInt("sem");
    }

    public String print()
    {
        String st = this.registered.print()+"\n"+this.course.print()+"\n";
        for (int i = 0; i <this.grades.length ; i++)
        {
            st+=this.grades[i].print()+"\n";
        }
        st+=this.year+" "+this.sem;

        return st;
    }
}
