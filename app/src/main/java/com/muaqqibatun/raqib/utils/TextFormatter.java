package com.muaqqibatun.raqib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by abalhassans on 7/23/2015.
 */
public class TextFormatter {

    private Typeface typeface;
    private int headerSize;
    private int subHeaderSize;
    private int textSize;
    private Context context;
    private String DefaultFont = "font/Simplified Arabic Bold.ttf";

    public static TextFormatter formatter;

    private TextFormatter() {

    }

    public static TextFormatter getFormatter(Context context) {


        if (formatter == null) {

            formatter = new TextFormatter();
            formatter.context = context;


            SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            myPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    formatter.reConfigure();
                }
            });

            formatter.reConfigure();

        }

        return formatter;
    }

    public int getHeaderFontSize() {
        return headerSize;
    }

    public int getSubHeaderSize() {
        return subHeaderSize;
    }

    public int getTextSize() {
        return textSize;
    }

    public Typeface getTypeface() {
        return typeface;
    }


    private void reConfigure() {

        String fontface = null;

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        formatter.headerSize = myPreferences.getInt("headerSize_picker", 26);
        formatter.subHeaderSize = myPreferences.getInt("subHeaderSize_picker", 22);
        formatter.textSize = myPreferences.getInt("textSize_picker", 18);
        fontface = myPreferences.getString("fontFace", formatter.DefaultFont);

        try {
            formatter.typeface = Typeface.createFromAsset(context.getAssets(), fontface);
        } catch (Exception e) {
            Toast.makeText(context, "Font family " + fontface + " could not be applied", Toast.LENGTH_SHORT).show();
            formatter.typeface = null;
        }

        if (fontface == null) {
            formatter.typeface = Typeface.createFromAsset(context.getAssets(), fontface);
        }

    }

}
