package com.alamat.alamattrainingapp.radio.network;

import android.util.Log;

import com.alamat.alamattrainingapp.NewsAPI.network.APICalls;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RadioApiManager {

    private static Retrofit retrofit;

    private static Retrofit getInstance(){
        if (retrofit ==null){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NotNull String s) {
                    Log.e("retrofit", s );
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://mp3quran.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;

    }

    public static APICalls getAPIS(){
        return getInstance().create(APICalls.class);
    }

}
