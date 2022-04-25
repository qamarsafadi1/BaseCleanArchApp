package com.example.data.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


open class Common {

    companion object {
        fun getFormattedDialogTime(date: Date): String {
            val h_mm_a = SimpleDateFormat("hh:mm", Locale.ENGLISH)
            return h_mm_a.format(date).toString()
        }

        fun getFormatted24to12WithoutS(date: String): String {
            val h_mm_a = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val hh_mm = SimpleDateFormat("hh:mm", Locale.ENGLISH)
            try {
                return hh_mm.format(h_mm_a.parse(date) ?: Date())
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
                return ""
            }

        }

        fun getFormatted24NoStoAmPm(date: String): String {
            val h_mm_a = SimpleDateFormat("HH:mm", Locale.ENGLISH)
            val hh_mm = SimpleDateFormat("a", Locale.ENGLISH)
            try {
                return hh_mm.format(h_mm_a.parse(date) ?: Date())
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
                return ""
            }

        }

        fun haveNetworkConnection(context: Context): Boolean {
            val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            return true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            return true
                        }
                    }
                }
            } else {
                try {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        return true
                    }
                } catch (e: Exception) {
                }
            }
            return false
        }

        fun setDecimal(count: Int): String {
            val df = DecimalFormat("##", DecimalFormatSymbols.getInstance(Locale.US))
            return df.format(count)
        }

        fun setPrice(price: Float): String {
            val df = DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US))
            df.roundingMode = RoundingMode.CEILING
            return df.format(price)
        }

        fun getDeviceName(): String {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                model
            } else {
                "$manufacturer $model"
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun getTodayDate(): String {
            val h_mm_a = SimpleDateFormat("EEEE,dd/MM/yyyy")
            return h_mm_a.format(Date().time)
        }

        @SuppressLint("SimpleDateFormat")
        fun getTomorrowDate(): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val tomorrow = calendar.time
            val h_mm_a = SimpleDateFormat("dd/MM/yyyy")
            return h_mm_a.format(tomorrow)

        }

        @SuppressLint("SimpleDateFormat")
        fun formatDate(createdAt: String): String {
            val h_mm_ss = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val ddd = SimpleDateFormat("dd/MM/yyyy")
            try {
                val d1 = h_mm_ss.parse(createdAt)
                d1?.let {
                    return ddd.format(d1)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                return ""
            }
            return ""
        }

        fun getDayOfWeek(): Int {
            return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        }
    }


}