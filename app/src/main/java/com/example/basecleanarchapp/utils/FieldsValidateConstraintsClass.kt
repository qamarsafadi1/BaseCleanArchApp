package com.example.basecleanarchapp.utils

import java.util.regex.Pattern

/**
 * Created by Tasneem Fathi on 1/1/2018.
 */
object FieldsValidateConstraintsClass {
    @JvmStatic
    fun isEmailValidPattern(email: String?): Boolean {
        val ePattern =
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = Pattern.compile(ePattern)
        val m = p.matcher(email)
        return m.matches()
    }

    @JvmStatic
    fun isPhoneNumberValidLength(password: String): Boolean {
        return password.length == 9
    }

    @JvmStatic
    fun isPasswordValidLength(password: String): Boolean {
        return password.length >= 6
    }

    @JvmStatic
    fun isPasswordsMatches(password1: String, password2: String): Boolean {
        return password1 == password2
    }

    @JvmStatic
    fun isPhoneNumberOnly(value: String): Boolean {
        return value.matches("[0-9]+".toRegex())
    }

    /* public static boolean isUsernameArabicEnglishDigitOnly(String password) {
        if (password.matches("^[a-zA-Zء-ي]+$"))
            return true;
        else
            return false;
    }*/
    /*  public static boolean isEmailValidType(String email) {
        String[] valid = {"gmail.com", "windowslive.com", "outlook.com", "outlook.sa", "hotmail.com", "yahoo.com"};
        String check = "";
        if (email.split("@").length > 1) {
            check = email.split("@")[1];
            // Log.d("check",check+"");
            for (int i = 0; i < valid.length; i++) {
                if (valid[i].equals(check))
                    return true;
            }
        }
        return false;
    }*/
    /*  public static boolean isCodeValidLength(String code) {
        if (code.length() == 6) {
            return true;
        } else {
            return false;
        }
    }*/
    /* public static boolean isUserNameValidLength(String txt) {
        if (txt.length() < 3) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPasswordWeak(String password) {
        if (!isPasswordSerialized(password) && isPasswordValidType(password)) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean isPasswordSerialized(String password) {

        char[] arr = password.toCharArray();
        char previous = '\u0000';
        int serialCounter = 0;

        for (char current : arr) {
            if (current > previous && current - previous == 1) {
                serialCounter++;
                if (serialCounter == 2) return true;
            } else {
                serialCounter = 0;
            }

            previous = current;
        }
        return false;
    }

    public static boolean isPasswordValidType(String password) {
        if (isPasswordDigitOnly(password) || isPasswordNumberOnly(password))
            return false;
        else
            return true;

    }

    public static boolean isPasswordDigitOnly(String password) {
        if (password.matches("[a-zA-Z]+"))
            return true;
        else
            return false;
    }

    public static boolean isDigitOrNumberOnly(String text) {
        if (text.matches("^[a-zA-Z0-9]*$"))
            return true;
        else
            return false;
    }

    public static boolean isPasswordNumberOnly(String password) {
        if (password.matches("[0-9]+"))
            return true;
        else
            return false;

    }
*/
    /* public static boolean isPasswordSpecialChar(String s) {
        if (s.matches("(.*[@#$%^&+=].*)"))
            return true;
        else return false;

    }

    public static boolean isPasswordCapitalChar(String s) {
        if (s.matches(".*[A-Z].*"))
            return true;
        else return false;
    }

    public static boolean isUserNameAlphabet(String str) {
        String expression = "^[a-zA-Z\\s]+";
        return str.matches(expression);
    }

    / *  \d{10} matches 1234567890
        (?:\d{3}-){2}\d{4} matches 123-456-7890
        \(\d{3}\)\d{3}-?\d{4} matches (123)456-7890 or (123)4567890
     */
    /* public static boolean isPhoneNumberValid(String str) {


        return Patterns.PHONE.matcher(str).matches();
    }

    public static boolean isURLValid(String str) {
        String expression = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        return str.matches(expression);
    }

    public static boolean firstLetterIsDigit(String name) {

        return isPasswordDigitOnly(name.charAt(0) + "");
    }

    public static boolean isBirthDayValid(String birthday) {
        return true;
    }*/
}