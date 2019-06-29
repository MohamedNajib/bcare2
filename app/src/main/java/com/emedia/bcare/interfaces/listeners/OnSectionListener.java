package com.emedia.bcare.interfaces.listeners;

import com.emedia.bcare.data.model.api_model.login.LogInMain;
import com.emedia.bcare.data.model.api_model.salons.SalonData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OnSectionListener {
   public void onSectionClick(List<String> mServicesIdList);
}
