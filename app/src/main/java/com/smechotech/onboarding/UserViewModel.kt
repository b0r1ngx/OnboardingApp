package com.smechotech.onboarding

import androidx.lifecycle.ViewModel
import com.smechotech.onboarding.MainActivity.Companion.sharedPreferences
import com.smechotech.onboarding.data.Test
import com.smechotech.onboarding.data.exampleStartTest
import com.smechotech.onboarding.ui.features.FIRST_TIME_LAUNCH

private const val USER_DIAMONDS = "USER_DIAMONDS"
private const val USER_EXPERIENCE = "USER_EXPERIENCE"

private const val ONE_LESSON_EXP: Int = 20
private const val ONE_LESSON_DIAMOND: Int = 5

class UserViewModel: ViewModel() {
    // TODO: maybe create data class User ?
    var userLogin = "ADMIN"
    var userPassword = "12345"
    var userExperience = sharedPreferences.getInt(USER_EXPERIENCE, 0)
    var userDiamonds = sharedPreferences.getInt(USER_DIAMONDS, 0)
    var userAvatarId = R.drawable.avatar_great

    // TODO: maybe create data class Test / separated TestViewModel?
    var test: Test = exampleStartTest
    var currentTestQuestionSize: Int = test.questions.size
    var currentQuestionIndex: Int = 0
    var currentCorrectAnswers: Int = 0

    fun saveRewardsToUserAndResetTestVariables() {
        resetTestVariables()
        saveRewardsToUserPreferences()
    }

    private fun resetTestVariables() {
        currentQuestionIndex = 0
        currentCorrectAnswers = 0
    }

    private fun saveRewardsToUserPreferences() {
        with(sharedPreferences.edit()) {
            putInt(USER_DIAMONDS, userDiamonds)
            putInt(USER_EXPERIENCE, userExperience)
            apply()
        }
    }

    fun getCurrentExpAndAppendItToUser(): Int {
        val earnedExp = currentCorrectAnswers * ONE_LESSON_EXP
        userExperience += earnedExp
        return earnedExp
    }
    fun getCurrentDiamondsAndAppendItToUser(): Int {
        val earnedDiamonds = currentCorrectAnswers * ONE_LESSON_DIAMOND
        userDiamonds += earnedDiamonds
        return earnedDiamonds
    }

    var leadersName: List<String> = listOf(
        "ADMIN",
        "ADMIN",
        "ADMIN",
        "ADMIN",
        "ADMIN",
        "ADMIN",
        "ADMIN",
        "ADMIN",
        "ADMIN"
    )
}