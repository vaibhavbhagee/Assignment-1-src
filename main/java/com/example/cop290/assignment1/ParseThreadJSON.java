package com.example.cop290.assignment1;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseThreadJSON {

    public int user_id;
    public String description;
    public String title;
    public String created_at;
    public int registered_course_id;
    public String updated_at;
    public int id;

    public ParseThreadJSON(String inputJSON) throws JSONException {
        JSONObject thread = new JSONObject(inputJSON);

        this.user_id = thread.getInt("user_id");
        this.description = thread.getString("description");
        this.title = thread.getString("title");
        this.created_at = thread.getString("created_at");
        this.registered_course_id = thread.getInt("registered_course_id");
        this.updated_at = thread.getString("updated_at");
        this.id = thread.getInt("id");
    }

    public String print()
    {
        String st = this.user_id+" "+this.description+" "+this.title+" "+this.created_at+" "+this.registered_course_id+" "+
                this.updated_at+" "+this.id;
        return st;
    }
}