package com.emedia.bcare.util;

import android.util.Patterns;

public class UserInputValidation {

    public static boolean isValidMail(String emailInput) {
        if (emailInput.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return false;
        }
        return true;
    }

}
