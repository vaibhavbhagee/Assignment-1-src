package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseAllGradesJSON {

    public ParseCourseJSON[] courses;
    public ParseGradeJSON[] grades;
    public ParseAllGradesJSON(String inputJSON) throws JSONException {
        JSONObject grades = new JSONObject(inputJSON);

        JSONArray course_array = grades.getJSONArray("courses");
        JSONArray grade_array = grades.getJSONArray("grades");
        this.courses = new ParseCourseJSON[course_array.length()];
        for (int i = 0; i <course_array.length() ; i++)
        {
            this.courses[i] = new ParseCourseJSON(course_array.get(i).toString());
        }
        //Add grades array here
        this.grades = new ParseGradeJSON[grade_array.length()];
        for (int j = 0; j < grade_array.length(); j++) {
            this.grades[j] = new ParseGradeJSON(grade_array.get(j).toString());
        }
    }

    public String print()
    {
        String st = "";
        for (int i = 0; i <this.courses.length ; i++)
        {
            st+=this.courses[i].print()+" ";
        }
        for (int j = 0; j <this.grades.length ; j++)
        {
            st+=this.grades[j].print()+" ";
        }

        return st;

    }
}
