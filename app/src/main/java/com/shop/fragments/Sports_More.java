package com.shop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.R;


public class Sports_More extends Fragment {
    public static Sports_More newInstance() {
        return new Sports_More();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_latout, container, false);

        return view;
    }

}