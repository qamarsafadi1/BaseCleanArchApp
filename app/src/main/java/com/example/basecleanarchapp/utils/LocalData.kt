package com.example.basecleanarchapp.utils

import com.orhanobut.hawk.Hawk
open class LocalData {

    companion object {

        private const val NOTIFICATION_TOKEN = "notification_token"

        var isFirstTime: Boolean = Hawk.get("isFirst", true)
            set(value) {
                field = value
                Hawk.put("isFirst", value)
            }
        var darkMode: Boolean? = Hawk.get("darkMode")
            set(value) {
                field = value
                Hawk.put("darkMode", value)
            }
        var isPackage: Boolean? = Hawk.get("isPackage")
            set(value) {
                field = value
                Hawk.put("isPackage", value)
            }
        var isTech: Boolean? = Hawk.get("isTech")
            set(value) {
                field = value
                Hawk.put("isTech", value)
            }
        var count: Int? = Hawk.get("count")
            set(value) {
                field = value ?: 0
                Hawk.put("count", value)
            }

        var appLocale: String = Hawk.get("app_locale", "ar")
            set(value) {
                field = value
                Hawk.put("app_locale", value)
            }

        var fcmToken: String = Hawk.get(NOTIFICATION_TOKEN, "")
            set(value) {
                field = value
                Hawk.put(NOTIFICATION_TOKEN, value)
            }

        var accessToken: String? = Hawk.get("accessToken", "")
            set(value) {
                field = value
                Hawk.put("accessToken", value)
            }
    }

}