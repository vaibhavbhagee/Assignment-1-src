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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Button extends AppCompatActivity {


    Context thisContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String loginString1 = "http://10.192.44.203:8000/default/login.json?userid=";
                final String loginString2 = "&password=";

                final String userID = ((EditText) findViewById(R.id.usernameBox)).getText().toString();
                final String passwd = ((EditText) findViewById(R.id.passwordBox)).getText().toString();

                final String loginRequest = loginString1 + userID + loginString2 + passwd;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                        new Response.Listener<String>() {
                            @Override
                            //On valid response
                            public void onResponse(String response) {

                                try {
                                    JSONObject responseJSON = new JSONObject(response);
                                    JSONObject name = responseJSON.getJSONObject("user");

                                    new AlertDialog.Builder(thisContext).setTitle("Response").setMessage( name.toString() ).setNeutralButton("Close", null).show();

                                }catch(Exception e){e.printStackTrace();}

                            }
                        },
                        //Launched when server return error
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                                //Toast.makeText(Main4Activity.this, "Server Error. Please check your internet connection.", Toast.LENGTH_LONG).show();
                            }
                        }) {

                };
                //Manages the queue of requests
                RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
                requestQueue.add(stringRequest);



                //Snackbar.make(view, "Sent request", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
