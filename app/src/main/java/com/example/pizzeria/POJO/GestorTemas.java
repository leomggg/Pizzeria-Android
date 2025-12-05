package com.example.pizzeria.POJO;

import android.content.Context;
import android.content.SharedPreferences;

public class GestorTemas {

    private static final String PREFS_NAME = "AppPrefs";
    private static final String KEY_THEME = "AppTheme";

    public static void saveThemeMode(Context context, int mode) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_THEME, mode).apply();
    }

    public static int getThemeMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(KEY_THEME, 0);
    }
}
