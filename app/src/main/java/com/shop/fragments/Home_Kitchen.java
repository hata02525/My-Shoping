package com.shop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.R;


public class Home_Kitchen extends Fragment {
    public static Home_Kitchen newInstance() {
        return new Home_Kitchen();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_latout, container, false);

        return view;
    }

}