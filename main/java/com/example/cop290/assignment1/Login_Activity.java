package com.example.cop290.assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class Login_Activity extends AppCompatActivity {

    Context thisContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();


               //Intent intent = new Intent(thisContext, HomePage_Activity.class);
                //startActivity(intent);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               ///         .setAction("Action", null).show();
            }
        });
    }



    public void login()
    {
        final String ServerURL = "http://10.192.44.203:8000";
        //final String ServerURL = "http://10.208.20.164:8000/";

        final String loginString1 = "/default/login.json?userid=";
        final String loginString2 = "&password=";

        final String userID = ((EditText) findViewById(R.id.usernameBox)).getText().toString();
        final String passwd = ((EditText) findViewById(R.id.passwordBox)).getText().toString();

        final String loginRequest = ServerURL + loginString1 + userID + loginString2 + passwd;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        try {
                            //JSONObject responseJSON = new JSONObject(response);
                            ParseLoginJSON login_response = new ParseLoginJSON(response);
                            System.out.println(response);

                            if( ! login_response.success ) {
                                new AlertDialog.Builder(thisContext).setTitle("Response").setMessage("Login failure\nPlease check your Credentials").setNeutralButton("Close", null).show();
                            }
                            else
                            {
                                //JSONObject name = responseJSON.getJSONObject("user");
                                //new AlertDialog.Builder(thisContext).setTitle("Response").setMessage( login_response.print() ).setNeutralButton("Close", null).show();

                                Intent intent = new Intent(thisContext, HomePage_Activity.class);
                                Bundle b = new Bundle();

                                LoadData l = new LoadData();
                                l.LoginResponseJSON = response;

                                b.putString("ServerURL", ServerURL);
                                b.putString("UserJSON", response);

                                l.json_response = response;

                                intent.putExtras(b);

                                startActivity(intent);
                            }

                        }catch(Exception e){e.printStackTrace();}

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage("No Internet Connection\nPlease check your connection").setNeutralButton("Close", null).show();

                        //Toast.makeText(Main4Activity.this, "Server Error. Please check your internet connection.", Toast.LENGTH_LONG).show();
                    }
                }) {

        };



        //Manages the queue of requests
        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);


    }
}
