package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseCommentJSON {

    public int user_id;
    public String description;
    public String created_at;
    public int thread_id;
    public int id;

    public ParseCommentJSON(String inputJSON) throws JSONException {
        JSONObject comment = new JSONObject(inputJSON);

        this.user_id = comment.getInt("user_id");
        this.description = comment.getString("description");
        this.created_at = comment.getString("created_at");
        this.thread_id = comment.getInt("thread_id");
        this.id = comment.getInt("id");
    }

    public String print()
    {
        String st = this.user_id+" "+this.description+" "+this.created_at+" "+this.thread_id+" "+this.id;
        return st;
    }
}
