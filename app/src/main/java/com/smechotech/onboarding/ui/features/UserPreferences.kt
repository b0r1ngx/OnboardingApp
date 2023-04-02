package com.smechotech.onboarding.ui.features

import com.smechotech.onboarding.MainActivity

const val FIRST_TIME_LAUNCH = "FIRST_TIME_LAUNCH"
const val USER_AUTHORIZED = "USER_AUTHORIZED"

fun firstTimeLaunch() = MainActivity.sharedPreferences.getBoolean(FIRST_TIME_LAUNCH, true)

fun showOnBoarding() = firstTimeLaunch()
fun userPassOnBoarding() = with(MainActivity.sharedPreferences.edit()) {
        putBoolean(FIRST_TIME_LAUNCH, false)
        apply()
    }


fun isUserNotAuthorized() = MainActivity.sharedPreferences.getBoolean(USER_AUTHORIZED, false)
fun userPassAuthorization() = with(MainActivity.sharedPreferences.edit()) {
    putBoolean(USER_AUTHORIZED, true)
    apply()
}