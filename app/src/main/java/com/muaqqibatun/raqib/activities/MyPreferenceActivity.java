package com.muaqqibatun.raqib.activities;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.muaqqibatun.raqib.R;

/**
 * Created by abalhassans on 7/19/2015.
 */
public class MyPreferenceActivity extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        //getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment());
    }

   /* @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class MyPreferenceFragment extends PreferenceFragment {




    }*/



}
