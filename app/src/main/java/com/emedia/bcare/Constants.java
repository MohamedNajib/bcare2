package com.emedia.bcare;

public abstract class Constants {

    /**
     * Shared Preferences Keys
     */
    public static class SharedKeys {
        public static final String SHARED_PREF_NAME = "My_Shared";

        /* API Token Key */
        public static final String USER_API_TOKEN = "user_token";

        /* Login Client ServiceData Key */
        public static final String USER_NAME = "u_name";
        public static final String USER_PASWORD = "u_password";
        public static final String USER_EMAIL = "u_email";
        public static final String USER_PHONE = "u_phone";
        public static final String USER_CITY = "u_city";
        public static final String USER_AGE = "u_age";

        public static final String POPUP_S = "STA";

    }


    /**
     * Fragments Keys
     */
    public static class FragmentsKeys {

        public static final int SPLASH_DISPLAY_LENGTH = 1000;

        public static final int MY_PERMISSIONS_REQUEST_ACCESS_GPS = 500;

        public static final int REQUEST_STATUS_OK = 200;

        public static final String TO_REG_ACTIVITY2 = "TO_REG_ACTIVITY2";


    }

}
