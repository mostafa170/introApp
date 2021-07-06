package com.alamat.introapp.NewsAPI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.alamat.introapp.NewsAPI.modelNews.SourceNewResponse;
import com.alamat.introapp.NewsAPI.network.ApiManager;
import com.alamat.introapp.R;
import com.alamat.introapp.databinding.ActivityNewsSourcesBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSourcesActivity extends AppCompatActivity {

    ActivityNewsSourcesBinding binding;
    SourceNewsApdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String apiKey ="85754acfc429414cb7546d71fa69ec6d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news_sources);
        callNews(apiKey);
        adapter = new SourceNewsApdapter(null);
        layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewNews.setAdapter(adapter);
        binding.recyclerViewNews.setLayoutManager(layoutManager);
    }

    public void callNews(String apiKey){
        ApiManager.getAPIS().getAllSources(apiKey).enqueue(new Callback<SourceNewResponse>() {
            @Override
            public void onResponse(Call<SourceNewResponse> call, Response<SourceNewResponse> response) {
                if (response.isSuccessful()){
                    //recyclerView
                    adapter = new SourceNewsApdapter(response.body().getSources());
                    binding.recyclerViewNews.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<SourceNewResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: "+ t.getLocalizedMessage() );
            }
        });
    }
}