package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParsePostCommentJSON {

    public ParseCommentJSON comment;
    public String user_name;
    public ParseUserJSON user;
    public boolean success;
    public String time_readable;


    public ParsePostCommentJSON(String inputJSON) throws JSONException {
        JSONObject post_comment = new JSONObject(inputJSON);

        this.comment = new ParseCommentJSON(post_comment.getJSONObject("comment").toString());
        this.user_name = post_comment.getString("user_name");
        this.user = new ParseUserJSON(post_comment.getJSONObject("user").toString());
        this.success = post_comment.getBoolean("success");
        this.time_readable = post_comment.getString("time_readable");
    }

    public String print()
    {
        String st = this.comment.print()+"\n"+this.user_name+" "+this.user+"\n"+this.success+" "+this.time_readable;
        return st;
    }
}
