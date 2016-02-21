package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseCourseThreadsJSON {

    public ParseRegisteredJSON registered;
    public ParseCourseJSON course;
    public int year;
    public int sem;
    public ParseThreadJSON[] threads;

    public ParseCourseThreadsJSON(String inputJSON) throws JSONException {
        JSONObject course_threads = new JSONObject(inputJSON);

        this.registered = new ParseRegisteredJSON(course_threads.getJSONObject("registered").toString());
        this.course= new ParseCourseJSON(course_threads.getJSONObject("course").toString());
        this.year = course_threads.getInt("year");
        this.sem = course_threads.getInt("sem");

        JSONArray threads = new JSONArray(course_threads.getJSONArray("threads"));
        this.threads = new ParseThreadJSON[threads.length()];

        for (int i = 0; i <threads.length() ; i++)
        {
            this.threads[i] = new ParseThreadJSON(threads.get(i).toString());
        }
    }

    public String print()
    {
        String st = this.registered.print()+"\n"+this.course.print()+"\n"+this.year+" "+this.sem+"\n";

        for (int i = 0; i <this.threads.length ; i++)
        {
            st+=this.threads[i].print()+"\n";
        }

        return st;
    }
}
