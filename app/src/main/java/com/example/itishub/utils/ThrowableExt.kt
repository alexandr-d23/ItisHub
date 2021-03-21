package com.example.itishub.utils

import android.content.res.Resources
import com.example.itishub.R
import java.net.UnknownHostException

fun Throwable.getErrorMessage(resources: Resources): String = when {
    this is UnknownHostException -> resources.getString(R.string.no_internet)
    else -> localizedMessage
}