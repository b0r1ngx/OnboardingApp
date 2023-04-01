package com.smechotech.onboarding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smechotech.onboarding.R
import com.smechotech.onboarding.data.Question
import com.smechotech.onboarding.data.testTest
import com.smechotech.onboarding.ui.Description
import com.smechotech.onboarding.ui.Title

@Composable
fun TestQuestionScreen(testTitle: String, question: Question) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(testTitle)
        Description(question.quest)
        QuestionsList(question)
        ButtonCheckTest(modifier = Modifier.padding(all = 10.dp), isFewAnswer = question.isFewAnswers)
    }
}

@Composable
fun QuestionsList(
    question: Question
) {
    if (question.isFewAnswers) FewAnswers(question) else NotFewAnswers(question)
}

@Composable
fun FewAnswers(
    question: Question
) {
    for (answer in question.answers) {
        Row {
            Checkbox(
                checked = false,
                onCheckedChange = { /*TODO*/ },
            )
            Text(text = answer.text)
        }
    }
}

@Composable
fun NotFewAnswers(
    question: Question
) {
    for (answer in question.answers) {
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = answer.text)
        }
    }
}

@Composable
fun ButtonCheckTest(
    modifier: Modifier = Modifier,
    isFewAnswer: Boolean
) {
    if (isFewAnswer)
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
        ) {
            Text(text = stringResource(id = R.string.Check))
        }
}

@Preview(
    showBackground = true,
    device = PIXEL_4
)
@Composable
fun TestQuestionsPreview() {
    TestQuestionScreen(testTest.title, testTest.questions[0])
}