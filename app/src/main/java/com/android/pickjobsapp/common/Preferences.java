package com.android.pickjobsapp.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {
    private Context context;
    private SharedPreferences preferences ;

    public Preferences(Context context) {
        this.context = context;
    }

   public String getToken() {
       preferences = PreferenceManager.getDefaultSharedPreferences(context);
       return preferences.getString(Constants.TOKEN , "");
   }

   public void setToken(String token) {
       preferences = PreferenceManager.getDefaultSharedPreferences(context);
       preferences.edit().putString(Constants.TOKEN , token).apply();
   }

}
