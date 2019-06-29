package com.emedia.bcare.Config;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedLanguage {
    private static final String LANG = "lang";

    private SharedPreferences sharedPreferences;

    private SharedLanguage() {
        sharedPreferences = BCareApp.getInstance().getContext().getSharedPreferences(LANG, MODE_PRIVATE);
    }

    public static SharedLanguage getSharedLanguage() {
        return new SharedLanguage();
    }

    public void registerOnLangSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnLangSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public String getLang() {
        return sharedPreferences.getString(LANG, "ar");
    }

    public void setLang(Activity activity, String lang) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", lang);
        boolean commit = editor.commit();
        if (commit) {
            Intent i = activity.getBaseContext().getPackageManager().getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
            activity.finish();
            //System.exit(2);
        }
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
