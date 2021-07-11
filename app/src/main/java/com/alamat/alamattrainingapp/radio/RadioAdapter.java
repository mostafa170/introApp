package com.alamat.alamattrainingapp.radio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ItemRadioBinding;
import com.alamat.alamattrainingapp.radio.radioModel.RadiosItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {

    List<RadiosItem> radiosItems;
    OnItemClickListener onPlayClickListener;
    OnItemClickListener onStopClickListener;

    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }

    public void setOnStopClickListener(OnItemClickListener onStopClickListener) {
        this.onStopClickListener = onStopClickListener;
    }

    public RadioAdapter(List<RadiosItem> radiosItems) {
        this.radiosItems = radiosItems;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemRadioBinding itemRadioBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_radio,parent,false);
        return new ViewHolder(itemRadioBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        RadiosItem radiosItem = radiosItems.get(position);
        holder.radioBinding.tvRadioName.setText(radiosItem.getName());
        if (onPlayClickListener!=null){
            holder.radioBinding.ivRadioPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPlayClickListener.onItemClick(position,radiosItem);
                }
            });
        }

        if (onStopClickListener!=null){
            holder.radioBinding.ivRadioStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStopClickListener.onItemClick(position,radiosItem);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (radiosItems ==null){
            return 0;
        }else {
           return radiosItems.size();
        }
    }

    public void changeData(List<RadiosItem> items){
        radiosItems = items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemRadioBinding radioBinding;
        public ViewHolder(@NonNull @NotNull ItemRadioBinding itemView) {
            super(itemView.getRoot());
            this.radioBinding = itemView;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos , RadiosItem radiosItem);
    }
}
