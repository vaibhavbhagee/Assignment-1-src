package com.example.cop290.assignment1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
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

        // Send request to server and return string reply

        return "bla bla";
    }


    void populateListView(View view) {

        ArrayList<NotificationsRowObject> gl=new ArrayList<NotificationsRowObject>();
        try {
            ParseNotificationsJSON p = new ParseNotificationsJSON(send_request());
//            gl = new ArrayList<GradesRowObject>();
            for (int i = 0; i <p.notifications.length; i++)
            {
                DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
                Date date = (Date)formatter.parse(p.notifications[i].created_at);
                Date d = new Date();
                long getmills = d.getTime()-date.getTime();
                long hrs = getmills/3600000;
                long hr_residue = getmills%3600000;
                long min = hr_residue/60000;

                gl.add(new NotificationsRowObject(p.notifications[i].description,hrs+" hours, "+min+" minutes ago"));
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
            LinearLayout layout = (LinearLayout) convertView.findViewById((R.id.layout));
            TextView slno = (TextView) convertView.findViewById(R.id.slno);
            WebView notification = (WebView) convertView.findViewById(R.id.notification);
            TextView time = (TextView) convertView.findViewById(R.id.time);

            // RENDER THE INFORMATION IN THE RESPECTIVE FIELDS
            slno.setText((position+1)+"");
            notification.loadDataWithBaseURL("",user.notification,"text/html","UTF-8","");
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
