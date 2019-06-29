package com.emedia.bcare.interfaces.nework;

import com.emedia.bcare.data.model.api_model.register.Registeration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRegister {
    @FormUrlEncoded
    @POST("register")
    Call<Registeration> doSignUp(@Field("email") String _email,
                                 @Field("name") String _password,
                                 @Field("address") String _address,
                                 @Field("age") String _age,
                                 @Field("city_id") String _city_id,
                                 @Field("country_id") String _country_id,
                                 @Field("mobile") String _mobile,
                                 @Field("code") String _code);
}
