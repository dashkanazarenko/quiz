package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizResultsActivity extends AppCompatActivity {

    private TextView motivationQuote;
    private MotivationApi motivationApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final AppCompatButton startNewQuiz = findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswers = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);
        final ImageView resultImage = findViewById(R.id.resultImage);

        // Инициализация нового TextView для мотивационной фразы
        motivationQuote = findViewById(R.id.motivationQuote);
        motivationApi = new MotivationApi();

        final int getCorrectAnswers = getIntent().getIntExtra("correct", 0);
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect", 0);

        correctAnswers.setText("Количество верных ответов: " + getCorrectAnswers);
        incorrectAnswers.setText("Количество неверных ответов: " + getIncorrectAnswers);

        if (getIncorrectAnswers > 6) {
            resultImage.setImageResource(R.drawable.sad);
        } else {
            resultImage.setImageResource(R.drawable.clapping);
        }

        // Загрузка мотивационной фразы
        loadMotivationQuote();

        startNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResultsActivity.this, MainActivity.class));
                finish();
            }
        });

        // Обработка нажатия кнопки "Назад"
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(QuizResultsActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void loadMotivationQuote() {
        // Показываем TextView и устанавливаем текст загрузки
        motivationQuote.setVisibility(View.VISIBLE);
        motivationQuote.setText("Загрузка мотивации...");

        motivationApi.getMotivationQuote(new MotivationApi.MotivationCallback() {
            @Override
            public void onSuccess(String quote) {
                runOnUiThread(() -> motivationQuote.setText(quote));
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    motivationQuote.setVisibility(View.GONE);
                    Toast.makeText(QuizResultsActivity.this,
                            "Не удалось загрузить мотивационную фразу",
                            Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}