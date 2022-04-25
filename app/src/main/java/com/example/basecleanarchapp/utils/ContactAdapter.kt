package com.example.basecleanarchapp.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.basecleanarchapp.R
import com.example.basecleanarchapp.utils.*
import com.skydoves.elasticviews.ElasticImageView
import com.skydoves.elasticviews.ElasticLayout


@BindingAdapter("insta_contact")
fun instaContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.instaContact(text)
        }
    } else {
        view.setOnClickListener {
            view.context.showMessage(view.context.getString(R.string.no_account))
        }
//        view.context.showMessage(view.context.getString(R.string.no_account))
    }
}
@BindingAdapter("insta_contact")
fun instaContact(view: ElasticImageView, text: String?) {
    if (text != null) {
        view.onClick {
            view.context?.instaContact(text)
        }
    } else {
        view.onClick {
            view.context.showMessage(
                view.context.getString(
                    R
                        .string.no_account
                )
            )
        }
//        view.context.showMessage(view.context.getString(R.string.no_account))
    }
}

@BindingAdapter("whatsapp_contact")
fun whatsappContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.whatsappContact(text)
        }
    } else {
//        view.context.showMessage(view.context.getString(R.string.no_account))
        view.setOnClickListener {
            view.context.showMessage(view.context.getString(R.string.no_account))
        }
    }
}
@BindingAdapter("whatsapp_contact")
fun whatsappContact(view: ElasticImageView, text: String?) {
    if (text != null) {
        view.onClick {
            view.context?.whatsappContact(text)
        }
    } else {
//        view.context.showMessage(view.context.getString(R.string.no_account))
        view.onClick {
            view.context.showMessage(view.context.getString(R.string.no_account))
        }
    }
}


@BindingAdapter("snap_contact")
fun snapchatContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.snapchatContact(text)
        }
    } else {
        view.setOnClickListener {
            view.context.showMessage(view.context.getString(R.string.no_account))
        }
//        view.context.showMessage(view.context.getString(R.string.no_account))
    }
}
@BindingAdapter("snap_contact")
fun snapchatContact(view: ElasticImageView, text: String?) {
    if (text != null) {
        view.onClick {
            view.context?.snapchatContact(text)
        }
    } else {
        view.onClick {
            view.context.showMessage(view.context.getString(R.string.no_account))
        }
//        view.context.showMessage(view.context.getString(R.string.no_account))
    }
}

@BindingAdapter("face_contact")
fun facebookContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.facebookContact(text)
        }
    }
}

@BindingAdapter("twitter_contact")
fun twitterContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.twitterContact(text)
        }
    }
}

@BindingAdapter("google_contact")
fun googleContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.googlePlayContact(text)
        }
    }
}

@BindingAdapter("mail_contact")
fun mailContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view.context?.sendEmail(text)
        }
    }
}

@BindingAdapter("mail_contact")
fun mailContact(view: ElasticLayout, text: String?) {
    if (text != null) {
        view.onClick {
            view.context?.sendEmail(text)
        }
    }
}

@BindingAdapter("phone_contact")
fun phoneContact(view: View, text: String?) {
    if (text != null) {
        view.setOnClickListener {
            view?.context?.phoneContact(text)
        }
    }
}

@BindingAdapter("phone_contact")
fun phoneContact(view: ElasticLayout, text: String?) {
    if (text != null) {
        view.onClick {
            view?.context?.phoneContact(text)
        }
    }
}