package com.selsela.cpapp.utils

import androidx.lifecycle.MutableLiveData
import com.example.basecleanarchapp.utils.Common


const val DEVICE_TYPE = "Android"
var DEVICE_NAME = Common.getDeviceName()
var isConfigChange: Boolean = false
const val INTRO_VIEWPAGER = 1
const val POS = 0
const val PENDING = 0
const val VERIFIED = 1
var authChanged: MutableLiveData<Boolean> = MutableLiveData(false)
