package com.example.practica4_animaciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tab = (TabLayout)findViewById(R.id.tab);
        TabItem tab1 = (TabItem)findViewById(R.id.tab1);
        TabItem tab2 = (TabItem)findViewById(R.id.tab2);
        TabItem tab3 = (TabItem)findViewById(R.id.tab3);
        TabItem tab4 = (TabItem)findViewById(R.id.tab4);
        ViewPager pager = (ViewPager)findViewById(R.id.pager);

        pager.setAdapter(new PageFragment(getSupportFragmentManager(),
                tab.getTabCount()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);





    }
}