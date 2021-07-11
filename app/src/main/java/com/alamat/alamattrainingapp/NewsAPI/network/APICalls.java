package com.alamat.alamattrainingapp.NewsAPI.network;

import com.alamat.alamattrainingapp.NewsAPI.modelNews.SourceNewResponse;
import com.alamat.alamattrainingapp.radio.radioModel.RadioResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICalls {

    @GET("top-headlines/sources")
    Call<SourceNewResponse>getAllSources(@Query("apiKey") String apiKey);

    @GET("radio/radio_ar.json")
    Call<RadioResponse>getRadio();


}
