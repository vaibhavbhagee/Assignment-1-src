package com.example.cop290.assignment1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePage_Fragment extends Fragment {

    public HomePage_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page_, container, false);
//        Bundle b = getActivity().getIntent().getExtras();
//        if(b!=null){
//            System.out.println("checking name "+b.getString("name"));
//            TextView user_name = (TextView) view.findViewById(R.id.name);
//            user_name.setText(b.getString("name"));
//        }
        try{
            LoadData l = new LoadData();
            ParseLoginJSON p = new ParseLoginJSON(l.json_response);
            String name = p.user.first_name+" "+p.user.last_name;
            TextView user_name = (TextView) view.findViewById(R.id.name);
            user_name.setText(name);
            System.out.println("Testing name again "+name);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

}
