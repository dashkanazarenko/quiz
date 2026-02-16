# Quiz App (Android)

Простое Android-приложение для прохождения викторин по теме "Объектно-ориентированное программирование (ООП)".

## Функционал

- Главный экран с кнопкой запуска викторины.
- Викторина состоит из вопросов по теме "ООП".
- Пользователь выбирает один из четырёх вариантов ответа.
- После прохождения всех вопросов отображается результат: сколько ответов дано верно из общего количества.
- Возможность вернуться на главный экран и пройти тест заново.

## Технологии

- Язык: **Java**
- Платформа: **Android**
- Архитектура: Activity + Layout (без ViewModel или фреймворков)
- Данные: жёстко заданные вопросы в классе `QuestionsBank`

## Структура проекта

- `MainActivity.java` — стартовый экран.
- `QuizActivity.java` — логика викторины.
- `QuizResultsActivity.java` — экран с результатами.
- `QuestionsList.java` — модель вопроса.
- `QuestionsBank.java` — хранилище вопросов по темам.
- `activity_main.xml`, `activity_quiz.xml`, `activity_results.xml` — макеты экранов.

## Скриншоты проекта

## Скриншоты

### Главный экран
<img width="241" height="532" alt="image" src="https://github.com/user-attachments/assets/4576d59d-6f47-498c-8f2f-cdf8ebea7e80" />


### Экран викторины
<img width="243" height="507" alt="image" src="https://github.com/user-attachments/assets/9a9d42ef-2aa6-41cc-a91b-2d27f2b5d5f1" />


### Результаты
<img width="250" height="523" alt="image" src="https://github.com/user-attachments/assets/463344cb-c74e-44b0-a6e4-a87d617e764d" />



## Как запустить

1. Откройте проект в **Android Studio**.
2. Подключите устройство или запустите эмулятор.
3. Нажмите **Run** (▶️).
