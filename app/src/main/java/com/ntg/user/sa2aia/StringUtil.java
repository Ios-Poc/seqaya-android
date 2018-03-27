package com.ntg.user.sa2aia;

import java.util.regex.Pattern;

/**
 * Created by ilias on 25/02/2018.
 */

public class StringUtil {

    public static boolean isValidEmailAddress(String s) {
        String regexp = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(s).matches();
    }

    public static boolean notNullOrEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
