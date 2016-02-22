package com.example.cop290.assignment1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.Console;

public class HomePage_Activity extends AppCompatActivity {

    Context thisContext = this;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SwipeRefreshLayout swipeRefreshLayout;

    ParseCourseListJSON p=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home_page_);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new HomePage_Fragment()).commit();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_homepage) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new HomePage_Fragment()).commit();
                }

                if (menuItem.getItemId() == 1 || menuItem.getItemId() == 2 || menuItem.getItemId() == 3 || menuItem.getItemId() == 4 || menuItem.getItemId() == 5 || menuItem.getItemId() == 6 || menuItem.getItemId() == 7 || menuItem.getItemId() == 8 || menuItem.getItemId() == 9 || menuItem.getItemId() == 0) {

                    Bundle bundle = new Bundle();
                    bundle.putString("CourseID", p.courses[menuItem.getItemId()].code);

                    Course_Fragment newcoursefragment = new Course_Fragment();
                    newcoursefragment.setArguments(bundle);

                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, newcoursefragment).commit();
                }

                if (menuItem.getItemId() == R.id.nav_notifications) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Notification_Fragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_grades) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new GlobalGrades_Fragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_logout) {


                    // Yahaan Log Out kar dena

                    Intent intent = new Intent(thisContext, Login_Activity.class);
                    startActivity(intent);

                }
                return false;
            }
        });

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name, R.string.app_name);
        on_refresh();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                on_refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        Menu menu = mNavigationView.getMenu();
        getCoursesAndAddToList(menu);
    }

    void getCoursesAndAddToList(Menu menu)
    {
        LoadData l = new LoadData();
        String response = l.ListOfCoursesJSON;
        Toast.makeText(HomePage_Activity.this, l.ListOfCoursesJSON, Toast.LENGTH_LONG).show();

        try {
            ParseCourseListJSON course_list_json = new ParseCourseListJSON(response);
            p = new ParseCourseListJSON(response);

            for(int i=0;i<course_list_json.courses.length;i++) {

                menu.add(R.id.CourseList, i, Menu.NONE, course_list_json.courses[i].code.toUpperCase());
                p.courses[i].code = p.courses[i].code.toUpperCase();
                System.out.println(course_list_json.courses[i].code.toUpperCase());
            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void thread_onClick(View view) {

        // NAVIGATE TO INDIVIDUAL THREAD PAGE

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new IndividualThread_Fragment()).commit();
    }

    public void post_thread_comment(View view) {

        // POST THE THREAD COMMENT HERE
        //SEND APPROPRIATE REQUESTS
    }

    public void navigate_to_assignment(View view) {

        //NAVIGATE TO THE ASSIGNMENT PAGE
        //SEND APPROPRIATE REQUESTS

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new IndividualAssignment_Fragment()).commit();

    }


    public void on_refresh(){
        LoadData l = new LoadData();
        l.setContext(thisContext);
        l.SetBasicInfoForUser();

        View v = mNavigationView.getHeaderView(0);
        TextView t = ((TextView) v.findViewById(R.id.textView));
        t.setText("naam daal de");


        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new HomePage_Fragment()).commit();
    }
}