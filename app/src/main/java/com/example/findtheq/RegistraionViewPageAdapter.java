package com.example.findtheq;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.findtheq.fragments.FillingStationFragment;
import com.example.findtheq.fragments.UserFragment;

public class RegistraionViewPageAdapter extends FragmentStateAdapter {
    public RegistraionViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new UserFragment();
            case 1:
                return new FillingStationFragment();
            default:
                return new UserFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
