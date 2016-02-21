package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.StringTokenizer;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseGradeJSON {

    public double weightage;
    public int user_id;
    public String name;
    public double out_of;
    public int registrered_course_id;
    public double score;
    public int id;

    public ParseGradeJSON(String inputJSON) throws JSONException {
        JSONObject grade = new JSONObject(inputJSON);

        this.weightage = grade.getDouble("weightage");
        this.user_id = grade.getInt("user_id");
        this.name = grade.getString("name");
        this.out_of = grade.getDouble("out_of");
        this.registrered_course_id = grade.getInt("registered_course_id");
        this.score = grade.getDouble("score");
        this.id = grade.getInt("id");
    }

    public String print()
    {
        String st = this.weightage+" "+this.user_id+" "+this.name+" "+this.out_of+" "+this.registrered_course_id+" "+this.score
                +" "+this.id;

        return st;
    }
}
