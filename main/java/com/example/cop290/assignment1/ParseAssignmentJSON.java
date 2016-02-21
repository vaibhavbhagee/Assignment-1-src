package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseAssignmentJSON {

    public String name;
    public String file_;
    public String created_at;
    public int registered_course_id;
    public int late_days_allowed;
    public int type_;
    public String deadline;
    public int id;
    public String description;

    public ParseAssignmentJSON(String inputJSON) throws JSONException {
        JSONObject ass_json = new JSONObject(inputJSON);

        this.name = ass_json.getString("name");
        this.file_ = ass_json.getString("file_");
        this.created_at = ass_json.getString("created_at");
        this.registered_course_id = ass_json.getInt("registered_course_id");
        this.late_days_allowed = ass_json.getInt("late_days_allowed");
        this.type_ = ass_json.getInt("type_");
        this.deadline = ass_json.getString("deadline");
        this.id = ass_json.getInt("id");
        this.description = ass_json.getString("description");
    }

    public String print()
    {
        String st = this.name+" "+this.file_+" "+this.created_at+" "+this.registered_course_id+" "+this.late_days_allowed+" "+
                this.type_+" "+this.deadline+" "+this.id+" "+this.description;
        return st;
    }
}
