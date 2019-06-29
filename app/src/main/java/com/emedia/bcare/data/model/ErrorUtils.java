package com.emedia.bcare.data.model;

import com.emedia.bcare.data.model.api_model.login.LoginErrorMain2;
import com.emedia.bcare.network.RequestSingletone;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static LoginErrorMain2 parseError(Response<?> response) {
        Converter<ResponseBody, LoginErrorMain2> converter =
                RequestSingletone.getInstance().getClient()
                        .responseBodyConverter(LoginErrorMain2.class, new Annotation[0]);

        LoginErrorMain2 error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new LoginErrorMain2();
        }

        return error;
    }

   //public static ErrorSignUpMain parseErrorSignUp(Response<?> response) {
   //    Converter<ResponseBody, ErrorSignUpMain> converter =
   //            RequestSingltone.getInstance().getClient()
   //                    .responseBodyConverter(ErrorSignUpMain.class, new Annotation[0]);

   //    ErrorSignUpMain error;

   //    try {
   //        error = converter.convert(response.errorBody());
   //    } catch (IOException e) {
   //        return new ErrorSignUpMain();
   //    }

   //    return error;
   //}

}
