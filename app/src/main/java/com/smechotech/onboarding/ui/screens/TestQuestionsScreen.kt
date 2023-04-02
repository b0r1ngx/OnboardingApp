package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smechotech.onboarding.R
import com.smechotech.onboarding.UserViewModel
import com.smechotech.onboarding.data.Answer
import com.smechotech.onboarding.data.Question
import com.smechotech.onboarding.ui.Description
import com.smechotech.onboarding.ui.Navigation
import com.smechotech.onboarding.ui.TextButtonDesign
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
            .padding(all = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            titleText = title,
            modifier = Modifier.weight(0.6f)
        )
        Description(
            descriptionText = question.quest,
            modifier = Modifier.weight(0.9f)
        )
        QuestionsList(
            question = question,
            isAnswerCheck = isAnswerCheck,
            isNext = isNext,
            viewModel = viewModel,
            modifier = Modifier.weight(1f)
        )
        ButtonCheckTest(
            isNext = isNext,
            isAnswerCheck = isAnswerCheck,
            isFewAnswer = question.isFewAnswers,
            navController = navController,
            viewModel = viewModel,
            modifier = Modifier
                .padding(all = 10.dp)
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
    if (question.isFewAnswers) FewAnswers(question, isAnswerCheck, isNext, modifier)
    else NotFewAnswers(question, isAnswerCheck, viewModel, modifier)
}

@Composable
fun FewAnswers(
    question: Question,
    isNext: MutableState<Boolean>,
    isAnswerCheck: MutableState<Boolean>,
    modifier: Modifier = Modifier
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
    Column(modifier = modifier) {
        for (answer in question.answers) {
            AnswerButton(answer, isAnswerCheck, viewModel)
        }
    }
}

@Composable
fun AnswerButton(
    answer: Answer,
    isAnswerCheck: MutableState<Boolean>,
    viewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    var color by remember { mutableStateOf(Color(0xD6FFFFFF)) }

    TextButtonDesign(
        onClick = {
            isAnswerCheck.value = true
            if (answer.isCorrect) {
                color = Color(66, 238, 38)
                viewModel.currentCorrectAnswers++
            } else color = Color(229, 29, 2)
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color(0xD6FFFFFF),
            disabledContentColor = Color.Black,
            disabledContainerColor = color
        ),
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
    TextButtonDesign(
        onClick = {
            if (isFewAnswer) {
                isNext.value = !isNext.value
            } else {
                with(viewModel) {
                    currentQuestionIndex++
                    if (currentQuestionIndex == currentTestQuestionSize) {
                        navController.navigate(Navigation.RewardingAfterTestScreen.name)
                    } else {
                        navController.navigate(Navigation.TestQuestionScreen.name)
                    }
                }
            }
        },
        enabled = isAnswerCheck.value,
        modifier = modifier
    ) {
        if (isFewAnswer && !isNext.value)
            Text(text = stringResource(id = R.string.check_test))
        else
            Text(text = stringResource(id = R.string.next))
    }
}

@Preview(
    showBackground = true,
    device = PIXEL_4
)
@Composable
fun TestQuestionsPreview() {
    TestQuestionScreen(
        navController = rememberNavController(),
        viewModel = UserViewModel()
    )
}