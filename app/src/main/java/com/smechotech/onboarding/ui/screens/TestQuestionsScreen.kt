package com.smechotech.onboarding.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.data.Answer
import com.smechotech.onboarding.data.Question
import com.smechotech.onboarding.ui.Description
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.Title

@Composable
fun TestQuestionScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {
    val isAnswerCheck = remember { mutableStateOf(false) }
    val isNext = remember { mutableStateOf(false) }

    val title by remember { mutableStateOf(viewModel.test.title) }
    val question by remember {
        mutableStateOf(viewModel.test.questions[viewModel.currentQuestionIndex])
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(title)
        Description(question.quest)
        QuestionsList(
            question,
            isAnswerCheck = isAnswerCheck,
            isNext = isNext,
            viewModel = viewModel
        )
        ButtonCheckTest(
            isNext = isNext,
            isAnswerCheck = isAnswerCheck,
            isFewAnswer = question.isFewAnswers,
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier.padding(all = 10.dp)
        )
    }
}

@Composable
fun QuestionsList(
    question: Question,
    isNext: MutableState<Boolean>,
    isAnswerCheck: MutableState<Boolean>,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    if (question.isFewAnswers) FewAnswers(question, isAnswerCheck, isNext)
    else NotFewAnswers(question, isAnswerCheck, viewModel)
}

@Composable
fun FewAnswers(
    question: Question,
    isNext: MutableState<Boolean>,
    isAnswerCheck: MutableState<Boolean>
) {
    Column(horizontalAlignment = Alignment.Start) {
        val mutableStateCheckForEachCheckbox =
            MutableList(question.answers.size) {
                remember { mutableStateOf(false) }
            }
        for (answerIndex in question.answers.indices) {
            AnswerCheckbox(
                answer = question.answers[answerIndex],
                isNext = isNext,
                isCheckBoxChecked = mutableStateCheckForEachCheckbox[answerIndex]
            )
        }
        isAnswerCheck.value =
            mutableStateCheckForEachCheckbox.fold(false) { previousValue, currentValue ->
                previousValue || currentValue.value
            }
    }
}

@Composable
fun AnswerCheckbox(
    answer: Answer,
    isNext: MutableState<Boolean>,
    isCheckBoxChecked: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    var color by remember { mutableStateOf(Color.Blue) }
    val checkedState = remember { mutableStateOf(false) }

    Row {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                isCheckBoxChecked.value = checkedState.value
                if (isNext.value) {
                    color = if (answer.isCorrect) Color.Green else Color.Red
                }
            },
            colors = CheckboxDefaults.colors(disabledCheckedColor = color)
        )
        Text(text = answer.text, modifier = Modifier.padding(top = 12.dp))
    }
}

@Composable
fun NotFewAnswers(
    question: Question,
    isAnswerCheck: MutableState<Boolean>,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    for (answer in question.answers) {
        AnswerButton(answer, isAnswerCheck, viewModel)
    }
}

@Composable
fun AnswerButton(
    answer: Answer,
    isAnswerCheck: MutableState<Boolean>,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    var color by remember { mutableStateOf(Color.Blue) }

    Button(
        onClick = {
            isAnswerCheck.value = true
            if (answer.isCorrect) {
                color = Color.Green
                viewModel.currentCorrectAnswers++
            } else color = Color.Red
        },
        colors = ButtonDefaults.buttonColors(disabledContainerColor = color),
        enabled = !isAnswerCheck.value
    ) {
        Text(text = answer.text)
    }
}

@Composable
fun ButtonCheckTest(
    isNext: MutableState<Boolean>,
    isAnswerCheck: MutableState<Boolean>,
    isFewAnswer: Boolean,
    navController: NavHostController,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = {
            if (isFewAnswer) {
                isNext.value = !isNext.value
            } else {
                with(viewModel) {
                    currentQuestionIndex++
                    Log.d("Test", currentQuestionIndex.toString())
                    Log.d("Test", currentTestQuestionSize.toString())
                    if (currentQuestionIndex == currentTestQuestionSize) {
                        Log.d("Test", "if")
                        navController.navigate(Navigation.RewardingAfterTestScreen.name)
                    } else {
                        Log.d("Test", "else")
                        navController.navigate(Navigation.TestQuestionScreen.name)
                    }
                }
            }
        },
        enabled = isAnswerCheck.value,
        modifier = modifier
    ) {
        if (isFewAnswer && !isNext.value)
            Text(text = stringResource(id = R.string.Check))
        else
            Text(text = stringResource(id = R.string.Next))
    }
}

//@Preview(
//    showBackground = true,
//    device = PIXEL_4
//)
//@Composable
//fun TestQuestionsPreview() {
//    TestQuestionScreen(
//        navController = rememberNavController(),
//        viewModel = UserViewModel(),
//        testTest.title,
//        testTest.questions[0],
//        correctAnswersCount = MutableState
//    )
//}