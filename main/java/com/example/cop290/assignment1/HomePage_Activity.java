package com.example.cop290.assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.Console;

public class HomePage_Activity extends AppCompatActivity {

    Context thisContext = this;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_);



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
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

                if (menuItem.getItemId() == 1 || menuItem.getItemId() == 2 ||menuItem.getItemId() == 3 || menuItem.getItemId() == 4 || menuItem.getItemId() == 5 || menuItem.getItemId() == 6 || menuItem.getItemId() == 7 || menuItem.getItemId() == 8 || menuItem.getItemId() == 9 || menuItem.getItemId() == 0)  {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new Course_Fragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_notifications) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new Notification_Fragment()).commit();
                }

                if (menuItem.getItemId() == R.id.nav_grades) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new GlobalGrades_Fragment()).commit();
                }

              /*  if (menuItem.getItemId() == R.id.nav_courses) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new Course_Fragment()).commit();
                }*/

                if (menuItem.getItemId() == R.id.nav_logout) {


                    // Yahaan Log Out kar dena

                    Intent intent = new Intent(thisContext, Login_Activity.class);
                    startActivity(intent);

                }
                return false;
            }
        });
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        set_param();
        mDrawerToggle.syncState();

        /*final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
               // navigationView.getMenu().findItem(R.id.nav_notifications).setChecked(true);
                return true;
            }
        });*/

        Menu menu = mNavigationView.getMenu();

        getCoursesAndAddToList(menu);
    }


    void set_param(){
        View v = mNavigationView.getHeaderView(0);
        TextView t = ((TextView) v.findViewById(R.id.textView));
        t.setText("naam daal de");
    }

    void getCoursesAndAddToList(Menu menu)
    {
        //REQUEST SERVER FOR COURSES
        //http://10.192.44.203:8000/courses/list.json

        String response = "{\"current_sem\": 2, \"courses\": [{\"code\": \"cop290\", \"name\": \"Design Practices in Computer Science\", \"description\": \"Design Practices in Computer Science.\", \"credits\": 3, \"id\": 1, \"l_t_p\": \"0-0-6\"}, {\"code\": \"col740\", \"name\": \"Software Engineering\", \"description\": \"Introduction to the concepts of Software Design and Engineering.\", \"credits\": 4, \"id\": 3, \"l_t_p\": \"3-0-2\"}, {\"code\": \"csl732\", \"name\": \"Cloud Computing and Virtualisation\", \"description\": \"Introduction to Cloud Computing and Virtualisation.\", \"credits\": 4, \"id\": 4, \"l_t_p\": \"3-0-2\"}, {\"code\": \"col380\", \"name\": \"Parallel Programming\", \"description\": \"Introduction to concurrent systems and programming style.\", \"credits\": 4, \"id\": 5, \"l_t_p\": \"3-0-2\"}, {\"code\": \"csl859\", \"name\": \"Advanced Computer Graphics\", \"description\": \"Graduate course on Advanced Computer Graphics\", \"credits\": 4, \"id\": 7, \"l_t_p\": \"3-0-2\"}], \"user\": {\"username\": \"cs5110300\", \"first_name\": \"Shubham\", \"last_name\": \"Jindal\", \"entry_no\": \"2011CS50300\", \"registration_id\": \"\", \"id\": 4, \"reset_password_key\": \"\", \"type_\": 0, \"registration_key\": \"\", \"email\": \"cs5110300@cse.iitd.ac.in\"}, \"current_year\": 2016}";

        try {
            ParseCourseListJSON course_list_json = new ParseCourseListJSON(response);

            for(int i=0;i<course_list_json.courses.length;i++) {

                menu.add(R.id.CourseList, i, Menu.NONE, course_list_json.courses[i].code.toUpperCase());

                System.out.println(course_list_json.courses[i].code.toUpperCase());
            }

        }catch(Exception e){e.printStackTrace();}

    }
}