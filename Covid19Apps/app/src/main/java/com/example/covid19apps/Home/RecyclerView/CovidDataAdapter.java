package com.example.covid19apps.Home.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19apps.Home.API.ResponseItem;
import com.example.covid19apps.R;
import com.squareup.picasso.Picasso;

class CovidDataAdapter extends ListAdapter<ResponseItem, CovidDataAdapter.ViewHolder> {
    private final ItemClickableCallback itemClickableCallback;

    protected CovidDataAdapter(@NonNull ItemClickableCallback userClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<ResponseItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull ResponseItem oldItem, @NonNull ResponseItem newItem) {
                return oldItem.getCountryInfo().getId() == newItem.getCountryInfo().getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ResponseItem oldItem, @NonNull ResponseItem newItem) {
                return oldItem.getCountryInfo().getId() == newItem.getCountryInfo().getId()
                        && oldItem.getCountry().equals(newItem.getCountry())
                        && oldItem.getContinent().equals(newItem.getContinent())
                        ;
            }
        }).build());
        this.itemClickableCallback = userClickableCallback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvCountry.setText(getItem(position).getCountry());
        holder.tvContinent.setText(getItem(position).getContinent());
        Picasso.get().load(getItem(position).getCountryInfo().getFlag()).placeholder(R.drawable.flag_place_holder).error(R.drawable.flag_error_place_holder).into(holder.tvFlag);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCountry;
        TextView tvContinent;
        ImageView tvFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.country);
            tvContinent= itemView.findViewById(R.id.continent);
            tvFlag = itemView.findViewById(R.id.flag);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                ResponseItem user = getItem(position);
                itemClickableCallback.onClick(v, user);
            }
        }
    }
}
