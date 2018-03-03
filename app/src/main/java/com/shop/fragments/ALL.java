package com.shop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.shop.R;
import com.shop.adapter.myAdapter;
import com.shop.config.Config;
import com.shop.model.Model;


public class ALL extends Fragment {

    public static ALL newInstance() {
        return new ALL();
    }
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private myAdapter adapter;
    private ArrayList<Model> list;

    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_frame_latout, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclearvier);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        Operation();
    }




    private void Operation() {

        progressBar.setVisibility(View.VISIBLE);

        list = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Config.main_url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list.clear();
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray array = response.getJSONArray("result");
                    Gson gson = null;
                    Model model = null;
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        gson = new Gson();
                        model = gson.fromJson(object.toString(), Model.class);
                        list.add(model);
                    }
                    adapter = new myAdapter(getActivity(), list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new myAdapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
