package com.smechotech.onboarding.ui.features

import com.smechotech.onboarding.MainActivity

const val FIRST_TIME_LAUNCH = "FIRST_TIME_LAUNCH"
const val SHOW_ON_BOARDING = "SHOW_ON_BOARDING"
const val USER_AUTHORIZED = "USER_AUTHORIZED"

fun firstTimeLaunch(): Boolean {
    val firstTimeLaunch = MainActivity.sharedPreferences.getBoolean(FIRST_TIME_LAUNCH, true)
    with(MainActivity.sharedPreferences.edit()) {
        putBoolean(FIRST_TIME_LAUNCH, false)
        commit()
    }
    return firstTimeLaunch
}

fun showOnBoarding() = MainActivity.sharedPreferences.getBoolean(SHOW_ON_BOARDING, true)
fun userPassOnBoarding() = with(MainActivity.sharedPreferences.edit()) {
    putBoolean(SHOW_ON_BOARDING, false)
    commit()
}


fun isUserAuthorized() = MainActivity.sharedPreferences.getBoolean(USER_AUTHORIZED, false)
fun userPassAuthorization() = with(MainActivity.sharedPreferences.edit()) {
    putBoolean(USER_AUTHORIZED, true)
    commit()
}