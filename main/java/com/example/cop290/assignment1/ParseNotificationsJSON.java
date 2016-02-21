package com.example.cop290.assignment1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vaibhav on 21/2/16.
 */
public class ParseNotificationsJSON {

    public ParseNotificationJSON[] notifications;

    public ParseNotificationsJSON(String inputJSON) throws JSONException {
        JSONObject notif = new JSONObject(inputJSON);

        JSONArray notifications = notif.getJSONArray("notifications");
        this.notifications = new ParseNotificationJSON[notifications.length()];
        for (int i = 0; i <notifications.length() ; i++)
        {
            this.notifications[i] = new ParseNotificationJSON(notifications.get(i).toString());
        }
    }
}
