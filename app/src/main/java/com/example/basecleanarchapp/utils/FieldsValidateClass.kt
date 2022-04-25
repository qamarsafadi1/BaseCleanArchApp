package com.example.basecleanarchapp.utils
import android.content.Context
import android.widget.EditText
import com.example.basecleanarchapp.R
import com.example.basecleanarchapp.utils.FieldsValidateConstraintsClass.isEmailValidPattern
import com.example.basecleanarchapp.utils.FieldsValidateConstraintsClass.isPasswordValidLength
import com.example.basecleanarchapp.utils.FieldsValidateConstraintsClass.isPasswordsMatches
import com.example.basecleanarchapp.utils.FieldsValidateConstraintsClass.isPhoneNumberOnly
import com.example.basecleanarchapp.utils.FieldsValidateConstraintsClass.isPhoneNumberValidLength


fun EditText.validateEmail(mContext: Context): String {
        var message = ""
        if (this.text.toString() == "") {
            message =
                mContext.getString(R.string.valid_required)
        } else if (!isEmailValidPattern(this.text.toString())) {
            message = mContext.getString(R.string.warning_email_pattern)
        }
        return message
    }

    fun EditText.validateRequired(mContext:Context): String {
        var message = ""
        if (this.text.toString() == "") {
            message = mContext.getString(R.string.valid_required)
        }
        return message
    }

    fun EditText.validateUserName(mContext:Context): String {
        var message = ""
        if (this.text.toString() == "") {
            message = mContext.getString(R.string.valid_required)
        }
        return message
    }

    fun EditText.validatePassword(mContext:Context): String {
        var message = ""
        if (this.text.toString() == "") {
            message = mContext.getString(R.string.valid_required)
        } else if (!isPasswordValidLength(this.text.toString())) {
            message = mContext.getString(R.string.warning_password_length)
        }
        return message
    }

    fun EditText.validRePassword(mContext:Context,password1: String?, password2: String): String {
        var message = ""
        if (password2 == "") {
            message = mContext.getString(R.string.valid_required)
        } else if (!isPasswordsMatches(password1!!, password2)) {
            message = mContext.getString(R.string.warning_password_not_match)
        }
        return message
    }

    fun EditText.validatePhone(mContext:Context): String {
        var message = ""
        if (this.text.toString() == "") {
            message = mContext.getString(R.string.valid_required)
        } else if (!isPhoneNumberOnly(this.text.toString())) {
            message = mContext.getString(R.string.warning_phone_pattern)
        } else if (!isPhoneNumberValidLength(this.text.toString())) {
            message = mContext.getString(R.string.warning_phone_length_pattern)
        }
        return message
    }

    fun String?.validatePhone(mContext:Context): String {
        var message = ""
        if (this == "" || this == null) {
            message = mContext.getString(R.string.valid_required)
        } else if (!isPhoneNumberOnly(this)) {
            message = mContext.getString(R.string.warning_phone_pattern)
        } else if (!isPhoneNumberValidLength(this)) {
            message = mContext.getString(R.string.warning_phone_length_pattern)
        }
        return message
    }

