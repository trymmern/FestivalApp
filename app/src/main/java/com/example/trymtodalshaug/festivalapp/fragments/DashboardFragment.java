package com.example.trymtodalshaug.festivalapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trymtodalshaug.festivalapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }
}
