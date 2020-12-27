package com.example.practica4_animaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageFragment extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Animales", "Musica", "Video", "Pelota" };

    int numOfTabs=4;

    public PageFragment(FragmentManager fm, int behavior) {
        super(fm, behavior);
        numOfTabs=behavior;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
