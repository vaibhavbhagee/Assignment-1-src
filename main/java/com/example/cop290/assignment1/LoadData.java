package com.example.cop290.assignment1;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

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

    public static boolean[] flag = new boolean[11];

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

    private String ServerURL = "http://10.192.44.203:8000";
    //private String ServerURL = "http://10.208.20.164:8000/";
    private String loginRequest;

    public void setContext(Context c)
    {
        thisContext = c;
    }

    public void SetCourses() {
        loginRequest = ServerURL + "/courses/list.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        ListOfCoursesJSON = response;
                        System.out.println(ListOfCoursesJSON);
                        flag[0] = true;
                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage("basicuserinfoError: " + error.toString()).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //0

    public void SetNotifications(){
        loginRequest = ServerURL + "/default/notifications.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        AllNotificationsJSON = response;
                        flag[1] = true;
                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage("notifsError: " + error.toString()).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //1

    public void SetGrades(){
        loginRequest = ServerURL + "/default/grades.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        AllGradesJSON = response;
                        flag[2] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "gradesError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

    }   //2

    public void SetListOfAssignments(String CourseCode) {
        loginRequest = ServerURL + "/courses/course.json/" + CourseCode + "/assignments";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        ListOfAllAssignmentsJSON = response;
                        flag[3] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage("assError: " + error.toString()).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //3

    public void SetCoursegrades(String CourseCode) {

        loginRequest = ServerURL + "/courses/course.json/" + CourseCode + "/grades";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        CourseGradesJSON = response;
                        flag[4] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage("coursegradesError: " + error.toString()).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //4

    public void SetCourseThreads(String CourseCode){

        loginRequest = ServerURL + "/courses/course.json/"+CourseCode+"/threads";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        ListCourseThreadsJSON = response;
                        flag[5] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "theaddsError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);

    }   //5

    public void SetInfoOfAssignment(String ass_no){
        loginRequest = ServerURL + "/courses/assignment.json/"+ass_no;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        InfoOfParticularAssignmentJSON = response;
                        flag[6] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "onesasinfoError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //6

    public void SetInfoOfThread(String thread){
        loginRequest = ServerURL + "/threads/thread.json/"+thread;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        InfoThreadJSON = response;
                        flag[7] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "infothreadError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }       //7

    public void SetCreateNewThread(String title, String desc, String coursecode){
        loginRequest = ServerURL + "/threads/new.json?title="+title+"&description="+desc+"&course_code="+coursecode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        CreateNewThreadJSON = response;
                        flag[8] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "newthreadError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //8

    public void SetAddCommentToThread(String thread, String desc){
        loginRequest = ServerURL + "/threads/post_comment.json?thread_id="+thread+"&description="+desc;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        AddCommentThreadJSON = response;
                        flag[9] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "commentError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //9

    public void ObtainUserInfo(String id){
        loginRequest = ServerURL + "/users/user.json/"+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, loginRequest ,
                new Response.Listener<String>() {
                    @Override
                    //On valid response
                    public void onResponse(String response) {

                        UserInfoJSON = response;
                        flag[10] = true;

                    }
                },
                //Launched when server return error
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "obtainuserError: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }   //10

    public void LogoutUser(){
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
                        new AlertDialog.Builder(thisContext).setTitle("Error").setMessage( "Error: " + error.toString() ).setNeutralButton("Close", null).show();

                    }
                }) {

        };

        RequestQueue requestQueue = Volley.newRequestQueue(thisContext);
        requestQueue.add(stringRequest);
    }

}
