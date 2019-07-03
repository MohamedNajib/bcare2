package com.emedia.bcare.cash;

import android.content.Intent;
import android.content.SharedPreferences;

import com.emedia.bcare.Config.BCareApp;
import com.emedia.bcare.data.model.login_email.UserData;
import com.emedia.bcare.data.model.register.UsersSocail;

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
    private static final String USERNAME = "username";


    private static final String U_TOKEN = "U_TOKEN";
    private static final String U_NAME = "U_NAME";
    private static final String U_USERNAME = "U_USERNAME";
    private static final String U_EMAILE = "U_EMAILE";
    private static final String U_AGE = "U_AGE";
    private static final String U_ADDRESS = "U_ADDRESS";
    private static final String U_CITY_ID = "U_CITY_ID";
    private static final String U_COUNTRY_ID = "U_COUNTRY_ID";

    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private static final String LOGINE_TOKEN = "LE_TOKEN";
    private static final String LOGINE_NAME= "LE_NAME";
    private static final String LOGINE_USERNAME = "LE_USERNAME";
    private static final String LOGINE_EMAIL = "LE_EMAIL";
    private static final String LOGINE_AGE = "LE_AGE";
    private static final String LOGINE_ADDRESS = "LE_ADDRESS";
    private static final String LOGINE_CITYID = "LE_CITYID";
    private static final String LOGINE_COUNTRYID = "LE_TOKEN";

private static final String PLACE = "place";

    private SharedPreferences sharedPreferences;

    private SharedUser() {
        sharedPreferences = BCareApp.getInstance().getContext().getSharedPreferences(USER, MODE_PRIVATE);
    }

    public static SharedUser getSharedUser() {
        return new SharedUser();
    }

    public String getPlaceOfService() {
        return sharedPreferences.getString(PLACE, "");
    }

    public boolean setPlaceOfService(String place) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLACE, place);
        return editor.commit();
    }



    public void saveClientLoginData(UserData loginData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGINE_NAME, loginData.getName());
        editor.putString(LOGINE_USERNAME, loginData.getUsername());
        editor.putString(LOGINE_EMAIL, loginData.getEmail());
        editor.putString(LOGINE_AGE, loginData.getAge());
        editor.putString(LOGINE_ADDRESS, loginData.getAddress());
        editor.putString(LOGINE_CITYID, loginData.getCityId());
        editor.putString(LOGINE_COUNTRYID, loginData.getCountryId());
        editor.apply();
    }

    public UserData getClientLoginData(){
        return new UserData(
                sharedPreferences.getString(LOGINE_NAME, null),
                sharedPreferences.getString(LOGINE_USERNAME, null),
                sharedPreferences.getString(LOGINE_EMAIL, null),
                sharedPreferences.getString(LOGINE_AGE, null),
                sharedPreferences.getString(LOGINE_ADDRESS, null),
                sharedPreferences.getString(LOGINE_CITYID, null),
                sharedPreferences.getString(LOGINE_COUNTRYID, null)
        );
    }

    public boolean setUserId (String id){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_USER_ID, id);
        return editor.commit();
    }

    public String getUserId () {
        return sharedPreferences.getString(LOGIN_USER_ID, null);
    }

    public boolean setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        return editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN, null);
    }

//    public void setUserToken(String token){
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(LOGIN_USER_ID, usersSocail.getUserId());
//        editor.putString(LOGINE_TOKEN, usersSocail.getAccessToken());
//    }
//    public UsersSocail gutToken(){
//        return new UsersSocail(
//                sharedPreferences.getString(LOGIN_USER_ID, null),
//                sharedPreferences.getString(LOGINE_TOKEN, null)
//        );
//    }

    public void saveClientRegisterData(com.emedia.bcare.data.model.register.UserData userData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(U_NAME, userData.getName());
        editor.putString(U_USERNAME, userData.getUsername());
        editor.putString(U_EMAILE, userData.getEmail());
        editor.putString(U_AGE, userData.getAge());
        editor.putString(U_ADDRESS, userData.getAddress());
        editor.putString(U_CITY_ID, userData.getCityId());
        editor.putString(U_COUNTRY_ID, userData.getCityId());
        editor.apply();
    }

    public com.emedia.bcare.data.model.register.UserData getClientRegisterData(){
        return new com.emedia.bcare.data.model.register.UserData(
                sharedPreferences.getString(U_NAME, null),
                sharedPreferences.getString(U_USERNAME, null),
                sharedPreferences.getString(U_EMAILE, null),
                sharedPreferences.getString(U_AGE, null),
                sharedPreferences.getString(U_ADDRESS, null),
                sharedPreferences.getString(U_CITY_ID, null),
                sharedPreferences.getString(U_COUNTRY_ID, null)
        );
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

    public String getId() {
        return sharedPreferences.getString(ID, "");
    }




    public boolean setEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        return editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, "");
    }


    public boolean setUserName(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        return editor.commit();
    }

    public String getUsername() {
        return sharedPreferences.getString(USERNAME, "");
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
