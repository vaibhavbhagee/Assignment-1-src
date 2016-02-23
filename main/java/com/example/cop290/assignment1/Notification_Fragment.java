package com.example.cop290.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_, container, false);
        populateListView(view);
        return view;
    }


    String send_request(){

        LoadData l = new LoadData();
        //Store response of get request for all notifications
        String st = l.AllNotificationsJSON;
        return st;
    }

    String ret_duration(String inputDate) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = (Date)formatter.parse(inputDate);
        Date d = new Date();
        long getmills = d.getTime()-date.getTime();
        long days = getmills/86400000;
        long days_residue = getmills%86400000;
        long hrs = days_residue/3600000;
        long hr_residue = getmills%3600000;
        long min = hr_residue/60000;

        String st = "";
        if(days !=0)
        {
            if (hrs !=0)
            {
                if (min !=0)
                {
                    st+=days+" days, "+hrs+" hrs, "+min+" minutes";
                }
                else
                {
                    st+=days+" days, "+hrs+" hrs ";
                }
            }
            else
            {
                if (min !=0)
                {
                    st+=days+" days, "+min+" minutes";
                }
                else
                {
                    st+=days+" days";
                }
            }
        }
        else
        {
            if (hrs !=0)
            {
                if (min !=0)
                {
                    st+=hrs+" hrs, "+min+" minutes";
                }
                else
                {
                    st+=hrs+" hrs ";
                }
            }
            else
            {
                if (min !=0)
                {
                    st+=min+" minutes";
                }
                else
                {
                    st+="";
                }
            }
        }

        return st;
    }


    void populateListView(View view) {

        ArrayList<NotificationsRowObject> gl=new ArrayList<NotificationsRowObject>();
        try {
            ParseNotificationsJSON p = new ParseNotificationsJSON(send_request());
//            gl = new ArrayList<GradesRowObject>();
            for (int i = 0; i <p.notifications.length; i++)
            {
                gl.add(new NotificationsRowObject(p.notifications[i].description,ret_duration(p.notifications[i].created_at)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        UserAdapter adapter = new UserAdapter(getActivity(), gl);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


    public class UserAdapter extends ArrayAdapter<NotificationsRowObject> {
        public UserAdapter(Context context, ArrayList<NotificationsRowObject> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            NotificationsRowObject user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.format_notification, parent, false);
            }
            RelativeLayout layout = (RelativeLayout) convertView.findViewById((R.id.layout));
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            TextView notification = (TextView) convertView.findViewById(R.id.notification);
            TextView time = (TextView) convertView.findViewById(R.id.time);

            // RENDER THE INFORMATION IN THE RESPECTIVE FIELDS
            slno.setText((position+1)+"");
            notification.setText(Html.fromHtml(user.notification));
            //notification.loadData(user.notification,"text/html; charset=UTF-8",null);
            time.setText(user.time);

            return convertView;
        }
    }

    public class NotificationsRowObject
    {
        public String notification;
        public String time;

        public NotificationsRowObject(String notification,String time) {
            this.notification = notification;
            this.time = time;
        }
    }
}
