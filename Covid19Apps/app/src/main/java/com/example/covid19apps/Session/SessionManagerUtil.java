package com.example.covid19apps.Session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.covid19apps.Login.LoginActivity;

import java.util.Calendar;
import java.util.Date;

public class SessionManagerUtil {
    public static final String SESSION_PREFERENCE = "com.example.covid19apps.Session.SessionManagerUtil.SESSION_PREFERENCE";
    public static final String SESSION_TOKEN = "com.example.covid19apps.Session.SessionManagerUtil.SESSION_TOKEN";
    public static final String SESSION_EXPIRY_TIME = "com.example.covid19apps.Session.SessionManagerUtil.SESSION_EXPIRY_TIME";
    public static final String SESSION_USERNAME = "com.example.covid19apps.Session.SessionManagerUtil.SESSION_USERNAME";
    public static final String SESSION_EMAIL = "com.example.covid19apps.Session.SessionManagerUtil.SESSION_EMAIL";

    private static SessionManagerUtil INSTANCE;
    public static SessionManagerUtil getInstance(){
        if (INSTANCE == null){
            INSTANCE = new SessionManagerUtil();
        }
        return INSTANCE;
    }

    public void startUserSession(Context context, int expiredIn){
        Calendar calendar = Calendar.getInstance();
        Date userLoggedTime = calendar.getTime();
        calendar.setTime(userLoggedTime);
        calendar.add(Calendar.DATE, expiredIn);
        Date expiryTime = calendar.getTime();
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SESSION_EXPIRY_TIME, expiryTime.getTime());
        editor.apply();
    }

    public boolean isSessionActive(Context context, Date currentTime){
        Date sessionExpiresAt = new Date(getExpiryDateFromPreference(context));
        return !currentTime.after(sessionExpiresAt);
    }

    private long getExpiryDateFromPreference(Context context){
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getLong(SESSION_EXPIRY_TIME, 0);
    }

    public void storeUserToken(Context context, String token){
        SharedPreferences.Editor editor =
                context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putString(SESSION_TOKEN, token);
        editor.apply();
    }

    public String getUserToken(Context context){
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getString(SESSION_TOKEN, "");
    }

    public String getSessionUsername(Context context){
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getString(SESSION_USERNAME, "Missing");
    }

    public String getSessionEmail(Context context){
        return context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE)
                .getString(SESSION_EMAIL, "Missing");
    }

    public void endUserSession(Context context){
        clearStoredData(context);
    }

    private void clearStoredData(Context context){
        SharedPreferences.Editor editor =
                context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    public void setUsername(Context context, String username) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SESSION_PREFERENCE,Context.MODE_PRIVATE).edit();
        editor.putString(SESSION_USERNAME,username);
        editor.apply();
    }

    public void setEmail(Context context, String email){
        SharedPreferences.Editor editor = context.getSharedPreferences(SESSION_PREFERENCE,Context.MODE_PRIVATE).edit();
        editor.putString(SESSION_EMAIL,email);
        editor.apply();
    }
}
