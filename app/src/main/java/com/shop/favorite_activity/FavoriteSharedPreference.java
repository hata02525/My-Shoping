package com.shop.favorite_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shop.model.Model;


public class FavoriteSharedPreference {

    public static final String PREFS_NAME = "NKDROID_APP";
    public static final String FAVORITES = "Favorite";

    public FavoriteSharedPreference() {
        super();
    }


    public void storeFavorites(Context context, List<Model> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings= context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
    }

    public ArrayList<Model> loadFavorites(Context context) {
        SharedPreferences settings;
        List<Model> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Model[] favoriteItems = gson.fromJson(jsonFavorites,Model[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Model>(favorites);
        } else
            return null;

        return (ArrayList<Model>) favorites;
    }


    public void addFavorite(Context context, Model beanSampleList) {
        List<Model> favorites = loadFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Model>();
        favorites.add(beanSampleList);
        storeFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Model beanSampleList) {
        ArrayList<Model> favorites = loadFavorites(context);
        if (favorites != null) {
            favorites.remove(beanSampleList);
            storeFavorites(context, favorites);
        }
    }




}
