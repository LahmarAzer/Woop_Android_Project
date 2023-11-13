package com.esprit.validationlastwoop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;


import androidx.appcompat.app.AppCompatActivity;



public class SearchActivity extends AppCompatActivity {
    private Various_work_fragment various_work_fragment = new Various_work_fragment();
    Button frag1,frag2,frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_interface);

    }

}
