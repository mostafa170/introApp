package com.alamat.alamattrainingapp.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alamat.alamattrainingapp.R;
import com.alamat.alamattrainingapp.databinding.ItemContactGridBinding;
import com.alamat.alamattrainingapp.databinding.ItemContantListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<ContactModel> contactModels;
    int itemView;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static final int LIST_ITEM = 300;
    public static final int GIRD_ITEM = 200;

    public RecyclerViewAdapter(List<ContactModel> contactModels , int itemView) {
        this.contactModels = contactModels;
        this.itemView = itemView;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (itemView == LIST_ITEM){
            ItemContantListBinding listBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contant_list ,parent,false);
            return new ViewHolder(listBinding);
        }else if (itemView == GIRD_ITEM){
            ItemContactGridBinding gridBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contact_grid ,parent,false);
            return new ViewHolder(gridBinding);
        }else {
            ItemContantListBinding listBinding = DataBindingUtil.
                    inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contant_list ,parent,false);
            return new ViewHolder(listBinding);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ContactModel contactModel = contactModels.get(position);

        if (itemView == LIST_ITEM){
            holder.listBinding.ivContactIconList.setImageResource(contactModel.image);
            holder.listBinding.tvContactNameList.setText(contactModel.name);
            holder.listBinding.tvContactStatusList.setText(contactModel.status);
        }else if (itemView == GIRD_ITEM){
            holder.gridBinding.ivContactIconList.setImageResource(contactModel.image);
            holder.gridBinding.tvContactNameList.setText(contactModel.name);
            holder.gridBinding.tvContactStatusList.setText(contactModel.status);
        }

        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(position,contactModel);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (contactModels == null){
            return 0;
        }else {
            return contactModels.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemContantListBinding listBinding;
        ItemContactGridBinding gridBinding;
        public ViewHolder (ItemContantListBinding contantListBinding){
            super(contantListBinding.getRoot());
            this.listBinding = contantListBinding;
        }

        public ViewHolder (ItemContactGridBinding gridBinding){
            super(gridBinding.getRoot());
            this.gridBinding = gridBinding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos , ContactModel contactModel);
    }
}
