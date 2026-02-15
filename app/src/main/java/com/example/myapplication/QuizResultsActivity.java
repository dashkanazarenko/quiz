package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class QuizResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView correctAnswers = findViewById(R.id.correctAnswers);
        TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);
        AppCompatButton startNewQuizBtn = findViewById(R.id.startNewQuizBtn);

        int correct = getIntent().getIntExtra("correct", 0);
        int total = getIntent().getIntExtra("total", 0);
        int incorrect = total - correct;

        correctAnswers.setText("Правильных ответов: " + correct);
        incorrectAnswers.setText("Неправильных ответов: " + incorrect);

        startNewQuizBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
