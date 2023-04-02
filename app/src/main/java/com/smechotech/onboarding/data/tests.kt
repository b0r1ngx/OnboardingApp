package com.smechotech.onboarding.data

import com.smechotech.onboarding.R

val testStartExample = Test(
    title = "Первое знакомство",
    theory = "Давайте познакомимся с тем, как выглядит тест! " +
            "Не переживайте, тесты не будут душными =) " +
            "Нажимайте на кнопки, все дальнейшие ответы будут правильными.\n",
    questions = listOf(
        Question(
            quest = "Как у вас настроение?",
            isFewAnswers = false,
            answers = listOf(
                Answer("Отлично", true),
                Answer("Хорошо", true),
                Answer("Средне", true),
                Answer("Ну, сойдёт", true)
            )
        ),
        Question(
            quest = "Вы готовы начать учиться?",
            isFewAnswers = false,
            answers = listOf(
                Answer("Да!", true),
                Answer("Ещё нет, но скоро точно!", true)
            )
        )
    )
)

val testTest = Test(
    title = "Аккуратнее с розетками",
    image = R.drawable.test_example,
    theory = "\tВ нашем офисе розетки расположены в большом количестве вокруг всех столов. " +
            "Но при этом не стоит включать туда 4 чайника или других высоковольтных приборов. " +
            "Используйте розетки только для подключения ваших ноутбуков.",
    questions = listOf(
        Question(
            quest = "Для чего можно использовать розетки?",
            isFewAnswers = false,
            answers = listOf(
                Answer("Для чайников", false),
                Answer("Для ноутбуков", true)
            )
        ),
        Question(
            quest = "Для чего можно использовать ноутбуки?",
            isFewAnswers = false,
            answers = listOf(
                Answer("В виде молотка", false),
                Answer("Для работы", true),
                Answer("Для отдыха", true),
                Answer("Для детей до 3-х лет", false)
            )
        )
    )
)

val tests = listOf(testTest)
