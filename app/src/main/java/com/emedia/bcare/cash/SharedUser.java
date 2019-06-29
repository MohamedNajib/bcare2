package com.emedia.bcare.cash;

import android.content.SharedPreferences;

import com.emedia.bcare.Config.BCareApp;

import static android.content.Context.MODE_PRIVATE;
public class SharedUser {
    private static final String USER = "user";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String TOKEN = "token";
    private static final String AVATAR = "avatar";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String CITYID = "cityId";
    private static final String COUNTRYID = "countryId";
    private static final String PHOTO = "photo";

    private SharedPreferences sharedPreferences;

    private SharedUser() {
        sharedPreferences = BCareApp.getInstance().getContext().getSharedPreferences(USER, MODE_PRIVATE);
    }

    public static SharedUser getSharedUser() {
        return new SharedUser();
    }


    public String getPhone() {
        return sharedPreferences.getString(PHONE, "");
    }

    public boolean setPhone(String phone) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE, phone);
        return editor.commit();
    }

    public String getCityid() {
        return sharedPreferences.getString(CITYID, "");
    }

    public boolean setCityid(String cityId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CITYID, cityId);
        return editor.commit();
    }

    public String getCountryid() {
        return sharedPreferences.getString(CITYID, "");
    }

    public boolean setCountryid(String countryId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COUNTRYID, countryId);
        return editor.commit();
    }

    public boolean setId(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID, id);
        return editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public String getId() {
        return sharedPreferences.getString(ID, "");
    }

    public boolean setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        return editor.commit();
    }


    public boolean setEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        return editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, "");
    }


    public boolean setPhoto(String photo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHOTO, photo);
        return editor.commit();
    }

    public String getPhoto() {
        return sharedPreferences.getString(PHOTO, "");
    }

    public boolean setName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, name);
        return editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(NAME, "");
    }



    public boolean setAddress(String address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ADDRESS, address);
        return editor.commit();
    }

    public String getAddress() {
        return sharedPreferences.getString(ADDRESS, "");
    }

    public String getUserType() {
        return sharedPreferences.getString(TYPE, "user");
    }

    public boolean isUser() {
        return getUserType().equals("user");
    }

    public boolean clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }
}
