package com.example.basecleanarchapp.utils

import com.skydoves.elasticviews.*


fun ElasticLayout.onClick(Clicked: () -> Unit) {
    this.setOnFinishListener { Clicked() }
}

fun ElasticCardView.onClick(Clicked: () -> Unit) {
    this.setOnFinishListener { Clicked() }
}

fun ElasticImageView.onClick(Clicked: () -> Unit) {
    this.setOnFinishListener { Clicked() }
}

fun ElasticButton.onClick(Clicked: () -> Unit) {
    this.setOnFinishListener { Clicked() }
}

fun ElasticFloatingActionButton.onClick(Clicked: () -> Unit) {
    this.setOnFinishListener { Clicked() }
}

