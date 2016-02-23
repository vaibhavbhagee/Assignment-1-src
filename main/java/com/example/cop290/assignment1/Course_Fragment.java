package com.example.cop290.assignment1;

import android.os.Bundle;
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

public class Course_Fragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4 ;

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

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        //Receiving data from bundle
        String courseid = getArguments().getString("CourseID");

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

        public MyAdapter(FragmentManager fm) {
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
                case 1 : return new Resources_Fragment();
                case 2 : return new Threads_Fragment();
                case 3 : return new Grades_Fragment();
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
                case 1 :
                    return "Resources";
                case 2 :
                    return "Threads";
                case 3:
                    return "Grades";
            }
            return null;
        }
    }

}