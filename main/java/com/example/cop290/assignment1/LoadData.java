package com.example.cop290.assignment1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aayan Kumar on 22-02-2016.
 */
public class LoadData extends Activity {

    public static String ListOfCoursesJSON;
    public static String AllNotificationsJSON;
    public static String AllGradesJSON;
    public static String ListOfAllAssignmentsJSON;
    public static String InfoOfParticularAssignmentJSON;
    public static String CourseGradesJSON;
    public static String ListCourseThreadsJSON;
    public static String InfoThreadJSON;
    public static String CreateNewThreadJSON;
    public static String AddCommentThreadJSON;
    public static String UserInfoJSON;
    public static Context thisContext = null;

    private String ServerURL = "http://10.192.11.155:8000";
    private String loginRequest;

    public void setContext(Context c)
    {
        thisContext = c;
    }

    public void SetBasicInfoForUser()
    {
        loginRequest = ServerURL + "/courses/list.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        ListOfCoursesJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

        loginRequest = ServerURL + "/default/notifications.json";
        stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        AllNotificationsJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

        loginRequest = ServerURL + "/default/grades.json";
        stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        AllGradesJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

    }


    public void SetCourseInfoForUser(String CourseCode)
    {
        loginRequest = ServerURL + "/courses/course.json/"+CourseCode+"/assignments";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        ListOfAllAssignmentsJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

        loginRequest = ServerURL + "/courses/course.json/"+CourseCode+"/grades";
        stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        CourseGradesJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);


        loginRequest = ServerURL + "/courses/course.json/"+CourseCode+"/threads";
        stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        ListCourseThreadsJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

    }


    public void SetInfoOfAssignment(String ass_no)
    {
        loginRequest = ServerURL + "/courses/assignment.json/"+ass_no;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        InfoOfParticularAssignmentJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }


    public void SetInfoOfThread(String thread)
    {
        loginRequest = ServerURL + "/threads/thread.json/"+thread;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        InfoThreadJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }


    public void SetCreateNewThread(String title, String desc, String coursecode)
    {
        loginRequest = ServerURL + "/threads/new.json?title="+title+"&description="+desc+"&course_code="+coursecode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        CreateNewThreadJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }


    public void SetAddCommentToThread(String thread, String desc)
    {
        loginRequest = ServerURL + "/threads/post_comment.json?thread_id="+thread+"&description="+desc;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        AddCommentThreadJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }


    public void ObtainUserInfo(String id)
    {
        loginRequest = ServerURL + "/users/user.json/"+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        UserInfoJSON = response;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }


    public void LogoutUser()
    {
        loginRequest = ServerURL + "/default/logout.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Kat gaya tera behenchod. Aise kata:" + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }

}
