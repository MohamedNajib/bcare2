package com.emedia.bcare.util;

import android.util.Patterns;

public class UserInputValidation {

    public static boolean isValidMail(String emailInput) {
        if (emailInput.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return false;
        }
        return true;
    }

    public static boolean isValidPhone(String phoneInput) {
        //if (phoneInput.isEmpty() || !Patterns.PHONE.matcher(phoneInput).matches()){
        //    return false;
        //}

        System.out.println("ret validation : " + phoneInput);
        if (phoneInput.isEmpty() || phoneInput.length() != 11){
            return false;
        }

        return true;
    }


    public static boolean isValidName (String name){
        if (name.isEmpty() || name.length() < 3 || name.length() > 50){
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String passwordInput) {
        if (passwordInput.isEmpty() || passwordInput.length() < 8){
            return false;
        }
        return true;
    }

    public static boolean isValidRePassword (String rePassword, String password){
        if (rePassword.isEmpty() || !rePassword.contentEquals(password)){
            return false;
        }
        return true;
    }

}
