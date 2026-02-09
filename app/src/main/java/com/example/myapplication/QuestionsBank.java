package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {

    public static List<QuestionsList> getQuestions(String topic) {

        List<QuestionsList> questions = new ArrayList<>();

        if (topic.equals("ООП")) {

            questions.add(new QuestionsList(
                    "Что такое объект в ООП?",
                    "Набор функций",
                    "Экземпляр класса",
                    "Тип данных",
                    "Алгоритм",
                    "Экземпляр класса"
            ));

            questions.add(new QuestionsList(
                    "Что такое инкапсуляция?",
                    "Сокрытие реализации",
                    "Наследование классов",
                    "Перегрузка методов",
                    "Создание объектов",
                    "Сокрытие реализации"
            ));
        }

        return questions;
    }
}
