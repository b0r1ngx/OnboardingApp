package com.smechotech.onboarding.ui.features

import com.smechotech.onboarding.MainActivity

const val FIRST_TIME_LAUNCH = "FIRST_TIME_LAUNCH"
const val USER_AUTHORIZED = "USER_AUTHORIZED"

fun firstTimeLaunch(): Boolean {
    val firsTimeLaunch = MainActivity.sharedPreferences.getBoolean(FIRST_TIME_LAUNCH, true)
    with(MainActivity.sharedPreferences.edit()) {
        putBoolean(FIRST_TIME_LAUNCH, false)
        apply()
    }
    return firsTimeLaunch
}

fun isUserNotAuthorized() = MainActivity.sharedPreferences.getBoolean(USER_AUTHORIZED, false)
fun userPassAuthorization() = with(MainActivity.sharedPreferences.edit()) {
    putBoolean(USER_AUTHORIZED, true)
    apply()
}