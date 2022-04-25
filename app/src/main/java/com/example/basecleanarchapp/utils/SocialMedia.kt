package com.example.basecleanarchapp.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.basecleanarchapp.R


fun Context.whatsappContact(phone: String) {
    val url = "https://api.whatsapp.com/send?phone=${phone}"
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    this.startActivity(i)
}

fun Context.snapchatContact(account: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/$account"))
        intent.setPackage("com.snapchat.android")
        this.startActivity(intent)
    } catch (e: Exception) {
        this.applicationContext.showError(getString(R.string.please_download_app))
//        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(account)))
    }
}

fun Context.instaContact(account: String) {
    val uri = Uri.parse("http://instagram.com/_u/$account")
    val i = Intent(Intent.ACTION_VIEW, uri)
    i.setPackage("com.instagram.android")
    try {
        this.startActivity(i)
    } catch (e: ActivityNotFoundException) {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                uri
            )
        )
    }
}

fun Context.phoneContact(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
    this.startActivity(intent)
}

fun Activity.sendEmail(email: String?) {
    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse("mailto:$email")
    this.startActivity(
        Intent.createChooser(
            emailIntent,
            "" // context.getString(R.string.send_email)
        )
    )
}

fun Context.sendEmail(email: String?) {
    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse("mailto:$email")
    this.startActivity(
        Intent.createChooser(
            emailIntent,
            "" // context.getString(R.string.send_email)
        )
    )
}

fun Context.twitterContact(link: String) {
    var intent: Intent? = null
    try {
        // get the Twitter app if possible
        this.packageManager.getPackageInfo("com.twitter.android", 0)
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    } catch (e: java.lang.Exception) {
        // no Twitter app, revert to browser
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    }
    this.startActivity(intent)
}

fun Context.facebookContact(link: String) {
    var intent: Intent? = null

    try {
        this.packageManager.getPackageInfo("com.facebook.katana", 0)
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + link));
    } catch (e: Exception) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + link));
    }
    this.startActivity(intent)
}

fun Context.googlePlayContact(link: String) {
    val appPackageName: String = this.packageName // package name of the app

    try {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            )
        )
    } catch (anfe: ActivityNotFoundException) {
        this.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            )
        )
    }
}