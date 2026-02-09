package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionText, counterText;
    private Button option1, option2, option3, option4, nextBtn;

    private List<QuestionsList> questionsList;
    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        counterText = findViewById(R.id.counterText);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);

        String topic = getIntent().getStringExtra("selectedTopic");
        questionsList = QuestionsBank.getQuestions(topic);

        showQuestion();

        option1.setOnClickListener(v -> selectAnswer(option1));
        option2.setOnClickListener(v -> selectAnswer(option2));
        option3.setOnClickListener(v -> selectAnswer(option3));
        option4.setOnClickListener(v -> selectAnswer(option4));

        nextBtn.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(this, "Выберите вариант", Toast.LENGTH_SHORT).show();
            } else {
                questionsList.get(currentQuestionIndex)
                        .setUserSelectedAnswer(selectedAnswer);
                nextQuestion();
            }
        });
    }

    private void showQuestion() {
        QuestionsList q = questionsList.get(currentQuestionIndex);

        counterText.setText((currentQuestionIndex + 1) + "/" + questionsList.size());
        questionText.setText(q.getQuestion());

        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());

        selectedAnswer = "";
    }

    private void selectAnswer(Button button) {
        selectedAnswer = button.getText().toString();
    }

    private void nextQuestion() {
        currentQuestionIndex++;

        if (currentQuestionIndex < questionsList.size()) {
            showQuestion();
        } else {
            Intent intent = new Intent(this, QuizResultsActivity.class);
            intent.putExtra("correct", getCorrectAnswers());
            intent.putExtra("total", questionsList.size());
            startActivity(intent);
            finish();
        }
    }

    private int getCorrectAnswers() {
        int correct = 0;

        for (QuestionsList q : questionsList) {
            if (q.getUserSelectedAnswer().equals(q.getAnswer())) {
                correct++;
            }
        }
        return correct;
    }
}
