package com.example.demo2;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

/*
In a fragment oriented architecture, activities become NAVIGATIONAL CONTAINERS, which are primarily
responsible for navigation to other activities, presenting fragments and passing data. Fragments
enable re-ues of parts of your screen including views and event logic across disparate activities
(i.e. using the same list across different data sources within an app)

In our fragment oriented architecture we will split responsibilities:

Activties - Navigation to other activities, presenting navigational components, hiding and showing
relevant fragments using `FragmentManager`, receiving data from intents and passing data between fragments

Fragments - contain most layouts and views displaying relevant content, event handling logic with
relevant views, retrieval and storage of data
 */
public class MainActivity extends AppCompatActivity {

    // FragmentManager allows you to add the fragment dynamically, allowing you to add, remove and replace fragments in the layout of your activity at runtime
    private static FragmentManager fm;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                // Depending on which navigation item is clicked, decide which fragment to show
                case R.id.navigation_home:
                    // If the home button is pressed we want to call on the fragment illustrated by `fragment_list.xml`
                    ListFragment frag = new ListFragment();
                    // Begin the transaction, replace the contents of the container with the new fragment, and commit (complete the changes you made above)
                    fm.beginTransaction().replace(R.id.frame, frag).addToBackStack("").commit();
                    /*
                    The purpoes of addToBackStack is so that back-click functionality on the phone works properly,
                    this is SUPER USEFUL especially if you add multiple changed to the transaction
                     */
                    return true;
                case R.id.navigation_dashboard:
                    OtherFragment otherFrag = new OtherFragment();
                    fm.beginTransaction().replace(R.id.frame, otherFrag).addToBackStack("").commit();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm = getSupportFragmentManager();
    }

}
