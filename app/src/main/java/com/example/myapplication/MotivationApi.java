package com.example.myapplication;

import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MotivationApi {
    private static final String API_URL = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=ru";
    private OkHttpClient client = new OkHttpClient();

    public interface MotivationCallback {
        void onSuccess(String quote);
        void onError(String error);
    }

    public void getMotivationQuote(MotivationCallback callback) {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError("Ошибка загрузки: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseData = response.body().string();
                        JSONObject json = new JSONObject(responseData);
                        String quoteText = json.getString("quoteText");
                        String quoteAuthor = json.getString("quoteAuthor");

                        String fullQuote = quoteText;
                        if (!quoteAuthor.isEmpty()) {
                            fullQuote += "\n\n— " + quoteAuthor;
                        }

                        callback.onSuccess(fullQuote);
                    } catch (Exception e) {
                        callback.onError("Ошибка обработки данных: " + e.getMessage());
                    }
                } else {
                    callback.onError("Ошибка сервера: " + response.code());
                }
            }
        });
    }
}