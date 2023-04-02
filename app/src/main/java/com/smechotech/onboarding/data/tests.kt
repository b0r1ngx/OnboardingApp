package com.smechotech.onboarding.data

import com.smechotech.onboarding.R

val exampleStartTest = Test(
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

val funTest = Test(
    title = "Аккуратнее с розетками",
    image = R.drawable.test_example,
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

val safetyPrecautionsTest = Test(
    title = "Техника безопасности в офисе",
    theory = "Если вы обнаружили подозрительный пакет в офисе, сообщите об этом своему начальнику. " +
            "Длина пароля для безопасного входа в компьютер должна быть 12 символов." +
            "Не следует размещать на бумаге имена и фамилии сотрудников, пароли и логины, Коды доступа и пин-коды." +
            "Cледует менять пароль раз в месяц",
    questions = listOf(
        Question(
            quest = "Что следует делать, если вы обнаружили подозрительный пакет в офисе?",
            answers = listOf(
                Answer("Открыть его и проверить содержимое", false),
                Answer("Сообщить об этом своему начальнику", true),
                Answer("Отнести пакет к двери и выбросить его в мусорный контейнер", false),
                Answer("Поставить пакет на стол и подождать, что кто-то заберет его", false),
            )
        ),
        Question(
            quest = "Какой должна быть длина пароля для безопасного входа в компьютер?",
            answers = listOf(
                Answer("4 символа", false),
                Answer("8 символов", false),
                Answer("12 символов", true),
                Answer("16 символов", false)
            )
        ),
        Question(
            quest = "Какую информацию не следует размещать на бумаге и оставлять на рабочем столе?",
            answers = listOf(
                Answer("Имена и фамилии сотрудников", false),
                Answer("Пароли и логины", false),
                Answer("Коды доступа и пин-коды", false),
                Answer("Все перечисленное", true)
            )
        ),
        Question(
            quest = "Как следует поступать, если вы нашли USB-флешку на рабочем месте?",
            answers = listOf(
                Answer("Подключить ее к компьютеру и проверить содержимое", false),
                Answer("Попросить начальника проверить содержимое", false),
                Answer("Немедленно вернуть ее владельцу", true),
                Answer("Отнести ее к двери и выбросить в мусорный контейнер", false)
            )
        ),
        Question(
            quest = "Как часто следует менять пароль для безопасности учетной записи электронной почты?",
            answers = listOf(
                Answer("Раз в год", false),
                Answer("Раз в 3 года", false),
                Answer("Раз в месяц", true),
                Answer("Раз в неделю", false)
            )
        )
    )
)

val tests = listOf(funTest, safetyPrecautionsTest)
