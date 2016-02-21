package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseCourseJSON {

    public String code;
    public String name;
    public String description;
    public int credits;
    public int id;
    public String l_t_p;

    public ParseCourseJSON(String courseJSON) throws JSONException {
        JSONObject course_json = new JSONObject(courseJSON);

        this.code = course_json.getString("code");
        this.name = course_json.getString("name");
        this.description = course_json.getString("description");
        this.credits = course_json.getInt("credits");
        this.id = course_json.getInt("id");
        this.l_t_p = course_json.getString("l_t_p");
    }

    public String print()
    {
        String st = this.code+" "+this.name+" "+this.description+" "+this.credits+" "+this.id+" "+this.l_t_p;

        return st;
    }
}