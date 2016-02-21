package com.example.cop290.assignment1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseNotificationJSON {

    public int user_id;
    public String description;
    public int is_seen;
    public String created_at;
    public int id;

    public ParseNotificationJSON(String inputJSON) throws JSONException {
        JSONObject notification = new JSONObject(inputJSON);

        this.user_id = notification.getInt("user_id");
        this.description = notification.getString("description");
        this.is_seen = notification.getInt("is_seen");
        this.created_at = notification.getString("created_at");
        this.id = notification.getInt("id");
    }

    public String print()
    {
        String st = this.user_id+" "+this.description+" "+this.is_seen+" "+this.created_at+" "+this.id;
        return st;
    }
}
