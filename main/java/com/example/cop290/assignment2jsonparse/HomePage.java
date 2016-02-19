package com.example.cop290.assignment2jsonparse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class HomePage extends AppCompatActivity {

    Context thisContext = this;

    public String ServerURL = "http://10.192.44.203:8000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context thisContext = this;

        final Bundle b = getIntent().getExtras();
        final String ServerURL = b.getString("ServerURL");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//        final Button button = (Button) findViewById(R.id.LogoutButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//            }

        //Button LogoutButton = (Button) findViewById(R.id.LogoutButton);
        //fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
          //      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //              .setAction("Action", null).show();
          //  }
        //});

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void LogOutClick(View view) {
        final String logoutString = ServerURL + "/default/logout.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, logoutString,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        Intent intent = new Intent(thisContext, Button.class);
                        startActivity(intent);

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage("Following error encountered:" + error.toString()).setNeutralButton("Close", null).show();

                        //Toast.makeText(Main4Activity.this, "Server Error. Please check your internet connection.", Toast.LENGTH_LONG).show();
                    }
                }
        );

        //Manages the queue of requests
        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

    }
}
