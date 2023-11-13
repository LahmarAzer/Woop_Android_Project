package com.esprit.validationlastwoop;

import android.os.Bundle;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class viewpageradapter extends FragmentStateAdapter {
    public viewpageradapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @Override
    public int getItemCount() {
        return 0;
    }
    @NonNull

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Various_work_fragment();
            case 1:
                //return new TabTwo();
            case 2:
               // return new TabThree();
            default:
                return new Various_work_fragment();
        }
    }

}
