package com.shop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.R;


public class Electronics extends Fragment {
    public static Electronics newInstance() {
        return new Electronics();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_latout, container, false);

        return view;
    }

}