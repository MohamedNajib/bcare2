package com.emedia.bcare.interfaces.nework;

import com.emedia.bcare.data.model.api_model.register.Registeration;
import com.emedia.bcare.data.model.api_model.verify.VerifyMain;

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

    @FormUrlEncoded
    @POST("register")
    Call<Registeration> doSignUpByEmail(@Field("email") String email,
                                 @Field("password") String password,
                                 @Field("address") String _address,
                                 @Field("age") String _age,
                                 @Field("city_id") String _city_id,
                                 @Field("country_id") String _country_id,
                                 @Field("code") String code,
                                 @Field("username") String username);
    @FormUrlEncoded
    @POST("register")
    Call<Registeration> doSignUpByPhone(@Field("mobile") String mobile,
                                        @Field("password") String password,
                                        @Field("address") String _address,
                                        @Field("age") String _age,
                                        @Field("city_id") String _city_id,
                                        @Field("country_id") String _country_id,
                                        @Field("code") String code,
                                        @Field("username") String username);


    @FormUrlEncoded
    @POST("verifyAccount")
    Call<VerifyMain> verifyMobile(@Field("mobile") String mobile, @Field("code") String code);

    @FormUrlEncoded
    @POST("verifyAccount")
    Call<VerifyMain> verifyEmail(@Field("mobile") String email, @Field("code") String code);
}
