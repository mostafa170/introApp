package com.alamat.alamattrainingapp.radio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ActivityRadioBinding;
import com.alamat.alamattrainingapp.radio.network.RadioApiManager;
import com.alamat.alamattrainingapp.radio.radioModel.RadioResponse;
import com.alamat.alamattrainingapp.radio.radioModel.RadiosItem;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioActivity extends AppCompatActivity {

    ActivityRadioBinding binding;
    RadioAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_radio);
        callRadio();
        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        adapter = new RadioAdapter(null);
        binding.recyclerViewRadio.setAdapter(adapter);
        binding.recyclerViewRadio.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerViewRadio);
        adapter.setOnPlayClickListener(new RadioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem radiosItem) {
                //play sound
                playRadio(radiosItem.getURL());
            }
        });

        adapter.setOnStopClickListener(new RadioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, RadiosItem radiosItem) {
                //stop sound
                stopRadio();

            }
        });

    }


    public void callRadio(){
        RadioApiManager.getAPIS().getRadio().enqueue(new Callback<RadioResponse>() {
            @Override
            public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                //put in recyclerView
                adapter.changeData(response.body().getRadios());
            }

            @Override
            public void onFailure(Call<RadioResponse> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getLocalizedMessage() );

            }
        });
    }


    MediaPlayer mediaPlayer;

    public void stopRadio(){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
        //Toast.makeText(this, "You click stop", Toast.LENGTH_SHORT).show();
    }

    public void playRadio(String URL){
        stopRadio();
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    //Toast.makeText(RadioActivity.this, "You click start", Toast.LENGTH_SHORT).show();
                }

            });
//            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(RadioActivity.this, "error", Toast.LENGTH_SHORT).show();
        }




    }
}