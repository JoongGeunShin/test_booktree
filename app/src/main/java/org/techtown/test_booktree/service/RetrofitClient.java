package org.techtown.test_booktree.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// base_url 등록

public class RetrofitClient {
    private final static String BASE_URL = "http://3.38.10.91:8080/";
    private static Retrofit retrofit = null;

    private RetrofitClient() {

    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // 요청을 보낼 base url을 설정한다.
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 파싱을 위한 GsonConverterFactory를 추가한다.
                    .build();
        }

        return retrofit;
    }
}