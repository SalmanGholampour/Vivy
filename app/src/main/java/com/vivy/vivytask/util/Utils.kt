package com.vivy.vivytask.util

import com.vivy.vivytask.BuildConfig

private const val DOCTOR_URL_POST_FIX = ".json"
private const val DOCTOR_URL_PRE_FIX = "doctors"

fun getUrl(lastKey: String?): String {
    return if (lastKey.isNullOrEmpty()) {
        "${BuildConfig.BASE_URL}$DOCTOR_URL_PRE_FIX$DOCTOR_URL_POST_FIX"
    } else {
        "${BuildConfig.BASE_URL}$DOCTOR_URL_PRE_FIX-$lastKey$DOCTOR_URL_POST_FIX"
    }

}