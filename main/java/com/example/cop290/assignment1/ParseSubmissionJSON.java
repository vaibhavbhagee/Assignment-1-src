package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseSubmissionJSON {

    public int user_id;
    public String name;
    public String created_at;
    public String file_;
    public int event_id;
    public int id;

    public ParseSubmissionJSON(String inputJSON) throws JSONException {
        JSONObject submission = new JSONObject(inputJSON);

        this.user_id = submission.getInt("user_id");
        this.name = submission.getString("name");
        this.created_at = submission.getString("created_at");
        this.file_ = submission.getString("file_");
        this.event_id = submission.getInt("event_id");
        this.id = submission.getInt("id");
    }

    public String print()
    {
        String st = this.user_id+" "+this.name+" "+this.created_at+" "+this.file_+" "+this.event_id+" "+this.id;
        return st;
    }
}
