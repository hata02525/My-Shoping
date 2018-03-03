package com.shop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.R;


public class Baby_Kids extends Fragment {
    public static Baby_Kids newInstance() {
        return new Baby_Kids();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frame_latout, container, false);

        return view;
    }

}