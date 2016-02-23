package com.example.cop290.assignment1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class Course_Fragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public String coursecode = "";
    public static int int_items = 3 ;

    public Course_Fragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.fragment_course_, null);

        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        //Receiving data from bundle
        String courseid = getArguments().getString("CourseID");
        this.coursecode = courseid;

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager(),courseid));

        ((Toolbar)getActivity().findViewById(R.id.toolbar)).setTitle(courseid);


        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter{

        public String course = "";
        public MyAdapter(FragmentManager fm,String course) {
            this.course = course;
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Assignment_Fragment();
               case 1 : return new Threads_Fragment(this.course);
                case 2 : return new Grades_Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "Assignment";
                //case 1 :
                //    return "Resources";
                case 1 :
                    return "Threads";
                case 2:
                    return "Grades";
            }
            return null;
        }
    }

/*    public void on_load(String s){
        final LoadData l = new LoadData();

        l.SetListOfAssignments(s);
        timer(l, 3);
        l.flag[3] = false;


        l.SetCoursegrades(s);
        timer(l, 4);
        l.flag[4] = false;

        l.SetCourseThreads(s);
        timer(l, 5);
        l.flag[5] = false;

    }*/

/*    public boolean timer(final LoadData l, final int i){

        new CountDownTimer(50, 1000) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                if(l.flag[i]){
                    System.out.println("done \t"+l.ListOfCoursesJSON);
                } else {
                    timer(l, i);
                    System.out.println("pocessing \t" + l.ListOfCoursesJSON);
                }
            }
        }.start();
        return true;
    }*/

}