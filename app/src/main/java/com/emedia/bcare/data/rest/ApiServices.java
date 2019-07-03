package com.emedia.bcare.data.rest;

import com.emedia.bcare.data.model.api_model.add_specialist_rate.AddSpecialistRate;
import com.emedia.bcare.data.model.api_model.booking.Booking;
import com.emedia.bcare.data.model.api_model.checkcode.CheckCode;
import com.emedia.bcare.data.model.api_model.cities.Cities;
import com.emedia.bcare.data.model.api_model.countries.RegisterCountries;
import com.emedia.bcare.data.model.api_model.favorite.Favorite;
import com.emedia.bcare.data.model.api_model.forgotPass_sendmail.ForgotPassSendMail;
import com.emedia.bcare.data.model.api_model.home.Home;
import com.emedia.bcare.data.model.api_model.my_event.MyEvent;
import com.emedia.bcare.data.model.api_model.register.Registeration;

import com.emedia.bcare.data.model.api_model.salons.Salons;
import com.emedia.bcare.data.model.api_model.service.Service;
import com.emedia.bcare.data.model.api_model.specialist_info.SpecialistInfo;
import com.emedia.bcare.data.model.api_model.specialists.Specialists;
import com.emedia.bcare.data.model.api_model.user_details.UserDetails;
import com.emedia.bcare.data.model.change_password.ChangePassword;
import com.emedia.bcare.data.model.checkCopoun.CheckCopoun;
import com.emedia.bcare.data.model.checkCopoun.WithoutCopoun;
import com.emedia.bcare.data.model.login_email.LoginEmail;
import com.emedia.bcare.data.model.logoutUser.LogoutUser;
import com.emedia.bcare.data.model.register.Register;
import com.emedia.bcare.data.model.salon_reserve.SalonReserve;
import com.emedia.bcare.data.model.salon_services.SalonServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("getCountriesRegister")
    Call<RegisterCountries> getCountriesList(@Query("lang") String lang);

    @GET("getCitiesRegister")
    Call<Cities> getCitesList(@Query("lang") String lang,
                              @Query("country_id") int country_id);

    @POST("login")
    @FormUrlEncoded
    Call<LoginEmail> doLoginByEmail(@Field("email") String _email,
                                    @Field("password") String _password);

    @POST("register")
    @FormUrlEncoded
    Call<Register> userRegister(@Field("email") String email,
                                @Field("password") String password,
                                @Field("name") String name,
                                @Field("address") String address,
                                @Field("age") String age,
                                @Field("city_id") int city_id,
                                @Field("country_id") int country_id,
                                @Field("code") String code,
                                @Field("username") String username);

    @POST("register")
    @FormUrlEncoded
    Call<Register> userRegisterPhone(@Field("mobile") String email,
                                     @Field("password") String password,
                                     @Field("name") String name,
                                     @Field("address") String address,
                                     @Field("age") String age,
                                     @Field("city_id") int city_id,
                                     @Field("country_id") int country_id,
                                     @Field("code") String code,
                                     @Field("username") String username);

    @POST("getSalons")
    @FormUrlEncoded
    Call<Salons> getSalons(@Field("token") String token,
                           @Field("lang") String lang,
                           @Field("category_id") int category_id,
                           @Field("user_latitude") String user_latitude,
                           @Field("user_longitude") String user_longitude,
                           @Field("country_id") int country_id,
                           @Field("orderBy") String orderBy,
                           @Field("salontype_id") int salontype_id);

    @POST("getServices")
    @FormUrlEncoded
    Call<Service> getServices(@Field("token") String token,
                              @Field("lang") String lang,
                              @Field("salontype_id") int salontype_id);

    @POST("addFavorite")
    @FormUrlEncoded
    Call<Favorite> addFavorite(@Field("token") String token,
                               @Field("salon_id") int salon_id,
                               @Field("toggle") int toggle);

    @POST("getSpecialists")
    @FormUrlEncoded
    Call<Specialists> getSpecialists(@Field("token") String token,
                                     @Field("lang") String lang,
                                     @Field("country_id") int country_id,
                                     @Field("salontype_id") int salontype_id,
                                     @Field("specialistgroup_id") int specialistgroup_id,
                                     @Field("orderBy") String orderBy);

    @POST("getSalonServices")
    @FormUrlEncoded
    Call<SalonServices> getSalonServices(@Field("token") String token,
                                         @Field("lang") String lang,
                                         @Field("salon_id") int country_id);

    @POST("checkCopoun")
    @FormUrlEncoded
    Call<CheckCopoun> checkCopoun(@Field("token") String token,
                                  @Field("lang") String lang,
                                  @Field("copoun_code") String copoun_code,
                                  @Field("salon_id") int salon_id);

    @POST("checkCopoun")
    @FormUrlEncoded
    Call<WithoutCopoun> withoutCopoun(@Field("token") String token,
                                      @Field("lang") String lang,
                                      @Field("copoun_code") String copoun_code,
                                      @Field("salon_id") int salon_id);

    @POST("reserve")
    @FormUrlEncoded
    Call<SalonReserve> homeReserve(@Field("token") String token,
                                   @Field("lang") String lang,
                                   @Field("salon_id") int salon_id,
                                   @Field("total_price") float total_price,
                                   //@Field("discount_percentage") int discount_percentage,
                                   //@Field("price_after_discount") float price_after_discount,
                                   @Field("reservation_time") String reservation_time,
                                   @Field("reservation_date") String reservation_date,
                                   @Field("client_name") String client_name,
                                   @Field("client_mobile") String client_mobile,
                                   @Field("place") String place,
                                   @Field("specialist_id") int specialist_id,
                                   @Field("services_id") String services_id0,
                                   @Field("home_latitude") String home_latitude,
                                   @Field("home_longitude") String home_longitude,
                                   @Field("building_number") String building_number,
                                   @Field("flat_number") String flat_number,
                                   @Field("landmark") String landmark);

    @POST("reserve")
    @FormUrlEncoded
    Call<SalonReserve> salonReserve(@Field("token") String token,
                                    @Field("lang") String lang,
                                    @Field("salon_id") int salon_id,
                                    @Field("total_price") float total_price,
                                    @Field("discount_percentage") int discount_percentage,
                                    @Field("price_after_discount") float price_after_discount,
                                    @Field("reservation_date") String reservation_date,
                                    @Field("client_name") String client_name,
                                    @Field("client_mobile") String client_mobile,
                                    @Field("place") String place,
                                    @Field("services_id") List<String> services_id0,
                                    @Field("home_latitude") String home_latitude,
                                    @Field("home_longitude") String home_longitude,
                                    @Field("building_number") String building_number,
                                    @Field("flat_number") String flat_number,
                                    @Field("landmark") String landmark,
                                    @Field("specialist_id") int specialist_id);

    @POST("reserve")
    @FormUrlEncoded
    Call<SalonReserve> salonReserve(@Field("token") String token,
                                    @Field("lang") String lang,
                                    @Field("salon_id") int salon_id,
                                    @Field("total_price") float total_price,
                                    @Field("reservation_time") String reservation_time,
                                    @Field("reservation_date") String reservation_date,
                                    @Field("client_name") String client_name,
                                    @Field("client_mobile") String client_mobile,
                                    @Field("place") String place,
                                    @Field("services_id") String services_id0,
                                    @Field("specialist_id") int specialist_id);

    @POST("salonTimeSpecialist")
    @FormUrlEncoded
    Call<Booking> salonTimeSpecialist(@Field("token") String token,
                                      @Field("lang") String lang,
                                      @Field("salon_id") int salon_id);

    @POST("myAppointment")
    @FormUrlEncoded
    Call<MyEvent> getMyEvents(@Field("token") String token,
                              @Field("lang") String lang);

    @POST("getSpecialistInfo")
    @FormUrlEncoded
    Call<SpecialistInfo> getSpecialistInfo(@Field("token") String token,
                                           @Field("specialist_id") int specialist_id,
                                           @Field("lang") String lang);

    @POST("addSpecialistRate")
    @FormUrlEncoded
    Call<AddSpecialistRate> addSpecialistRate(@Field("token") String token,
                                              @Field("rate") int rate,
                                              @Field("description") String description,
                                              @Field("specialist_id") int specialist_id);

    @POST("sendmail")
    @FormUrlEncoded
    Call<ForgotPassSendMail> SendMail(@Field("email") String email);

    @POST("checkcode")
    @FormUrlEncoded
    Call<CheckCode> checkCode(@Field("email") String email,
                              @Field("code") String code);

    @GET("home")
    Call<Home> getHomeData(@Query("token") String token,
                           @Query("lang") String lang,
                           @Query("salontype_id") int salontype_id);

    @POST("getUserDetails")
    @FormUrlEncoded
    Call<UserDetails> getUserDetails(@Field("token") String token,
                                     @Field("lang") String lang);

    @POST("changepassword")
    @FormUrlEncoded
    Call<ChangePassword> changePassword(@Field("email") String email,
                                        @Field("newpassword") String newpassword);

    @GET("logout")
    Call<LogoutUser> logoutUser(@Query("token") String token,
                                @Query("user_id") int user_id);


}
