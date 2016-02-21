package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseRegisteredJSON {

    public String starting_date;
    public int id;
    public int professor;
    public int semester;
    public String ending_date;
    public int year_;
    public int course_id;

    public ParseRegisteredJSON(String inputJSON) throws JSONException {
        JSONObject reg_json = new JSONObject(inputJSON);

        this.starting_date = reg_json.getString("starting_date");
        this.id = reg_json.getInt("id");
        this.professor = reg_json.getInt("professor");
        this.semester = reg_json.getInt("semester");
        this.ending_date = reg_json.getString("ending_date");
        this.year_ = reg_json.getInt("year_");
        this.course_id = reg_json.getInt("course_id");
    }

    public String print()
    {
        String st = this.starting_date+" "+this.id+" "+this.professor+" "+this.semester+" "+this.ending_date+" "+this.year_+" "+
                this.course_id;
        return st;
    }
}
