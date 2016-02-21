package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 20/2/16.
 */
public class ParseLoginJSON {

    public String JSONObj;
    public boolean success;
    public ParseUserJSON user;
    public ParseLoginJSON(String JSON) throws JSONException {
        JSONObject responseJSON = new JSONObject(JSON);
        this.JSONObj = JSON;

        this.success = responseJSON.getBoolean("success");
        if(this.success)
        {
            JSONObject user_obj = responseJSON.getJSONObject("user");
            //Instantiate a new use object
            this.user = new ParseUserJSON(user_obj.toString());
        }
        System.out.println(responseJSON);
    }

    public String print()
    {
        String st = this.user.print()+" "+this.success;
        return st;
    }

    /*public static void main(String args[])
    {
        String json_val = "\"user\": {\"last_name\": \"Jindal\",\"reset_password_key\": \"\",\"registration_key\": \"\",\"id\": 4,\"first_name\": \"Shubham\",\"entry_no\": \"2011CS50300\",\"email\": \"cs5110300@cse.iitd.ac.in\",\"username\": \"cs5110300\",\"registration_id\": \"\",\"password\": \"shubham\",\"type_\": 0},\"success\": true}";
        try {
            ParseLoginJSON p = new ParseLoginJSON(json_val);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/
}