package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    public static List<QuestionsList> getQuestions() {
        List<QuestionsList> questions = new ArrayList<>();

        questions.add(new QuestionsList(
                "Тестовый вопрос",
                "Вариант 1",
                "Вариант 2",
                "Вариант 3",
                "Вариант 4",
                "Вариант 1"
        ));

        return questions;
    }
}
