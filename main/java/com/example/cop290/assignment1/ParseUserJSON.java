package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseUserJSON {
    public String last_name;
    public String reset_password_key;
    public String registration_key;
    public int id;
    public String first_name;
    public String entry_no;
    public String email;
    public String username;
    public String registration_id;
    public String password;
    public int type_;
    public ParseUserJSON(String inputJSON) throws JSONException {
        JSONObject user_json = new JSONObject(inputJSON);
        this.last_name = user_json.getString("last_name");
        this.reset_password_key = user_json.getString("reset_password_key");
        this.registration_key = user_json.getString("registration_key");
        this.id = user_json.getInt("id");
        this.first_name = user_json.getString("first_name");
        this.entry_no = user_json.getString("entry_no");
        this.email = user_json.getString("email");
        this.username = user_json.getString("username");
        this.registration_id = user_json.getString("registration_id");
        try {
            this.password = user_json.getString("password");
        }catch(Exception e)
        {this.password = "";}
        this.type_ = user_json.getInt("type_");
    }

    public String print()
    {
        String st = this.last_name+" "+this.reset_password_key+" "+this.registration_key+" "+this.id+" "+this.first_name+" "
                +this.entry_no+" "+this.email+" "+this.username+" "+this.registration_id+" "+this.password+" "+this.type_;
        return st;
    }
}