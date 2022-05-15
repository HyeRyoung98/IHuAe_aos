package com.example.ihuae_aos;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainPagerAdapter extends FragmentStateAdapter {

    public MainPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new HomeFragment();
                break;
            case 1 :
                fragment = new DateFragment();
                break;
            case 2 :
                fragment = new EditFragment();
                break;
            case 3 :
                fragment = new MessageFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
