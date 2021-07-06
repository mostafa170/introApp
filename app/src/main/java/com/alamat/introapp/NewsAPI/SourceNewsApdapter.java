package com.alamat.introapp.NewsAPI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.introapp.NewsAPI.modelNews.SourcesItem;
import com.alamat.introapp.R;
import com.alamat.introapp.databinding.ItemSourceNewsBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.os.Build.VERSION_CODES.R;

public class SourceNewsApdapter extends RecyclerView.Adapter<SourceNewsApdapter.ViewHolder> {

    List<SourcesItem> sourcesItems;

    public SourceNewsApdapter(List<SourcesItem> sourcesItems) {
        this.sourcesItems = sourcesItems;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemSourceNewsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                com.alamat.introapp.R.layout.item_source_news,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        SourcesItem sourcesItem = sourcesItems.get(position);
        holder.binding.tvNewsNameList.setText(sourcesItem.getName());
        holder.binding.tvNewsCategoryList.setText(sourcesItem.getCategory());
    }

    @Override
    public int getItemCount() {
        if (sourcesItems == null){
            return 0;
        }else {
            return sourcesItems.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemSourceNewsBinding binding;

        public ViewHolder(@NonNull @NotNull ItemSourceNewsBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
