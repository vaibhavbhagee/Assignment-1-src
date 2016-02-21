package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseParticularThreadJSON {

    public ParseCourseJSON course;
    public String[] times_readable;
    public ParseCommentJSON[] comments;
    public ParseThreadJSON thread;
    public ParseUserJSON[] comment_users;

    public ParseParticularThreadJSON(String inputJSON) throws JSONException {
        JSONObject pthread = new JSONObject(inputJSON);

        this.course = new ParseCourseJSON(pthread.getJSONObject("course").toString());
        this.times_readable = (String[])(pthread.get("times_readable"));
        //create array of comment objects
        JSONArray comments = new JSONArray(pthread.getJSONArray("comments"));
        this.comments = new ParseCommentJSON[comments.length()];
        for (int j = 0; j <comments.length() ; j++)
        {
            this.comments[j] = new ParseCommentJSON(comments.get(j).toString());
        }
        this.thread = new ParseThreadJSON(pthread.getJSONObject("thread").toString());
        JSONArray comment_users = new JSONArray(pthread.getJSONArray("comment_users"));
        this.comment_users = new ParseUserJSON[comment_users.length()];
        for (int i = 0; i <comment_users.length() ; i++)
        {
            this.comment_users[i] = new ParseUserJSON(comment_users.get(i).toString());
        }
    }

    public String print()
    {
        String st = this.course.print()+"\n"+this.thread+"\n";
        for (int i = 0; i <this.comments.length ; i++)
        {
            st+=this.comments[i].print()+"\n";
        }
        for (int j = 0; j <this.comments.length ; j++)
        {
            st+=this.comments[j].print()+"\n";
        }
        return st;
    }
}
