package com.example.cop290.assignment1;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewParent;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import android.widget.RelativeLayout;
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
    boolean flag_nav;

    ParseCourseListJSON p=null;
    public String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String json_response = bundle.getString("UserJSON");
        try{
            ParseLoginJSON p = new ParseLoginJSON(json_response);
            this.name = p.user.first_name+" "+p.user.last_name;
            System.out.println("Testing name again "+this.name);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Bundle b = new Bundle();
        b.putString("name", this.name);
        HomePage_Fragment homepage = new HomePage_Fragment();
        homepage.setArguments(b);

        flag_nav = false;
        setContentView(R.layout.activity_home_page_);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, homepage).commit();





        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_homepage) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new HomePage_Fragment()).commit();
                }

                if (menuItem.getItemId() == 1 || menuItem.getItemId() == 2 || menuItem.getItemId() == 3 || menuItem.getItemId() == 4 || menuItem.getItemId() == 5 || menuItem.getItemId() == 6 || menuItem.getItemId() == 7 || menuItem.getItemId() == 8 || menuItem.getItemId() == 9 || menuItem.getItemId() == 0) {

                    on_loadCourse(p.courses[menuItem.getItemId()].code, menuItem);

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

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                on_refresh();

            }
        });

       /* ((TextView)findViewById(R.id.assignment_name)).setMovementMethod(new ScrollingMovementMethod());*/
        on_refresh();

    }

    void getCoursesAndAddToList(Menu menu) {
        LoadData l = new LoadData();
        String response = l.ListOfCoursesJSON;

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
    public void on_loadCourse(String s, MenuItem menuItem){
        final LoadData l = new LoadData();

        l.SetListOfAssignments(s);
        l.SetCoursegrades(s);
        l.SetCourseThreads(s);

        timer2(0,l, menuItem);

        l.flag[3] = false;
        l.flag[4] = false;
        l.flag[5] = false;

    }
    public void thread_onClick(View view) {

        // NAVIGATE TO INDIVIDUAL THREAD PAGE

        final LoadData l = new LoadData();
        l.setContext(thisContext);
        System.out.println(view.toString() + " ");
//        view.findViewById(R.id.listView.)

        RelativeLayout rl = (RelativeLayout)view;
        TextView t = (TextView) rl.findViewById(R.id.slno);
        int slno = Integer.parseInt(t.getText().toString()) - 1;

        try {
            ParseCourseThreadsJSON p = new ParseCourseThreadsJSON(l.ListCourseThreadsJSON);

            l.SetInfoOfThread(p.threads[slno].id + "");
            timer4(0,l);
            l.flag[7] = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void post_new_thread(View view) {
        //POST NEW THREAD HERE


//        android.support.design.widget.FloatingActionButton f = (android.support.design.widget.FloatingActionButton) view;
        RelativeLayout parent = (RelativeLayout)view.getParent();

        EditText title = (EditText) parent.findViewById(R.id.title);
        EditText description = (EditText) parent.findViewById(R.id.description);
        TextView course_code = (TextView) parent.findViewById(R.id.course_code);

        String title1 = title.getText().toString().replace(' ','+');
        String description1 = description.getText().toString().replace(' ','+');
        String course = course_code.getText().toString();

        if(title1.equals("")){
            Toast.makeText(HomePage_Activity.this,"Title is Empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(description1.equals("")){
            Toast.makeText(HomePage_Activity.this,"Description is Empty", Toast.LENGTH_LONG).show();
            return;
        }

        final LoadData l = new LoadData();
        System.out.println("testing " + title1);
        System.out.println("testing " + description1);
        System.out.println("testing" + course);

        l.SetCreateNewThread(title1, description1, course.toLowerCase());
        timer5(0, l, course);
        l.flag[8] = false;

        Toast.makeText(HomePage_Activity.this,"New thread posted", Toast.LENGTH_LONG).show();

    }

    public void post_thread_comment(View view) {

        RelativeLayout parent = (RelativeLayout)view.getParent();

        EditText comment = (EditText) parent.findViewById(R.id.new_comment);
        TextView thread_id = (TextView) parent.findViewById(R.id.thread_id);

        String comment1 = comment.getText().toString();
        String thread_id1 = thread_id.getText().toString();

        if(comment1.equals("")){
            Toast.makeText(HomePage_Activity.this,"Comment is Empty", Toast.LENGTH_LONG).show();
            return;
        }

        // POST THE THREAD COMMENT HERE
        //SEND APPROPRIATE REQUESTS

        final LoadData l = new LoadData();
        System.out.println("testing " + comment1);
        System.out.println("testing " + thread_id1);

        l.SetAddCommentToThread(thread_id1, comment1.replace(' ','+'));
        timer7(0,l, thread_id1);
        l.flag[9] = false;

    }

    public void navigate_to_assignment(View view) {

        //NAVIGATE TO THE ASSIGNMENT PAGE
        //SEND APPROPRIATE REQUESTS
        RelativeLayout rl = (RelativeLayout)view;
        TextView t = (TextView) rl.findViewById(R.id.slno);
        int slno = Integer.parseInt(t.getText().toString()) - 1;

        final LoadData l = new LoadData();
        l.setContext(thisContext);
        System.out.println(view.toString() + " ");
//        view.findViewById(R.id.listView.)

        try {
            ParseCourseAssignmentsJSON p = new ParseCourseAssignmentsJSON(l.ListOfAllAssignmentsJSON);

            l.SetInfoOfAssignment(p.assignments[slno].id + "");
            timer3(0,l);
            l.flag[6] = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void on_refresh(){
        final LoadData l = new LoadData();
        l.setContext(thisContext);

        l.SetCourses();
        timer(0,l, 0);
        l.flag[0] = false;


        l.SetNotifications();
        timer(0,l, 1);
        l.flag[1] = false;

        l.SetGrades();
        timer(0,l, 2);
        l.flag[2] = false;

        View v = mNavigationView.getHeaderView(0);
        TextView t = ((TextView) v.findViewById(R.id.textView));
        t.setText(this.name);




        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerView, new HomePage_Fragment()).commit();
    }

    public boolean timer(final int x, final LoadData l, final int i){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this,"Connection Timed Out", Toast.LENGTH_LONG).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
                else if(l.flag[i]){
                    //System.out.println("done \t"+l.ListOfCoursesJSON);
                    if(!flag_nav && i==0){
                        Menu menu = mNavigationView.getMenu();
                        getCoursesAndAddToList(menu);
                        flag_nav = true;
                    }
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    timer(x+1,l, i);
                    //System.out.println("pocessing \t" + l.ListOfCoursesJSON);
                }
            }
        }.start();
        return true;
    }
    public boolean timer2(final int x, final LoadData l, final MenuItem menuItem){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this, "Connection Timed Out", Toast.LENGTH_LONG).show();
                }
                else if(l.flag[3] && l.flag[4] && l.flag[5]){

                    Bundle bundle = new Bundle();
                    bundle.putString("CourseID", p.courses[menuItem.getItemId()].code);
                    Course_Fragment newcoursefragment = new Course_Fragment();
                    newcoursefragment.setArguments(bundle);
                    System.out.println("done \t" + l.ListCourseThreadsJSON);
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, newcoursefragment).commit();

                } else {
                    timer2(x+1,l, menuItem);
                    System.out.println("pocessing \t" + l.flag[3] + " " + l.flag[4] + " " + l.flag[5] + l.ListOfCoursesJSON);
                }
            }
        }.start();
        return true;
    }
    public boolean timer3(final int x, final LoadData l){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this, "Connection Timed Out", Toast.LENGTH_LONG).show();
                }
                else if(l.flag[6]){
                    System.out.println("done \t" + l.InfoOfParticularAssignmentJSON);
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new IndividualAssignment_Fragment()).commit();

                } else {
                    timer3(x+1,l);
                    System.out.println("pocessing \t" + l.flag[6] + l.InfoOfParticularAssignmentJSON);
                }
            }
        }.start();
        return true;
    }
    public boolean timer4(final int x, final LoadData l){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this, "Connection Timed Out", Toast.LENGTH_LONG).show();
                }
                else if(l.flag[7]){
                    System.out.println("done \t"+l.InfoThreadJSON);
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new IndividualThread_Fragment()).commit();

                } else {
                    timer4(x+1,l);
                    System.out.println("pocessing \t" + l.flag[7]+l.InfoThreadJSON);
                }
            }
        }.start();
        return true;
    }
    public boolean timer5(final int x, final LoadData l, final String course){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this,"Connection Timed Out", Toast.LENGTH_LONG).show();
                }
                else if(l.flag[8]){
                    l.SetCourseThreads(course);
                    timer6(0,l,course);
                    l.flag[5] = false;
                } else {
                    timer5(x+1,l, course);
                }
            }
        }.start();
        return true;
    }
    public boolean timer7(final int x, final LoadData l, final String thread_id1){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this,"Connection Timed Out", Toast.LENGTH_LONG).show();
                }
                else if(l.flag[9]){
                    Toast.makeText(HomePage_Activity.this,"New comment added", Toast.LENGTH_LONG).show();
                    l.SetInfoOfThread(thread_id1);
                    timer4(0,l);
                    l.flag[7] = false;
                    System.out.println("done \t"+l.AddCommentThreadJSON);
//                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.containerView, new IndividualThread_Fragment()).commit();

                } else {
                    timer7(x+1,l, thread_id1);
                    System.out.println("pocessing \t" + l.flag[9]+l.AddCommentThreadJSON);
                }
            }
        }.start();
        return true;
    }
    public boolean timer6(final int x, final LoadData l,final String course_id){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(x==100){
                    Toast.makeText(HomePage_Activity.this, "Connection Timed Out", Toast.LENGTH_LONG).show();
                }
                else if(l.flag[5]){
                    System.out.println("done \t"+l.InfoThreadJSON);
                    Bundle bundle = new Bundle();
                    bundle.putString("CourseID", course_id);

                    Course_Fragment newcoursefragment = new Course_Fragment();
                    newcoursefragment.setArguments(bundle);
                    System.out.println("done \t" + l.ListCourseThreadsJSON);
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, newcoursefragment).commit();

                } else {
                    timer6(x+1,l,course_id);
                    System.out.println("pocessing \t" + l.flag[5]+l.InfoThreadJSON);
                }
            }
        }.start();
        return true;
    }
}

