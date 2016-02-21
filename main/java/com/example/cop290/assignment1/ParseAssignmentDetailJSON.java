package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseAssignmentDetailJSON {

    public ParseAssignmentJSON assignment;
    public ParseRegisteredJSON registered;
    public ParseSubmissionJSON[] submissions;
    public ParseCourseJSON course;

    public ParseAssignmentDetailJSON(String inputJSON) throws JSONException {
        JSONObject ass_detail_json = new JSONObject(inputJSON);

        this.assignment = new ParseAssignmentJSON(ass_detail_json.getJSONObject("assignment").toString());
        this.registered = new ParseRegisteredJSON(ass_detail_json.getJSONObject("registered").toString());
        //create submissions array
        JSONArray submissions = new JSONArray(ass_detail_json.getJSONArray("submissions"));
        this.submissions = new ParseSubmissionJSON[submissions.length()];

        for (int i = 0; i <submissions.length() ; i++)
        {
            this.submissions[i] = new ParseSubmissionJSON(submissions.get(i).toString());
        }
        this.course = new ParseCourseJSON(ass_detail_json.getJSONObject("course").toString());
    }

    public String print()
    {
        String st = this.assignment.print()+"\n"+this.registered.print()+"\n";

        for (int i = 0; i <this.submissions.length ; i++)
        {
            st+=this.submissions[i].print()+"\n";
        }

        st+=this.course.print();

        return st;
    }
}
