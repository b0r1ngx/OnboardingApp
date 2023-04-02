package com.smechotech.onboarding.data

val testTest = Test(
    title = "Аккуратнее с розетками",
    theory = "В нашем офисе розетки расположены в большом количестве вокруг всех столов. " +
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
