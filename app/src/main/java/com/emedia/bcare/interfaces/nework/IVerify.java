package com.emedia.bcare.interfaces.nework;

import com.emedia.bcare.data.model.api_model.register.Registeration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IVerify {
    @FormUrlEncoded
    @POST("verifyAccount")
    Call<Registeration> verify(@Field("email") String _email,
                                 @Field("code") String _code);
}
