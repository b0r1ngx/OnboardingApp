package com.smechotech.onboarding

import androidx.lifecycle.ViewModel
import com.smechotech.onboarding.data.Test
import com.smechotech.onboarding.data.testTest

class UserViewModel: ViewModel() {

    /**
     * Constants
     */
    val ONE_LESSON_EXP: Int = 20

    /**
     * User Data
     */
    var userLogin: String = ""
    var userPassword: String = ""
    var userExperience: Int = 0
    var userDiamonds: Int = 0

    /**
    * Current Test
     */
    var test: Test = testTest
    var currentTestQuestionSize: Int = test.questions.size
    var currentQuestionIndex: Int = 0
    var currentCorrectAnswers: Int = 0

    //var experienceForTest: Int = 0

}