package com.emedia.bcare.interfaces.nework;

import com.emedia.bcare.data.model.api_model.login.LogInMain;
import com.emedia.bcare.data.model.api_model.register.Registeration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ILogin {
    @FormUrlEncoded
    @POST("login")
    Call<Registeration> doLogginByMobile(@Field("mobile") String _mobile,
                                     @Field("password") String _password);

    @FormUrlEncoded
    @POST("login")
    Call<Registeration> doLogginByEmail(@Field("email") String _email,
                                        @Field("password") String _password);
}
